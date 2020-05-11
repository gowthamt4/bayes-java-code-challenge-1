package gg.bayes.challenge.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import gg.bayes.challenge.model.HeroSpell;
import gg.bayes.challenge.service.LogParser;

@Component
public class HeroSpellsParser implements LogParser<HeroSpell>{

  private static final Pattern PATTERN =
      Pattern.compile(".* npc_dota_hero_([a-z]+) casts ability ([a-z_]+) \\(lvl [0-9]+ \\) .*");
  
  public List<HeroSpell> process(List<String> events) {
    List<HeroSpell> spellList =  events.stream()
          .map(event -> generate(event))
          .filter(Objects::nonNull)
          .collect(Collectors.toList());
     
    Map<HeroSpell, Integer> counters = spellList.stream()
        .collect(Collectors.groupingBy(s -> s, Collectors.reducing(0, e -> 1, Integer::sum)));
    for(Map.Entry<HeroSpell, Integer> entry : counters.entrySet()) {
      entry.getKey().setCasts(entry.getValue());
    }
     return new ArrayList<>(counters.keySet()) ;
  }
  
  
  @Override
  public HeroSpell generate(String event) {
    HeroSpell heroSpell = new HeroSpell();
    Matcher regexMatcher = PATTERN.matcher(event);
    if(regexMatcher.matches()) {
      heroSpell.setHeroName(regexMatcher.group(1));
      heroSpell.setSpell(regexMatcher.group(2));
      return heroSpell;
    }
    return null;
  }

 }