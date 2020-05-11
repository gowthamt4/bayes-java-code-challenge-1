package gg.bayes.challenge.dao;

import java.util.List;

import gg.bayes.challenge.rest.model.HeroDamage;

public interface HeroDamagesDAO {
  List<HeroDamage> findAllDamagesToHeroByMatchId(Long matchId, String heroName);
}
