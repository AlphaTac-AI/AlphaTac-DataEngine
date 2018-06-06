package com.alphatac.dataengine.dao;

import com.alphatac.dataengine.entity.match.MatchDetail;

public interface MatchDetailDAO {
    void insertMatchDetail(MatchDetail matchDetail);
    void insertIfNotExist(MatchDetail matchDetail);
    Boolean isExist(Long matchId);
}
