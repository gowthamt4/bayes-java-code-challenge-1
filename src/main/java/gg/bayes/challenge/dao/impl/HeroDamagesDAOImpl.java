package gg.bayes.challenge.dao.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;

import gg.bayes.challenge.QueryConstants;
import gg.bayes.challenge.dao.HeroDamagesDAO;
import gg.bayes.challenge.dao.HeroItemsDAO;
import gg.bayes.challenge.rest.model.HeroDamage;
import gg.bayes.challenge.rest.model.HeroItems;

@Repository
public class HeroDamagesDAOImpl implements HeroDamagesDAO {
  
  @PersistenceContext
  private EntityManager entityManager;

  @Deprecated
  public HeroDamagesDAOImpl() {}

  public HeroDamagesDAOImpl(final EntityManager entityManager) {
      this.entityManager = entityManager;
  }

  @SuppressWarnings("unchecked")
  @Override
  public List<HeroDamage> findAllDamagesToHeroByMatchId(final Long matchId, final String heroName) {
    final Query query = entityManager.createQuery(QueryConstants.GET_DAMAGES_TO_HERO_BY_MATCH_ID);
    query.setParameter("matchId", matchId);
    query.setParameter("heroName", heroName);
    return (List<HeroDamage>) query.getResultList();
  }

}
