package ro.blockchainpki.client.utils;

import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import ro.blockchainpki.client.exceptions.BadRequestException;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class SessionUtils {

    public static Map<String, String> tokenMap = new HashMap<>();


    public static String getJSessionID() {
        HttpServletRequest request = getRequest();
        Cookie[] cookies = request.getCookies();
        if (cookies == null)
            return null;
        return Arrays.stream(cookies)
                .filter(c -> c.getName().equals("JSESSIONID"))
                .findFirst()
                .map(Cookie::getValue).orElseThrow(() -> new BadRequestException("Eroare getJSessionId"));
    }
    public static String getSessionValue() {
        return tokenMap.get(getJSessionID());
    }
    public static void putSessionValue(String value) {
        tokenMap.put(getJSessionID(), value);
    }
    public static HttpServletResponse getResponse() {
        return ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getResponse();
    }

    public static HttpServletRequest getRequest() {
        return ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
    }


}