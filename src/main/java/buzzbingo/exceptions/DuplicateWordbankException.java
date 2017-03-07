package buzzbingo.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value= HttpStatus.FORBIDDEN,reason="This wordbank already exists.")
public class DuplicateWordbankException extends Exception{
}
