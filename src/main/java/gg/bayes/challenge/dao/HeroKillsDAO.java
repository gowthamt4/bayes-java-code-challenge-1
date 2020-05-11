package gg.bayes.challenge.dao;

import java.util.List;

import gg.bayes.challenge.rest.model.HeroKills;

public interface HeroKillsDAO {

  List<HeroKills> findAllByMatchId(Long matchId);

}
