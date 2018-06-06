package com.alphatac.dataengine.dao.impl;

import com.alphatac.dataengine.dao.MatchDetailDAO;
import com.alphatac.dataengine.dao.MatchInfoDAO;
import com.alphatac.dataengine.dao.TeamInfoDAO;
import com.alphatac.dataengine.entity.MatchInfo;
import com.alphatac.dataengine.entity.match.DraftTiming;
import com.alphatac.dataengine.entity.match.MatchDetail;
import com.alphatac.dataengine.entity.match.MatchPlayer;
import com.alphatac.dataengine.entity.player.MmrEstimate;
import com.alphatac.dataengine.entity.player.Player;
import com.alphatac.dataengine.entity.player.TeamInfo;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Arrays;
import java.util.List;


@SpringBootTest
@RunWith(SpringRunner.class)
public class DAOImplTest {
    @Autowired
    private MongoTemplate mongoTemplate;

    @Autowired
    public MatchInfoDAO matchInfoDAO;

    @Autowired
    public MatchDetailDAO matchDetailDAO;
    @Autowired
    public TeamInfoDAO teamInfoDAO;
    @Test
    public void insertMatchInfoTest(){
        MatchInfo matchInfo = new MatchInfo();
        matchInfo.setMatch_id(1L);
        matchInfo.setLeague_name("a");
        matchInfo.setLeagueid(2);
        matchInfo.setStart_time(3L);
        matchInfo.setTeam_id(4L);
        matchInfoDAO.insertMatchInfo(matchInfo);
        matchInfoDAO.insertIfNotExist(matchInfo);
        Query query = new Query(Criteria
        .where("match_id").is(1L));
        List<MatchInfo> m = mongoTemplate.find(query,MatchInfo.class,
                "match_info");
        Assert.assertTrue(m.size()==1);
        Assert.assertEquals(m.get(0).getLeague_name(),matchInfo.getLeague_name());

        mongoTemplate.remove(query,"match_info");
        MatchInfo tm = mongoTemplate.findOne(query,MatchInfo.class,
                "match_info");
        Assert.assertNull(tm);

    }

    @Test
    public void insertTeamInfoTest(){
        TeamInfo teamInfo = new TeamInfo();
        teamInfo.setTeam_id(1L);
        Player player = new Player();
        player.setAccount_id(2L);
        MmrEstimate mmrEstimate = new MmrEstimate();
        mmrEstimate.setEstimate(100);
        player.setMmr_estimate(mmrEstimate);
        teamInfo.setPlayers(Arrays.asList(new Player[]{player}));
        teamInfoDAO.insertTeamInfo(teamInfo);
        teamInfo.setRating(1000.0);
        teamInfoDAO.insertOrUpdate(teamInfo);
        Query query = new Query(Criteria
                .where("team_id").is(1L));
        List<TeamInfo> res = mongoTemplate.find(query,TeamInfo.class,
                "team_info");
        Assert.assertTrue(res.size()==1);
        Assert.assertEquals(res.get(0).getPlayers().get(0).getMmr_estimate().getEstimate(),
                teamInfo.getPlayers().get(0).getMmr_estimate().getEstimate());
        Assert.assertTrue(res.get(0).getRating()==1000.0);

        mongoTemplate.remove(query,"team_info");
        TeamInfo t = mongoTemplate.findOne(query,TeamInfo.class,
                "team_info");
        Assert.assertNull(t);
    }

    @Test
    public void insertMatchDetailTest(){
        MatchDetail matchDetail = new MatchDetail();
        matchDetail.setMatch_id(1L);
        MatchPlayer player = new MatchPlayer();
        player.setAccount_id(2L);
        DraftTiming draftTiming = new DraftTiming();
        draftTiming.setHero_id(3);
        matchDetail.setPlayers(Arrays.asList(new MatchPlayer[]{player}));
        matchDetail.setDraft_timings(Arrays.asList(new DraftTiming[]{draftTiming}));
        matchDetailDAO.insertMatchDetail(matchDetail);
        matchDetailDAO.insertIfNotExist(matchDetail);
        Query query = new Query(Criteria
                .where("match_id").is(1L));
        List<MatchDetail> res = mongoTemplate.find(query,MatchDetail.class,
                "match_detail");
        Assert.assertEquals(res.get(0).getPlayers().get(0).getAccount_id(),
                matchDetail.getPlayers().get(0).getAccount_id());
        Assert.assertEquals(res.get(0).getDraft_timings().get(0).getHero_id(),
                matchDetail.getDraft_timings().get(0).getHero_id());

        mongoTemplate.remove(query,"match_detail");
        MatchDetail t = mongoTemplate.findOne(query,MatchDetail.class,
                "match_detail");
        Assert.assertNull(t);
    }
}