package gg.bayes.challenge.service.impl;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import gg.bayes.challenge.model.HeroItem;
import gg.bayes.challenge.service.LogParser;

@Component
public class HeroItemsParser implements LogParser<HeroItem>{
  
  private static final Pattern PATTERN =
      Pattern.compile("\\[([0-9.:]+)\\] npc_dota_hero_([a-z]+) buys item item_([a-z_]+)");

  public List<HeroItem> process(List<String> events) {
    
    return events.stream()
          .map(event -> generate(event))
          .filter(Objects::nonNull)
          .collect(Collectors.toList());
  }
  
  @Override
  public HeroItem generate(String event) {
    // TODO Auto-generated method stub
    HeroItem heroItem = new HeroItem();
    Matcher regexMatcher = PATTERN.matcher(event);
    if(regexMatcher.matches()) {
      heroItem.setHeroName(regexMatcher.group(2));
      heroItem.setItem(regexMatcher.group(3));
      heroItem.setEventProcessed(calculateEpoch(regexMatcher.group(1)));
      //DateTimeFormatter
      return heroItem;
    }
    return null;
  }

  private Long calculateEpoch(String time) {
    SimpleDateFormat simpleDateFormat = new SimpleDateFormat("HH:mm:ss.SSS");
    Date date;
    try {
      date = simpleDateFormat.parse(time);
      return date.getTime();
    } catch (ParseException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }
    return null;
  }

}
