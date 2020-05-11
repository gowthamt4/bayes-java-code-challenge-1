package gg.bayes.challenge.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import gg.bayes.challenge.model.HeroItem;
import gg.bayes.challenge.model.HeroKill;
import gg.bayes.challenge.model.HeroSpell;
import gg.bayes.challenge.service.LogParser;

@Component
public class HeroKillsParser implements LogParser<HeroKill>{

  private static final Pattern PATTERN = Pattern.compile(".* is killed by npc_dota_hero_([a-z]+)");
  
  public List<HeroKill> process(List<String> events) {
    
    Map<HeroKill, Integer> counters = events.stream()
              .map(event -> generate(event))
              .filter(Objects::nonNull)
              .collect(Collectors.groupingBy(s -> s, Collectors.reducing(0, e -> 1, Integer::sum)));
    
    for(Map.Entry<HeroKill, Integer> entry : counters.entrySet()) {
      entry.getKey().setKills(entry.getValue());
    }
    return new ArrayList<>(counters.keySet()) ;
  }

  @Override
  public HeroKill generate(String event) {
    // TODO Auto-generated method stub
    HeroKill heroKill = new HeroKill();
    Matcher regexMatcher = PATTERN.matcher(event);
    if(regexMatcher.matches()) {
      heroKill.setHeroName(regexMatcher.group(1));
      return heroKill;
    } 
    return null;
  }

}
