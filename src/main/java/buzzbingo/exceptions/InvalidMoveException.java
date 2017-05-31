package buzzbingo.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value= HttpStatus.BAD_REQUEST,reason="This move is invalid - check game size and index.")
public class InvalidMoveException extends Exception {
}
