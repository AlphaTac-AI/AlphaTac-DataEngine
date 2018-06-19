package com.alphatac.dataengine.entity.match_schedule;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.JsonNode;

import java.util.Map;

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class MatchScheduleEntity {
    private String id;
    private String tournament_logo;
    private String url;
    private String match_id;
    private String round;
    private String status_name;
    private String status;
    private String left_team_score;
    private String right_team_score;
    private String match_time;
    private String match_date;
    private String category;
    private String victory;
    private String team_left_short_name;
    private String team_left_logo;
    private String team_right_short_name;
    private String team_right_logo;
    private String real_data;
    private String real_status;

    @JsonProperty("team")
    private void unpackNameFromNestedObject(Map<String,Object> team){
        Map<String,String> left = (Map<String,String>)team.get("left");
        Map<String,String> right = (Map<String,String>)team.get("right");
        this.team_left_short_name = left.get("short_name");
        this.team_left_logo = left.get("logo");
        this.team_right_short_name = right.get("short_name");
        this.team_right_logo = right.get("logo");
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTournament_logo() {
        return tournament_logo;
    }

    public void setTournament_logo(String tournament_logo) {
        this.tournament_logo = tournament_logo;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getMatch_id() {
        return match_id;
    }

    public void setMatch_id(String match_id) {
        this.match_id = match_id;
    }

    public String getRound() {
        return round;
    }

    public void setRound(String round) {
        this.round = round;
    }

    public String getStatus_name() {
        return status_name;
    }

    public void setStatus_name(String status_name) {
        this.status_name = status_name;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getLeft_team_score() {
        return left_team_score;
    }

    public void setLeft_team_score(String left_team_score) {
        this.left_team_score = left_team_score;
    }

    public String getRight_team_score() {
        return right_team_score;
    }

    public void setRight_team_score(String right_team_score) {
        this.right_team_score = right_team_score;
    }

    public String getMatch_time() {
        return match_time;
    }

    public void setMatch_time(String match_time) {
        this.match_time = match_time;
    }

    public String getMatch_date() {
        return match_date;
    }

    public void setMatch_date(String match_date) {
        this.match_date = match_date;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getVictory() {
        return victory;
    }

    public void setVictory(String victory) {
        this.victory = victory;
    }

    public String getTeam_left_short_name() {
        return team_left_short_name;
    }

    public void setTeam_left_short_name(String team_left_short_name) {
        this.team_left_short_name = team_left_short_name;
    }

    public String getTeam_left_logo() {
        return team_left_logo;
    }

    public void setTeam_left_logo(String team_left_logo) {
        this.team_left_logo = team_left_logo;
    }

    public String getTeam_right_short_name() {
        return team_right_short_name;
    }

    public void setTeam_right_short_name(String team_right_short_name) {
        this.team_right_short_name = team_right_short_name;
    }

    public String getTeam_right_logo() {
        return team_right_logo;
    }

    public void setTeam_right_logo(String team_right_logo) {
        this.team_right_logo = team_right_logo;
    }

    public String getReal_data() {
        return real_data!=null?real_data:"";
    }

    public void setReal_data(String real_data) {
        this.real_data = real_data;
    }

    public String getReal_status() {
        return real_status!=null?real_status:"";
    }

    public void setReal_status(String real_status) {
        this.real_status = real_status;
    }
}
