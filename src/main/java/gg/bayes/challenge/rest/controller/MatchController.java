package gg.bayes.challenge.rest.controller;

import gg.bayes.challenge.exception.NoItemsFoundException;
import gg.bayes.challenge.exception.NoKillsFoundException;
import gg.bayes.challenge.exception.NoSpellCastsException;
import gg.bayes.challenge.rest.model.HeroDamage;
import gg.bayes.challenge.rest.model.HeroItems;
import gg.bayes.challenge.rest.model.HeroKills;
import gg.bayes.challenge.rest.model.HeroSpells;
import gg.bayes.challenge.service.MatchService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.NotImplementedException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.List;

@Slf4j
@RestController
@RequestMapping("/api/match")
public class MatchController {

  private final MatchService matchService;

  @Autowired
  public MatchController(MatchService matchService) {
    this.matchService = matchService;
  }

  @PostMapping(consumes = "text/plain")
  public ResponseEntity<Long> ingestMatch(@RequestBody @NotNull @NotBlank String payload) {
    final Long matchId = matchService.ingestMatch(payload);
    return ResponseEntity.ok(matchId);
  }

  @GetMapping("{matchId}")
  public ResponseEntity<List<HeroKills>> getMatch(@PathVariable("matchId") Long matchId) {

    List<HeroKills> heroKills = matchService.getHeroKills(matchId);
    if (heroKills == null || heroKills.isEmpty()) {
      throw new NoKillsFoundException("No Kills found for the match id " + matchId);
    }
    return new ResponseEntity<List<HeroKills>>(heroKills, HttpStatus.OK);
  }

  @GetMapping("{matchId}/{heroName}/items")
  public ResponseEntity<List<HeroItems>> getItems(@PathVariable("matchId") Long matchId,
      @PathVariable("heroName") String heroName) {

    List<HeroItems> heroItems = matchService.getHeroItems(matchId, heroName);
    if (heroItems == null || heroItems.isEmpty()) {
      throw new NoItemsFoundException(
          "No Items found for the match id " + matchId + " and hero " + heroName);
    }
    return new ResponseEntity<List<HeroItems>>(heroItems, HttpStatus.OK);
  }

  @GetMapping("{matchId}/{heroName}/spells")
  public ResponseEntity<List<HeroSpells>> getSpells(@PathVariable("matchId") Long matchId,
      @PathVariable("heroName") String heroName) {
    
    List<HeroSpells> heroSpells = matchService.getHeroSpells(matchId, heroName);
    if (heroSpells == null || heroSpells.isEmpty()) {
      throw new NoSpellCastsException(
          "No Spell Casts for the match id " + matchId + " and hero " + heroName);
    }
    return new ResponseEntity<List<HeroSpells>>(heroSpells, HttpStatus.OK);
  }

  @GetMapping("{matchId}/{heroName}/damage")
  public ResponseEntity<List<HeroDamage>> getDamage(@PathVariable("matchId") Long matchId,
      @PathVariable("heroName") String heroName) {
    
    List<HeroDamage> heroDamages = matchService.getDamages(matchId, heroName);
    return new ResponseEntity<List<HeroDamage>>(heroDamages, HttpStatus.OK);
  }
}
