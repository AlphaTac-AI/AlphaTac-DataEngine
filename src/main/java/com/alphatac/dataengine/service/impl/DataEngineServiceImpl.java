package com.alphatac.dataengine.service.impl;

import com.alphatac.dataengine.dao.MatchDetailDAO;
import com.alphatac.dataengine.dao.MatchInfoDAO;
import com.alphatac.dataengine.dao.TeamInfoDAO;
import com.alphatac.dataengine.entity.MatchInfo;
import com.alphatac.dataengine.entity.match.MatchDetail;
import com.alphatac.dataengine.entity.player.TeamInfo;
import com.alphatac.dataengine.service.DataEngineService;
import com.alphatac.dataengine.service.OpenDataService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.task.TaskExecutor;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

@Service
public class DataEngineServiceImpl implements DataEngineService {
    @Autowired
    private TeamInfoDAO teamInfoDAO;
    @Autowired
    private MatchInfoDAO matchInfoDAO;
    @Autowired
    private MatchDetailDAO matchDetailDAO;
    @Autowired
    private OpenDataService openDataService;
    @Autowired
    private ThreadPoolTaskExecutor executor;
    private CyclicBarrier barrier = new CyclicBarrier(2);
    private Logger logger = LoggerFactory.getLogger(DataEngineServiceImpl.class);

    @Override
    public void teamInfoHandle(long teamId) {
        try {
            logger.info("Begin to execute teamInfoHandle");
            long starting = System.currentTimeMillis();
            TeamInfo teamInfo = openDataService.getTeamInfo(teamId);
            teamInfo = openDataService.fillPlayerInfo(teamInfo);
            if (teamInfo.getPlayers() != null && teamInfo.getPlayers().size() >= 5) {
                teamInfoDAO.insertOrUpdate(teamInfo);
            }
            logger.info("Finish the execution of teamInfoHandle, the cost time is " +
                    (System.currentTimeMillis() - starting));
        } catch (Exception e){
            logger.error("Exception occurs in teamInfoHandle. ",e);
        }
    }

    @Override
    public void matchInfoHandle(long teamId) {
        try {
            logger.info("Begin to execute matchInfoHandle");
            long starting = System.currentTimeMillis();
            List<MatchInfo> matchInfos = openDataService.getSortedMatchInfo(teamId);
            for (MatchInfo matchInfo : matchInfos) {
                matchInfoDAO.insertIfNotExist(matchInfo);
                matchDetailHandle(matchInfo.getMatch_id());
            }
            logger.info("Finish the execution of matchInfoHandle, the cost time is " +
                    (System.currentTimeMillis() - starting));
            barrier.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (BrokenBarrierException e) {
            e.printStackTrace();

        }

    }

    @Override
    public void matchDetailHandle(long matchId) {
        try {
            logger.info("Begin to execute matchDetailHandle");
            long starting = System.currentTimeMillis();
            if (!matchDetailDAO.isExist(matchId)) {
                MatchDetail matchDetail = openDataService.getMatchDetail(matchId);
                matchDetailDAO.insertIfNotExist(matchDetail);
            }
            logger.info("Finish the execution of matchDetailHandle for match"+matchId+", the cost time is " +
                    (System.currentTimeMillis() - starting));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    @Override
    public void mainHandle() {
        try {
            List<Long> teamIds = openDataService.getSortedTeamId();
            for (Long id : teamIds) {
                teamInfoHandle(id);
                if (isActiveTeam(id)) {
                    Runnable r2 = () -> matchInfoHandle(id);
                    executor.execute(r2);
                    barrier.await(20, TimeUnit.HOURS);
                }
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (BrokenBarrierException e) {
            e.printStackTrace();
        } catch (TimeoutException e) {
            logger.error("mainHandle exited because of barrier timeout. ", e);
            executor.shutdown();
        }
    }

    private Boolean isActiveTeam(Long id) {
        try {
            TeamInfo teamInfo = teamInfoDAO.queryById(id);
            if (teamInfo == null || teamInfo.getPlayers() == null || teamInfo.getPlayers().size() == 0) {
                return false;
            } else {
                return true;
            }
        } catch (Exception e) {
            logger.error("Judge isActiveTeam error", e);
        }
        return false;
    }
}

