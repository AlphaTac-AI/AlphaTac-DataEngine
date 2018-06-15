package com.alphatac.dataengine.dao.impl;

import com.alphatac.dataengine.dao.TeamInfoDAO;
import com.alphatac.dataengine.entity.player.TeamInfo;
import com.mongodb.BasicDBObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
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

    @Override
    public void insertOrUpdate(TeamInfo teamInfo) {
        Query query = new Query(Criteria
                .where("team_id").is(teamInfo.getTeam_id()));
        Boolean isFound = mongoTemplate.exists(query,COLLECTION);
        if (!isFound){
            mongoTemplate.save(teamInfo,COLLECTION);
        }else{
            mongoTemplate.remove(query,COLLECTION);
            mongoTemplate.save(teamInfo,COLLECTION);
        }
    }

    @Override
    public TeamInfo queryById(Long id) {
        Query query = new Query(Criteria
                .where("team_id").is(id));
        return mongoTemplate.findOne(query,TeamInfo.class,COLLECTION);
    }

}
