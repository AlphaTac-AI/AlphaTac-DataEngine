package com.alphatac.dataengine.entity.match;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class DraftTiming {
    private Integer order;
    private Boolean pick;
    private Integer active_team;
    private Integer hero_id;
    private String player_slot;
    private Integer extra_time;
    private Long total_time_taken;

    public Integer getOrder() {
        return order;
    }

    public void setOrder(Integer order) {
        this.order = order;
    }

    public Boolean getPick() {
        return pick;
    }

    public void setPick(Boolean pick) {
        this.pick = pick;
    }

    public Integer getActive_team() {
        return active_team;
    }

    public void setActive_team(Integer active_team) {
        this.active_team = active_team;
    }

    public Integer getHero_id() {
        return hero_id;
    }

    public void setHero_id(Integer hero_id) {
        this.hero_id = hero_id;
    }

    public String getPlayer_slot() {
        return player_slot;
    }

    public void setPlayer_slot(String player_slot) {
        this.player_slot = player_slot;
    }

    public Integer getExtra_time() {
        return extra_time;
    }

    public void setExtra_time(Integer extra_time) {
        this.extra_time = extra_time;
    }

    public Long getTotal_time_taken() {
        return total_time_taken;
    }

    public void setTotal_time_taken(Long total_time_taken) {
        this.total_time_taken = total_time_taken;
    }
}
