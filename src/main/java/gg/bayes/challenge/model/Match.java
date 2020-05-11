package gg.bayes.challenge.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "matches")
@Getter
@Setter
public class Match implements Serializable {

  /**
   * 
   */
  private static final long serialVersionUID = 3695648737956513789L;

  @Id
  @Column(name = "match_id")
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Long matchId;
  
  @OneToMany(cascade = CascadeType.ALL, mappedBy = "match")
  private List<HeroKill> heroKills = new ArrayList<HeroKill>();
  
  @OneToMany(cascade = CascadeType.ALL, mappedBy = "match")
  private List<HeroItem> heroItems = new ArrayList<HeroItem>();
  
  @OneToMany(cascade = CascadeType.ALL, mappedBy = "match")
  private List<HeroSpell> heroSpells = new ArrayList<HeroSpell>();
  
  @OneToMany(cascade = CascadeType.ALL, mappedBy = "match")
  private List<HeroDamage> heroDamages = new ArrayList<HeroDamage>();

}
