package com.alphatac.dataengine.service.impl;

import com.alphatac.dataengine.entity.MatchInfo;
import com.alphatac.dataengine.entity.match.MatchDetail;
import com.alphatac.dataengine.entity.player.TeamInfo;
import com.alphatac.dataengine.service.OpenDataService;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.junit.Assert.*;
@SpringBootTest
@RunWith(SpringRunner.class)
public class OpenDataServiceImplTest {
    @Autowired
    private OpenDataService service;

    @Test
    public void getSortedTeamIds() {
        List<Long> teamIds = service.getSortedTeamId();
        Assert.assertNotNull(teamIds);
        Assert.assertTrue(teamIds.indexOf(15L)!=-1);
    }

    @Test
    public void fillPlayerInfo() {
        TeamInfo teamInfo = new TeamInfo();
        teamInfo.setTeam_id(15L);
        teamInfo = service.fillPlayerInfo(teamInfo);
        Assert.assertNotNull(teamInfo);
        Assert.assertNotNull(teamInfo.getPlayers());
        System.out.println(teamInfo.getPlayers().size());
    }

    @Test
    public void getSortedMathcInfo() {
        List<MatchInfo> matchInfos = service.getSortedMatchInfo(15L);
        Assert.assertNotNull(matchInfos);
        Assert.assertTrue(matchInfos.size()>1000);
        System.out.println(matchInfos.size());
    }

    @Test
    public void getMatchDetail() {
        MatchDetail matchDetail = service.getMatchDetail(3891750360L);
        Assert.assertNotNull(matchDetail);
        Assert.assertTrue(matchDetail.getPlayers().size()==10);
        Assert.assertNotNull(matchDetail.getDraft_timings());
    }

    @Test
    public void getTeamInfo() {
        TeamInfo teamInfo = service.getTeamInfo(15L);
        Assert.assertNotNull(teamInfo);
        Assert.assertNotNull(teamInfo.getPlayers());
        Assert.assertNotNull(teamInfo.getPlayers().get(0).getMmr_estimate());
    }
}