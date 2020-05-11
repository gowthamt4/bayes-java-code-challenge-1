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
import javax.persistence.Transient;

import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "hero_damages")
@Getter
@Setter
public class HeroDamage implements Serializable{
  
  /**
   * 
   */
  private static final long serialVersionUID = 6587584931613933377L;
  
  @Id
  @Column(name = "hero_damage_id")
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Long heroDamageId;
  
  @Column(name = "hero_name")
  private String heroName;
  
  @Column(name = "target")
  private String target;
  
  @Column(name = "damage_instances")
  private Integer damageInstances;
  
  @Column(name = "total_damage")
  private Integer totalDamage;
  
  @Transient
  private Integer damage;
  
  @ManyToOne(cascade = CascadeType.ALL, targetEntity = Match.class)
  @JoinColumn(name = "match_id")
  @Basic(fetch = FetchType.EAGER)
  private Match match;

  @Override
  public int hashCode() {
    final int prime = 31;
    int result = 1;
    result = prime * result + ((damageInstances == null) ? 0 : damageInstances.hashCode());
    result = prime * result + ((heroName == null) ? 0 : heroName.hashCode());
    result = prime * result + ((target == null) ? 0 : target.hashCode());
    result = prime * result + ((totalDamage == null) ? 0 : totalDamage.hashCode());
    return result;
  }

  @Override
  public boolean equals(Object obj) {
    if (this == obj) return true;
    if (obj == null) return false;
    if (getClass() != obj.getClass()) return false;
    HeroDamage other = (HeroDamage) obj;
    if (damageInstances == null) {
      if (other.damageInstances != null) return false;
    } else if (!damageInstances.equals(other.damageInstances)) return false;
    if (heroName == null) {
      if (other.heroName != null) return false;
    } else if (!heroName.equals(other.heroName)) return false;
    if (target == null) {
      if (other.target != null) return false;
    } else if (!target.equals(other.target)) return false;
    if (totalDamage == null) {
      if (other.totalDamage != null) return false;
    } else if (!totalDamage.equals(other.totalDamage)) return false;
    return true;
  }

  
}
