package com.alphatac.dataengine.dao.impl;

import com.alphatac.dataengine.dao.MatchInfoDAO;
import com.alphatac.dataengine.entity.MatchInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

@Repository
public class MatchInfoDAOImpl implements MatchInfoDAO {
    private final String COLLECTION = "match_info";
    @Autowired
    private MongoTemplate mongoTemplate;
    @Override
    public void insertMatchInfo(MatchInfo matchInfo) {
        mongoTemplate.save(matchInfo,COLLECTION);
    }

    @Override
    public void insertIfNotExist(MatchInfo matchInfo) {
        Query query = new Query(Criteria.where("match_id").is(matchInfo.getMatch_id()).
        and("team_id").is(matchInfo.getTeam_id()));
        Boolean isFound = mongoTemplate.exists(query,COLLECTION);
        if (!isFound){
            mongoTemplate.save(matchInfo,COLLECTION);
        }
    }
}
