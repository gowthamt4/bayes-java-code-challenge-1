package gg.bayes.challenge.dao.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;

import gg.bayes.challenge.QueryConstants;
import gg.bayes.challenge.dao.HeroSpellsDAO;
import gg.bayes.challenge.rest.model.HeroSpells;

@Repository
public class HeroSpellsDAOImpl implements HeroSpellsDAO {
  
  @PersistenceContext
  private EntityManager entityManager;

  @Deprecated
  public HeroSpellsDAOImpl() {}

  public HeroSpellsDAOImpl(final EntityManager entityManager) {
      this.entityManager = entityManager;
  }

  @SuppressWarnings("unchecked")
  @Override
  public List<HeroSpells> findAllSpellsByHeroByMatchId(Long matchId, String heroName) {
    final Query query = entityManager.createQuery(QueryConstants.GET_SPELLS_BY_HERO_BY_MATCH_ID);
    query.setParameter("matchId", matchId);
    query.setParameter("heroName", heroName);
    return (List<HeroSpells>) query.getResultList();
  }

}
