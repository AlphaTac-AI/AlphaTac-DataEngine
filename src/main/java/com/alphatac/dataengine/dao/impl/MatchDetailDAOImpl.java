package com.alphatac.dataengine.dao.impl;

import com.alphatac.dataengine.dao.MatchDetailDAO;
import com.alphatac.dataengine.entity.match.MatchDetail;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

@Repository
public class MatchDetailDAOImpl implements MatchDetailDAO {
    private final String COLLECTION = "match_detail";
    @Autowired
    private MongoTemplate mongoTemplate;
    @Override
    public void insertMatchDetail(MatchDetail matchDetail) {
        mongoTemplate.save(matchDetail,COLLECTION);
    }

    @Override
    public void insertIfNotExist(MatchDetail matchDetail) {
        Query query = new Query(Criteria
                .where("match_id").is(matchDetail.getMatch_id()));
        Boolean isFound = mongoTemplate.exists(query,COLLECTION);
        if (!isFound){
            mongoTemplate.save(matchDetail,COLLECTION);
        }
    }

    @Override
    public Boolean isExist(Long matchId) {
        Query query = new Query(Criteria
                .where("match_id").is(matchId));
        return mongoTemplate.exists(query,COLLECTION);
    }
}
