package gg.bayes.challenge.model;

import java.io.Serializable;

import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "hero_spells")
@Getter
@Setter
public class HeroSpell implements Serializable{
  
  /**
   * 
   */
  private static final long serialVersionUID = -5431403423348842219L;

  @Id
  @Column(name = "hero_spell_id")
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Long heroSpellId;
  
  @Column(name = "hero_name")
  private String heroName;
  
  @Column(name = "spell")
  private String spell;
  
  @Column(name = "casts_count")
  private Integer casts;
  
  @ManyToOne(cascade = CascadeType.ALL, targetEntity = Match.class)
  @JoinColumn(name = "match_id")
  @Basic(fetch = FetchType.EAGER)
  private Match match;

  @Override
  public int hashCode() {
    final int prime = 31;
    int result = 1;
    result = prime * result + ((casts == null) ? 0 : casts.hashCode());
    result = prime * result + ((heroName == null) ? 0 : heroName.hashCode());
    result = prime * result + ((match == null) ? 0 : match.hashCode());
    result = prime * result + ((spell == null) ? 0 : spell.hashCode());
    return result;
  }

  @Override
  public boolean equals(Object obj) {
    if (this == obj) return true;
    if (obj == null) return false;
    if (getClass() != obj.getClass()) return false;
    HeroSpell other = (HeroSpell) obj;
    if (casts == null) {
      if (other.casts != null) return false;
    } else if (!casts.equals(other.casts)) return false;
    if (heroName == null) {
      if (other.heroName != null) return false;
    } else if (!heroName.equals(other.heroName)) return false;
    if (match == null) {
      if (other.match != null) return false;
    } else if (!match.equals(other.match)) return false;
    if (spell == null) {
      if (other.spell != null) return false;
    } else if (!spell.equals(other.spell)) return false;
    return true;
  }
  
}
