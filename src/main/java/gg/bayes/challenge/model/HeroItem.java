package gg.bayes.challenge.model;

import java.io.Serializable;
import java.util.Date;

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
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import lombok.Getter;
import lombok.Setter;


@Entity
@Table(name = "hero_items")
@Getter
@Setter
public class HeroItem implements Serializable{
  
  /**
   * 
   */
  private static final long serialVersionUID = 6313854726905351745L;

  @Id
  @Column(name = "hero_item_id")
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Long heroItemId;
  
  @Column(name = "hero_name")
  private String heroName;
  
  @Column(name = "item_name")
  private String item;
  
  @Column(name = "event_processed")
  private Long eventProcessed;
  
  @ManyToOne(cascade = CascadeType.ALL, targetEntity = Match.class)
  @JoinColumn(name = "match_id")
  @Basic(fetch = FetchType.EAGER)
  private Match match;

  @Override
  public int hashCode() {
    final int prime = 31;
    int result = 1;
    result = prime * result + ((heroName == null) ? 0 : heroName.hashCode());
    result = prime * result + ((item == null) ? 0 : item.hashCode());
    result = prime * result + ((match == null) ? 0 : match.hashCode());
    return result;
  }

  @Override
  public boolean equals(Object obj) {
    if (this == obj) return true;
    if (obj == null) return false;
    if (getClass() != obj.getClass()) return false;
    HeroItem other = (HeroItem) obj;
    if (heroName == null) {
      if (other.heroName != null) return false;
    } else if (!heroName.equals(other.heroName)) return false;
    if (item == null) {
      if (other.item != null) return false;
    } else if (!item.equals(other.item)) return false;
    if (match == null) {
      if (other.match != null) return false;
    } else if (!match.equals(other.match)) return false;
    return true;
  }
  
  
}
