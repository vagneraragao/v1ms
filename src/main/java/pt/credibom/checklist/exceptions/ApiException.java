package pt.credibom.checklist.exceptions;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public class ApiException extends RuntimeException {

    private static final long serialVersionUID = 8192521164568486539L;
    
    private HttpStatus httpStatus;
    
    public ApiException(String message, HttpStatus httpStatus) {
        super(message);
        this.httpStatus = httpStatus;
    }
}
