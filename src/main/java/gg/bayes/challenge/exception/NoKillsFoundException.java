package gg.bayes.challenge.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.NOT_FOUND)
public class NoKillsFoundException extends RuntimeException {

  /**
   * 
   */
  private static final long serialVersionUID = 6866892905380373626L;
  
  
  public NoKillsFoundException(String msg) {
    super(msg);
  }
}
