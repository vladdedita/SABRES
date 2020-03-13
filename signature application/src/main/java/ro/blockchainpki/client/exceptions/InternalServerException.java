package ro.blockchainpki.client.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
public class InternalServerException extends RuntimeException {

    public InternalServerException(final String message){
        super(message);
    }

}
