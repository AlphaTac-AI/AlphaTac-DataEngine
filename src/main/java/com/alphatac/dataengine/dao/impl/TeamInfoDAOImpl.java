package com.alphatac.dataengine.dao.impl;

import com.alphatac.dataengine.dao.TeamInfoDAO;
import com.alphatac.dataengine.entity.player.TeamInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class TeamInfoDAOImpl implements TeamInfoDAO {
    private final String COLLECTION = "team_info";
    @Autowired
    private MongoTemplate mongoTemplate;
    @Override
    public void insertTeamInfo(TeamInfo teamInfo) {
        mongoTemplate.save(teamInfo,COLLECTION);
    }
}
