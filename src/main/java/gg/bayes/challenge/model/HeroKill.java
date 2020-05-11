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
@Table(name = "hero_kills")
@Getter
@Setter
public class HeroKill implements Serializable {

  /**
   * 
   */
  private static final long serialVersionUID = 941802790699310965L;
  
  @Id
  @Column(name = "hero_kill_id")
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Long heroKillId;

  @Column(name = "hero_name")
  private String heroName;

  @Column(name = "kill_count")
  private Integer kills;
  
  @ManyToOne(cascade = CascadeType.ALL, targetEntity = Match.class)
  @JoinColumn(name = "match_id")
  @Basic(fetch = FetchType.EAGER)
  private Match match;

  @Override
  public int hashCode() {
    final int prime = 31;
    int result = 1;
    result = prime * result + ((heroName == null) ? 0 : heroName.hashCode());
    result = prime * result + ((kills == null) ? 0 : kills.hashCode());
    result = prime * result + ((match == null) ? 0 : match.hashCode());
    return result;
  }

  @Override
  public boolean equals(Object obj) {
    if (this == obj) return true;
    if (obj == null) return false;
    if (getClass() != obj.getClass()) return false;
    HeroKill other = (HeroKill) obj;
    if (heroName == null) {
      if (other.heroName != null) return false;
    } else if (!heroName.equals(other.heroName)) return false;
    if (kills == null) {
      if (other.kills != null) return false;
    } else if (!kills.equals(other.kills)) return false;
    if (match == null) {
      if (other.match != null) return false;
    } else if (!match.equals(other.match)) return false;
    return true;
  }

}
