package ro.blockchainpki.proxy.filters;


import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.springframework.stereotype.Component;
import ro.blockchainpki.proxy.model.SignHashPayload;
import ro.blockchainpki.proxy.service.BlockchainService;

import java.io.ByteArrayOutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@Slf4j
@Component
@RequiredArgsConstructor
public class SignRequestFilter extends ZuulFilter {

    private final BlockchainService blockchainService;

    @Override
    public String filterType() {
        return "post";
    }

    @Override
    public int filterOrder() {
        return 2;
    }

    @Override
    public boolean shouldFilter() {
        RequestContext context = RequestContext.getCurrentContext();
        return context.getRequest().getRequestURI().contains("/signatures/signHash");
    }

    @lombok.SneakyThrows
    @Override
    public Object run() {
        RequestContext context = RequestContext.getCurrentContext();
        SignHashPayload parsedBody;


        try (ByteArrayOutputStream outputStream = new ByteArrayOutputStream(1024)) {
            IOUtils.copy(context.getRequest().getInputStream(), outputStream);
            log.info("******************REQUEST BODY******************");
            String body = outputStream.toString();
            log.info(body);

            parsedBody = new ObjectMapper().readValue(body, SignHashPayload.class);

            context.setResponseBody(body);
        }
        try (ByteArrayOutputStream outputStream = new ByteArrayOutputStream(1024)) {
            IOUtils.copy(context.getResponseDataStream(), outputStream);
            log.info("******************RESPONSE BODY******************");
            String body = outputStream.toString();
            log.info(body);

            List<String> signatures = getSignatures(body).orElseThrow(() -> new RuntimeException("signHash/signatures returned no sigatures"));
            parsedBody.setSignatures(signatures);
            context.setResponseBody(body);
        }
        System.out.println(parsedBody.toString());
        /**
         * If signature request is successful, log info in blockchain
         */
        if (context.getResponse().getStatus() == 200)
            blockchainService.writeSignRequest(parsedBody);

        return null;
    }

    private Optional<List<String>> getSignatures(String body) throws com.fasterxml.jackson.core.JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        JsonNode node = mapper.readTree(body);
        if (!node.has("signatures"))
            return Optional.empty();
        else {
            JsonNode signaturesNode = node.get("signatures");
            return Optional.of(mapper.convertValue(signaturesNode, ArrayList.class));
        }
    }

    private String getUsername(String body) {
        String[] bodyParts = body.split("&");
        String[] usernameParts = bodyParts[0].split("=");
        return usernameParts[1];
    }
}