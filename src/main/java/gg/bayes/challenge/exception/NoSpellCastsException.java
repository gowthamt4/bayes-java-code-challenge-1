package gg.bayes.challenge.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.NOT_FOUND)
public class NoSpellCastsException extends RuntimeException {

  /**
   * 
   */
  private static final long serialVersionUID = 6357551373100308486L;
  
  public NoSpellCastsException(String msg) {
    super(msg);
  }

}
