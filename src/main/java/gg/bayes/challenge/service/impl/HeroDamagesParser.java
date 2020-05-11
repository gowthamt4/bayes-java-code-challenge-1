package gg.bayes.challenge.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import gg.bayes.challenge.model.HeroKill;
import gg.bayes.challenge.model.HeroDamage;
import gg.bayes.challenge.service.LogParser;

@Component
public class HeroDamagesParser implements LogParser<HeroDamage> {

  private static final Pattern PATTERN = Pattern
      .compile(".* npc_dota_hero_([a-z]+) hits npc_dota_hero_([a-z]+) [a-z_\\s]+ (\\d+) damage .*");

  public List<HeroDamage> process(List<String> events) {

    List<HeroDamage> damagesList = events.stream().map(event -> generate(event))
        .filter(Objects::nonNull).collect(Collectors.toList());

    Map<HeroDamage, List<HeroDamage>> counters =
        damagesList.stream().collect(Collectors.groupingBy(s -> s));

    for (Map.Entry<HeroDamage, List<HeroDamage>> entry : counters.entrySet()) {
      Integer totalDamage = entry.getValue().stream().map(HeroDamage::getDamage).reduce(0, Integer::sum);
      entry.getKey().setTotalDamage(totalDamage);
      entry.getKey().setDamageInstances(entry.getValue().size());
    }
    
    return new ArrayList<>(counters.keySet());

  }

  @Override
  public HeroDamage generate(String event) {
    HeroDamage heroDamage = new HeroDamage();
    Matcher regexMatcher = PATTERN.matcher(event);
    if (regexMatcher.matches()) {
      heroDamage.setHeroName(regexMatcher.group(1));
      heroDamage.setTarget(regexMatcher.group(2));
      heroDamage.setDamage(Integer.parseInt(regexMatcher.group(3)));
      return heroDamage;
    }
    return null;
  }

}
