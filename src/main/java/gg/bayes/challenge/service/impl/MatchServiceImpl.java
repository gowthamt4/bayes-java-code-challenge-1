package gg.bayes.challenge.service.impl;

import gg.bayes.challenge.dao.HeroDamagesDAO;
import gg.bayes.challenge.dao.HeroItemsDAO;
import gg.bayes.challenge.dao.HeroKillsDAO;
import gg.bayes.challenge.dao.HeroSpellsDAO;
import gg.bayes.challenge.dao.MatchDAO;
import gg.bayes.challenge.exception.NoKillsFoundException;
import gg.bayes.challenge.model.HeroItem;
import gg.bayes.challenge.model.HeroKill;
import gg.bayes.challenge.model.HeroSpell;
import gg.bayes.challenge.model.Match;
import gg.bayes.challenge.rest.model.HeroDamage;
import gg.bayes.challenge.rest.model.HeroItems;
import gg.bayes.challenge.rest.model.HeroKills;
import gg.bayes.challenge.rest.model.HeroSpells;
import gg.bayes.challenge.service.LogParser;
import gg.bayes.challenge.service.MatchService;
import lombok.extern.slf4j.Slf4j;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Stream;

import javax.transaction.Transactional;

import org.apache.commons.lang3.NotImplementedException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class MatchServiceImpl implements MatchService {
  
  @Autowired
  private MatchDAO matchDAO;
  
  @Autowired
  private HeroKillsDAO heroKillsDAO;

  @Autowired
  private HeroItemsDAO heroItemsDAO;
  
  @Autowired
  private HeroSpellsDAO heroSpellsDAO;
  
  @Autowired
  private HeroDamagesDAO heroDamagesDAO;
  
  @Autowired
  private List<LogParser> logParserList;
  
  
  @Autowired
  public MatchServiceImpl() {}

  @Transactional
  @Override
  public Long ingestMatch(String payload) {
    // TODO
    List<String> events = Arrays.asList(payload.split("\\r?\\n"));
    Match match = new Match();
    for(LogParser logParser : logParserList) {
      if(logParser instanceof HeroDamagesParser) {
        match.setHeroDamages((List<gg.bayes.challenge.model.HeroDamage>) logParser.process(events));
      } else if(logParser instanceof HeroSpellsParser) {
        match.setHeroSpells((List<HeroSpell>) logParser.process(events));
      } else if(logParser instanceof HeroKillsParser) {
        match.setHeroKills((List<HeroKill>) logParser.process(events));
      } else {
        match.setHeroItems((List<HeroItem>) logParser.process(events));
      }
    }
    return matchDAO.saveMatchDetails(match);
  }

  @Override
  public List<HeroKills> getHeroKills(final Long matchId) {
    return heroKillsDAO.findAllByMatchId(matchId);
  }

  @Override
  public List<HeroItems> getHeroItems(Long matchId, String heroName) {
    return heroItemsDAO.findAllItemsByHeroByMatchId(matchId, heroName);
  }

  @Override
  public List<HeroSpells> getHeroSpells(Long matchId, String heroName) {
    return heroSpellsDAO.findAllSpellsByHeroByMatchId(matchId, heroName);
  }

  @Override
  public List<HeroDamage> getDamages(Long matchId, String heroName) {
    return heroDamagesDAO.findAllDamagesToHeroByMatchId(matchId, heroName);
  }
}
