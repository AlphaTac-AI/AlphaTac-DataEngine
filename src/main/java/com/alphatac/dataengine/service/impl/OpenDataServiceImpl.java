package com.alphatac.dataengine.service.impl;

import com.alphatac.dataengine.entity.MatchInfo;
import com.alphatac.dataengine.entity.match.MatchDetail;
import com.alphatac.dataengine.entity.player.Player;
import com.alphatac.dataengine.entity.player.TeamInfo;
import com.alphatac.dataengine.service.OpenDataService;
import com.alphatac.dataengine.source.OpenDataClient;
import com.alphatac.dataengine.source.OpenDataClientImpl;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.task.TaskExecutor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executor;

@Service
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
    public List<Long> getSortedTeamId() {
        try {
            String jsonStr = client.getTeamInfo();
            JsonNode teamInfos = mapper.readTree(jsonStr);
            List<Long> teamIds = new ArrayList<>();
            for (JsonNode teamInfo:teamInfos){
                teamIds.add(teamInfo.get("team_id").asLong());
                if (teamIds.size()==TEAM_NUM) {
                    break;
                }
            }
            return teamIds;
        } catch (Exception e) {
            logger.error("getSortedTeamInfo failed.\n"+e.getMessage());
        }
        return null;
    }


    @Override
    public TeamInfo fillPlayerInfo(TeamInfo teamInfo) {
        try {
            String jsonStr = client.getTeamPlayerByTeamId(teamInfo.getTeam_id());
            List<Player> onDutyPlayers = new ArrayList<>();
            JsonNode arr = mapper.readTree(jsonStr);
            for (JsonNode p:arr){
                if (p.get("is_current_team_member").asBoolean()){
                    Player player = mapper.treeToValue(p,Player.class);
                    String playerDetail = client.getPlayerById(player.getAccount_id());
                    mapper.readerForUpdating(player).readValue(playerDetail);
                    onDutyPlayers.add(player);
                }
            }
            teamInfo.setPlayers(onDutyPlayers);
            return teamInfo;
        }catch (Exception e){
            logger.error("fillPlayerInfo failed.\n"+e.getMessage());
        }
        return null;
    }

    @Override
    public TeamInfo getTeamInfo(Long teamId) {
        try {
            String jsonStr = client.getTeamInfoById(teamId);
            TeamInfo teamInfo = mapper.readValue(jsonStr,TeamInfo.class);
            teamInfo = fillPlayerInfo(teamInfo);
            return teamInfo;
        }catch (Exception e){
            logger.error("fillPlayerInfo failed.\n"+e.getMessage());
        }
        return null;
    }

    @Override
    public List<MatchInfo> getSortedMatchInfo(Long teamId) {
        try {
            String jsonStr = client.getMatchInfoByTeamId(teamId);
            List<MatchInfo> matchInfos = mapper.readValue(jsonStr,
                    new TypeReference<List<MatchInfo>>(){});
            if (matchInfos.size()>TEAM_NUM){
                return new ArrayList<>(matchInfos.subList(0,TEAM_NUM));
            }else {
                return matchInfos;
            }
        }catch (Exception e){
            logger.error("getSortedMathcInfo failed.\n"+e.getMessage());
        }
        return null;
    }

    @Override
    public MatchDetail getMatchDetail(Long matchId) {
        try{
            String jsonStr = client.getMatchDetailByMatchId(matchId);
            MatchDetail matchDetail = mapper.readValue(jsonStr,MatchDetail.class);
            return matchDetail;
        }catch (Exception e){
            logger.error("getSortedMatchInfo failed.\n"+e.getMessage());
        }
        return null;
    }
}
