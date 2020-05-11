package gg.bayes.challenge.dao;

import java.util.List;

import gg.bayes.challenge.rest.model.HeroSpells;

public interface HeroSpellsDAO {

  List<HeroSpells> findAllSpellsByHeroByMatchId(Long matchId, String heroName);

}
