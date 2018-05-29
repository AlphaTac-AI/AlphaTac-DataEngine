package com.alphatac.dataengine.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.springframework.data.annotation.Id;

@JsonIgnoreProperties(ignoreUnknown = true)
public class MatchInfo {
    private Long match_id;
    private Long team_id;
    private Long start_time;
    private Integer leagueid;
    private String league_name;

    public Long getMatch_id() {
        return match_id;
    }

    public void setMatch_id(Long match_id) {
        this.match_id = match_id;
    }

    public Long getTeam_id() {
        return team_id;
    }

    public void setTeam_id(Long team_id) {
        this.team_id = team_id;
    }

    public Long getStart_time() {
        return start_time;
    }

    public void setStart_time(Long start_time) {
        this.start_time = start_time;
    }

    public Integer getLeagueid() {
        return leagueid;
    }

    public void setLeagueid(Integer leagueid) {
        this.leagueid = leagueid;
    }

    public String getLeague_name() {
        return league_name;
    }

    public void setLeague_name(String league_name) {
        this.league_name = league_name;
    }
}
