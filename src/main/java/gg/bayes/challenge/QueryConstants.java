package gg.bayes.challenge;

public interface QueryConstants {
  
  String GET_HERO_KILLS_BY_MATCH_ID = 
      "select HKS.heroName, HKS.kills from HeroKills as HKS " 
          + "where HKS.match.matchId = :matchId";
  
  
  String GET_HERO_ITEMS_FOR_HERO_BY_MATCH_ID = 
      "select HIS.item, HIS.match.eventProcessed from HeroItems as HIS " 
          + "where HIS.heroName = :heroName"
          + "and HIS.match.matchId = :matchId ";
  
  String GET_SPELLS_BY_HERO_BY_MATCH_ID = 
      "select HSS.spell, HSS.casts from HeroSpells as HSS " 
          + "where HSS.heroName = :heroName"
          + "and HSS.match.matchId = :matchId ";


  String GET_DAMAGES_TO_HERO_BY_MATCH_ID = 
      "select ";
}
