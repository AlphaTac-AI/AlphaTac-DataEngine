package com.alphatac.dataengine.entity.match;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class MatchDetail {
    private Long match_id;
    private Integer barracks_status_dire;
    private Integer barracks_status_radiant;
    private Integer dire_score;
    private Integer dire_team_id;
    private List<DraftTiming> draft_timings;
    private Integer duration;
    private Integer first_blood_time;
    private List<Integer> radiant_gold_adv;
    private Integer radiant_score;
    private Integer radiant_team_id;
    private Boolean radiant_win;
    private List<Integer> radiant_xp_adv;
    private Long start_time;
    private Integer tower_status_dire;
    private Integer tower_status_radiant;
    private List<MatchPlayer> players;

    public Long getMatch_id() {
        return match_id;
    }

    public void setMatch_id(Long match_id) {
        this.match_id = match_id;
    }

    public Integer getBarracks_status_dire() {
        return barracks_status_dire;
    }

    public void setBarracks_status_dire(Integer barracks_status_dire) {
        this.barracks_status_dire = barracks_status_dire;
    }

    public Integer getBarracks_status_radiant() {
        return barracks_status_radiant;
    }

    public void setBarracks_status_radiant(Integer barracks_status_radiant) {
        this.barracks_status_radiant = barracks_status_radiant;
    }

    public Integer getDire_score() {
        return dire_score;
    }

    public void setDire_score(Integer dire_score) {
        this.dire_score = dire_score;
    }

    public Integer getDire_team_id() {
        return dire_team_id;
    }

    public void setDire_team_id(Integer dire_team_id) {
        this.dire_team_id = dire_team_id;
    }

    public List<DraftTiming> getDraft_timings() {
        return draft_timings;
    }

    public void setDraft_timings(List<DraftTiming> draft_timings) {
        this.draft_timings = draft_timings;
    }

    public Integer getDuration() {
        return duration;
    }

    public void setDuration(Integer duration) {
        this.duration = duration;
    }

    public Integer getFirst_blood_time() {
        return first_blood_time;
    }

    public void setFirst_blood_time(Integer first_blood_time) {
        this.first_blood_time = first_blood_time;
    }

    public List<Integer> getRadiant_gold_adv() {
        return radiant_gold_adv;
    }

    public void setRadiant_gold_adv(List<Integer> radiant_gold_adv) {
        this.radiant_gold_adv = radiant_gold_adv;
    }

    public Integer getRadiant_score() {
        return radiant_score;
    }

    public void setRadiant_score(Integer radiant_score) {
        this.radiant_score = radiant_score;
    }

    public Integer getRadiant_team_id() {
        return radiant_team_id;
    }

    public void setRadiant_team_id(Integer radiant_team_id) {
        this.radiant_team_id = radiant_team_id;
    }

    public Boolean getRadiant_win() {
        return radiant_win;
    }

    public void setRadiant_win(Boolean radiant_win) {
        this.radiant_win = radiant_win;
    }

    public List<Integer> getRadiant_xp_adv() {
        return radiant_xp_adv;
    }

    public void setRadiant_xp_adv(List<Integer> radiant_xp_adv) {
        this.radiant_xp_adv = radiant_xp_adv;
    }

    public Long getStart_time() {
        return start_time;
    }

    public void setStart_time(Long start_time) {
        this.start_time = start_time;
    }

    public Integer getTower_status_dire() {
        return tower_status_dire;
    }

    public void setTower_status_dire(Integer tower_status_dire) {
        this.tower_status_dire = tower_status_dire;
    }

    public Integer getTower_status_radiant() {
        return tower_status_radiant;
    }

    public void setTower_status_radiant(Integer tower_status_radiant) {
        this.tower_status_radiant = tower_status_radiant;
    }

    public List<MatchPlayer> getPlayers() {
        return players;
    }

    public void setPlayers(List<MatchPlayer> players) {
        this.players = players;
    }
}
