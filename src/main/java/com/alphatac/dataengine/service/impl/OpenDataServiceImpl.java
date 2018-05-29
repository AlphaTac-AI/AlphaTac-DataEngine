package com.alphatac.dataengine.service.impl;

import com.alphatac.dataengine.entity.MatchInfo;
import com.alphatac.dataengine.entity.match.MatchDetail;
import com.alphatac.dataengine.entity.player.TeamInfo;
import com.alphatac.dataengine.service.OpenDataService;
import com.alphatac.dataengine.source.OpenDataClient;
import com.alphatac.dataengine.source.OpenDataClientImpl;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
@PropertySource("classpath:datasource.properties")
public class OpenDataServiceImpl implements OpenDataService {
    @Autowired
    private OpenDataClient client;
    @Autowired
    private ObjectMapper mapper;

    final private Logger logger = LoggerFactory.getLogger(OpenDataClientImpl.class);

    @Value("${datasource.teams.num}")
    private Integer TEAM_NUM;
    @Value("${datasource.team.matches.num}")
    private Integer MATCH_NUM;
    @Override
    public List<TeamInfo> getSortedTeamInfo() {
        try {
            String jsonStr = client.getTeamInfo();
            List<TeamInfo> teamInfos = mapper.readValue(jsonStr,
                    new TypeReference<List<TeamInfo>>(){});
            return new ArrayList<>(teamInfos.subList(0, TEAM_NUM));
        } catch (Exception e) {
            logger.error("getSortedTeamInfo failed.\n"+e.getMessage());
        }
        return null;
    }

    @Override
    public TeamInfo fillPlayerInfo(TeamInfo teamInfos) {
        return null;
    }

    @Override
    public List<MatchInfo> getSortedMathcInfo(Long teamId) {
        return null;
    }

    @Override
    public MatchDetail getMatchDetail(Long matchId) {
        return null;
    }
}
