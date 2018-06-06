package com.alphatac.dataengine.dao;

import com.alphatac.dataengine.entity.player.TeamInfo;

public interface TeamInfoDAO {
    void insertTeamInfo(TeamInfo teamInfo);
    void insertOrUpdate(TeamInfo teamInfo);
}
