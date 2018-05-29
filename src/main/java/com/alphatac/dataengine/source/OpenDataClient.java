package com.alphatac.dataengine.source;

public interface OpenDataClient {
    String getTeamInfo();
    String getTeamPlayerByTeamId(long teamId);
    String getPlayerById(long playerId);
    String getMatchInfoByTeamId(long teamId);
    String getMatchDetailByMatchId(long matchId);
}
