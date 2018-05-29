package com.alphatac.dataengine.service;

import com.alphatac.dataengine.entity.MatchInfo;
import com.alphatac.dataengine.entity.match.MatchDetail;
import com.alphatac.dataengine.entity.player.TeamInfo;

import java.util.List;

public interface OpenDataService {
    List<TeamInfo> getSortedTeamInfo();
    TeamInfo fillPlayerInfo(TeamInfo teamInfos);
    List<MatchInfo> getSortedMathcInfo(Long teamId);
    MatchDetail getMatchDetail(Long matchId);
}
