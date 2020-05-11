package gg.bayes.challenge.exception;

public class HeroNotFoundException extends RuntimeException{
  
  /**
   * 
   */
  private static final long serialVersionUID = -845990608236650582L;

  public HeroNotFoundException(String msg) {
    super(msg);
  }
}
