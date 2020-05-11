package gg.bayes.challenge.dao;

import java.util.List;

import gg.bayes.challenge.rest.model.HeroItems;

public interface HeroItemsDAO {

  List<HeroItems> findAllItemsByHeroByMatchId(Long matchId, String heroName);

}
