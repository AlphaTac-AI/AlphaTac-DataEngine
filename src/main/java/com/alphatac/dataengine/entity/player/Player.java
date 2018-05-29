package com.alphatac.dataengine.entity.player;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Player {
    private Long account_id;
    private String name;
    private Integer games_played;
    private Integer wins;
    private Integer competitive_rank;
    private Integer sole_competitive_rank;
    private Integer rank_tier;
    private MmrEstimate mmr_estimate;

    public Long getAccount_id() {
        return account_id;
    }

    public void setAccount_id(Long account_id) {
        this.account_id = account_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getGames_played() {
        return games_played;
    }

    public void setGames_played(Integer games_played) {
        this.games_played = games_played;
    }

    public Integer getWins() {
        return wins;
    }

    public void setWins(Integer wins) {
        this.wins = wins;
    }

    public Integer getCompetitive_rank() {
        return competitive_rank;
    }

    public void setCompetitive_rank(Integer competitive_rank) {
        this.competitive_rank = competitive_rank;
    }

    public Integer getSole_competitive_rank() {
        return sole_competitive_rank;
    }

    public void setSole_competitive_rank(Integer sole_competitive_rank) {
        this.sole_competitive_rank = sole_competitive_rank;
    }

    public Integer getRank_tier() {
        return rank_tier;
    }

    public void setRank_tier(Integer rank_tier) {
        this.rank_tier = rank_tier;
    }

    public MmrEstimate getMmr_estimate() {
        return mmr_estimate;
    }

    public void setMmr_estimate(MmrEstimate mmr_estimate) {
        this.mmr_estimate = mmr_estimate;
    }
}

