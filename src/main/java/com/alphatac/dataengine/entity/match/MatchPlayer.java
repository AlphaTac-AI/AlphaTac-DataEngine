package com.alphatac.dataengine.entity.match;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.springframework.data.annotation.Id;
@JsonIgnoreProperties(ignoreUnknown = true)
public class MatchPlayer {
    private Long account_id;
    private Integer assists;
    private Integer deaths;
    private Integer gold_per_min;
    private Integer hero_damage;
    private Integer hero_healing;
    private Integer hero_id;
    private Integer kills;
    private Integer level;
    private Double teamfight_participation;
    private Integer tower_damage;
    private Integer towers_killed;
    private Integer xp_per_min;
    private Integer total_gold;
    private Integer total_xp;
    private Integer kda;
    private Double lane_efficiency;
    private Integer lane_efficiency_pct;
    private Integer lane_role;

    public Long getAccount_id() {
        return account_id;
    }

    public void setAccount_id(Long account_id) {
        this.account_id = account_id;
    }

    public Integer getAssists() {
        return assists;
    }

    public void setAssists(Integer assists) {
        this.assists = assists;
    }

    public Integer getDeaths() {
        return deaths;
    }

    public void setDeaths(Integer deaths) {
        this.deaths = deaths;
    }

    public Integer getGold_per_min() {
        return gold_per_min;
    }

    public void setGold_per_min(Integer gold_per_min) {
        this.gold_per_min = gold_per_min;
    }

    public Integer getHero_damage() {
        return hero_damage;
    }

    public void setHero_damage(Integer hero_damage) {
        this.hero_damage = hero_damage;
    }

    public Integer getHero_healing() {
        return hero_healing;
    }

    public void setHero_healing(Integer hero_healing) {
        this.hero_healing = hero_healing;
    }

    public Integer getHero_id() {
        return hero_id;
    }

    public void setHero_id(Integer hero_id) {
        this.hero_id = hero_id;
    }

    public Integer getKills() {
        return kills;
    }

    public void setKills(Integer kills) {
        this.kills = kills;
    }

    public Integer getLevel() {
        return level;
    }

    public void setLevel(Integer level) {
        this.level = level;
    }

    public Double getTeamfight_participation() {
        return teamfight_participation;
    }

    public void setTeamfight_participation(Double teamfight_participation) {
        this.teamfight_participation = teamfight_participation;
    }

    public Integer getTower_damage() {
        return tower_damage;
    }

    public void setTower_damage(Integer tower_damage) {
        this.tower_damage = tower_damage;
    }

    public Integer getTowers_killed() {
        return towers_killed;
    }

    public void setTowers_killed(Integer towers_killed) {
        this.towers_killed = towers_killed;
    }

    public Integer getXp_per_min() {
        return xp_per_min;
    }

    public void setXp_per_min(Integer xp_per_min) {
        this.xp_per_min = xp_per_min;
    }

    public Integer getTotal_gold() {
        return total_gold;
    }

    public void setTotal_gold(Integer total_gold) {
        this.total_gold = total_gold;
    }

    public Integer getTotal_xp() {
        return total_xp;
    }

    public void setTotal_xp(Integer total_xp) {
        this.total_xp = total_xp;
    }

    public Integer getKda() {
        return kda;
    }

    public void setKda(Integer kda) {
        this.kda = kda;
    }

    public Double getLane_efficiency() {
        return lane_efficiency;
    }

    public void setLane_efficiency(Double lane_efficiency) {
        this.lane_efficiency = lane_efficiency;
    }

    public Integer getLane_efficiency_pct() {
        return lane_efficiency_pct;
    }

    public void setLane_efficiency_pct(Integer lane_efficiency_pct) {
        this.lane_efficiency_pct = lane_efficiency_pct;
    }

    public Integer getLane_role() {
        return lane_role;
    }

    public void setLane_role(Integer lane_role) {
        this.lane_role = lane_role;
    }
}
