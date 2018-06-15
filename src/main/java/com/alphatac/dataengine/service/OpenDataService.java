package com.alphatac.dataengine.service;

import com.alphatac.dataengine.entity.MatchInfo;
import com.alphatac.dataengine.entity.match.MatchDetail;
import com.alphatac.dataengine.entity.player.TeamInfo;

import java.util.List;

public interface OpenDataService {
    List<Long> getSortedTeamId();
    TeamInfo fillPlayerInfo(TeamInfo teamInfo);
    TeamInfo getTeamInfo(Long teamId);
    List<MatchInfo> getSortedMatchInfo(Long teamId);
    MatchDetail getMatchDetail(Long matchId);

}
