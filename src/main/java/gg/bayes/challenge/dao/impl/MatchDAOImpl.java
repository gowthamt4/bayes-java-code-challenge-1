package gg.bayes.challenge.dao.impl;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;

import gg.bayes.challenge.dao.MatchDAO;
import gg.bayes.challenge.model.Match;

@Repository
public class MatchDAOImpl implements MatchDAO{
  @PersistenceContext
  private EntityManager entityManager;

  @Deprecated
  public MatchDAOImpl() {}

  public MatchDAOImpl(final EntityManager entityManager) {
      this.entityManager = entityManager;
  }


  @Override
  public Long saveMatchDetails(Match match) {
    // TODO Auto-generated method stub
    entityManager.persist(match);
    return match.getMatchId();
  }
  
}
