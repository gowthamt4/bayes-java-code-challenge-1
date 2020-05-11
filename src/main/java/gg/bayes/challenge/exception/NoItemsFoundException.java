package gg.bayes.challenge.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.NOT_FOUND)
public class NoItemsFoundException extends RuntimeException {

  /**
   * 
   */
  private static final long serialVersionUID = 5564646087963353165L;

  public NoItemsFoundException(String msg) {
    super(msg);
  }
}
