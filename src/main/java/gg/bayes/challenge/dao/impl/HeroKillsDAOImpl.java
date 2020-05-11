package gg.bayes.challenge.dao.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;

import gg.bayes.challenge.QueryConstants;
import gg.bayes.challenge.dao.HeroKillsDAO;
import gg.bayes.challenge.rest.model.HeroKills;

@Repository
public class HeroKillsDAOImpl implements HeroKillsDAO {
  
  @PersistenceContext
  private EntityManager entityManager;

  @Deprecated
  public HeroKillsDAOImpl() {}

  public HeroKillsDAOImpl(final EntityManager entityManager) {
      this.entityManager = entityManager;
  }

  @SuppressWarnings("unchecked")
  @Override
  public List<HeroKills> findAllByMatchId(final Long matchId) {
    final Query query = entityManager.createQuery(QueryConstants.GET_HERO_KILLS_BY_MATCH_ID);
    query.setParameter("matchId", matchId);
    return (List<HeroKills>) query.getResultList();
  }

}
