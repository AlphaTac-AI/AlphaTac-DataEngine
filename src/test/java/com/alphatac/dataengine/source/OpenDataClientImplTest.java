package com.alphatac.dataengine.source;

import com.alphatac.dataengine.entity.MatchInfo;
import com.alphatac.dataengine.entity.match.MatchDetail;
import com.alphatac.dataengine.entity.player.MmrEstimate;
import com.alphatac.dataengine.entity.player.Player;
import com.alphatac.dataengine.entity.player.TeamInfo;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.retry.support.RetryTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.IOException;
import java.util.List;

import static org.junit.Assert.*;

@SpringBootTest
@RunWith(SpringRunner.class)
public class OpenDataClientImplTest {
    @Autowired
    private OpenDataClient client;
    @Autowired
    private ObjectMapper mapper;


    @Test
    public void getTeamInfo() throws IOException {
        String teamInfoJson = client.getTeamInfo();
        List<TeamInfo> teamInfo = mapper.readValue(teamInfoJson,
                new TypeReference<List<TeamInfo>>(){});
        Assert.assertNotNull(teamInfo);
        System.out.println(teamInfo.size());
        Assert.assertNotNull(teamInfo.get(0));

    }

    @Test
    public void getTeamPlayerByTeamId()throws IOException {
        String jsonStr = client.getTeamPlayerByTeamId(15);
        Player[] players = mapper.readValue(jsonStr,Player[].class);
        Assert.assertNotNull(players);
        System.out.println(players.length);
        Assert.assertNotNull(players[0]);
    }

    @Test
    public void getPlayerById()throws IOException {
        String jsonStr = client.getPlayerById(98878010);
        Player player = mapper.readValue(jsonStr,Player.class);
        Assert.assertNotNull(player);
        System.out.println(player.getMmr_estimate().getEstimate());
    }

    @Test
    public void getMatchInfoByTeamId()throws IOException {
        String jsonStr = client.getMatchInfoByTeamId(15);
        List<MatchInfo> matchInfos = mapper.readValue(jsonStr,
                new TypeReference<List<MatchInfo>>(){});
        Assert.assertNotNull(matchInfos);
        System.out.println(matchInfos.size());
        Assert.assertNotNull(matchInfos.get(0));
    }

    @Test
    public void getMatchDetailByMatchId()throws IOException {
        String jsonStr = client.getMatchDetailByMatchId(3891750360L);
        MatchDetail matchDetails = mapper.readValue(jsonStr,MatchDetail.class);
        Assert.assertNotNull(matchDetails);
        System.out.println(matchDetails.getRadiant_win());
    }
}