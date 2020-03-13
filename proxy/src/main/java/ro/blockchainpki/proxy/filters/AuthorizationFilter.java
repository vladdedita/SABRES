package ro.blockchainpki.proxy.filters;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.springframework.stereotype.Component;
import ro.blockchainpki.proxy.model.CredentialsAuthorizePayload;
import ro.blockchainpki.proxy.service.BlockchainService;

import java.io.ByteArrayOutputStream;


@Slf4j
@Component
@RequiredArgsConstructor
public class AuthorizationFilter extends ZuulFilter {

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
//        return context.getRequest().getRequestURI().contains("/credentials/authorize");
        return false;
    }

    @lombok.SneakyThrows
    @Override
    public Object run() {
        RequestContext context = RequestContext.getCurrentContext();
        CredentialsAuthorizePayload parsedBody;

        try (ByteArrayOutputStream outputStream = new ByteArrayOutputStream(1024)) {
            IOUtils.copy(context.getRequest().getInputStream(), outputStream);
            log.info("******************REQUEST BODY******************");
            String body = outputStream.toString();
            log.info(body);
            parsedBody = new ObjectMapper().readValue(body,CredentialsAuthorizePayload.class);
            context.setResponseBody(body);
        }
        System.out.println(parsedBody.toString());

//        if(context.getResponse().getStatus() == 200)
//            blockchainService.writeCredentialAccess(parsedBody);


        return null;
    }

    private String getUsername(String body) {
        String[] bodyParts = body.split("&");
        String[] usernameParts = bodyParts[0].split("=");
        return usernameParts[1];
    }
}