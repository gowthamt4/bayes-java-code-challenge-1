package gg.bayes.challenge.service;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import gg.bayes.challenge.model.HeroItem;

public interface LogParser<T> {

  List<T> process(List<String> events);
    
    
  T generate(String event);
}
