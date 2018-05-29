package com.alphatac.dataengine.dao.impl;

import com.alphatac.dataengine.dao.MatchDetailDAO;
import com.alphatac.dataengine.entity.match.MatchDetail;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
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
}
