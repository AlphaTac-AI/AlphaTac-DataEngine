package com.alphatac.dataengine.dao.impl;

import com.alphatac.dataengine.dao.MatchInfoDAO;
import com.alphatac.dataengine.entity.MatchInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
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
}
