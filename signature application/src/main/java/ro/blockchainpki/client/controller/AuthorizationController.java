package ro.blockchainpki.client.controller;

import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ro.blockchainpki.client.service.CSCService;

import static ro.blockchainpki.client.utils.SessionUtils.getSessionValue;

@RestController
@RequiredArgsConstructor
@RequestMapping("/")
public class AuthorizationController {

    private final CSCService service;

    /**
     * Main entrypoint of the application
     * If the user accesses the application for the first time (JSessionID is not associated to a code/value)
     * sends redirect to TransSped authentication provider
     * then the authentication provider redirects the user here with a generated Authorization Code
     * @param code - Authorization Code used to request a CSC Access Token
     * @param state - state used to prevent CSRF
     */
    @SneakyThrows
    @GetMapping("/")
    public void code(@RequestParam(value = "code", required = false) String code, @RequestParam(value = "state", required = false) String state) {
        boolean isFirstAccess = getSessionValue() == null && code == null;
        if (isFirstAccess)
            service.redirectToAuthorization();
        else if(code!=null){
            service.requestToken(code);
        }
    }


}
