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
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.CyclicBarrier;

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
    private TaskExecutor executor;
    private CyclicBarrier barrier = new CyclicBarrier(3);
    private Logger logger = LoggerFactory.getLogger(DataEngineServiceImpl.class);
    @Override
    public void teamInfoHandle(long teamId) {
        try {
            logger.info("Begin to execute teamInfoHandle");
            long starting = System.currentTimeMillis();
            TeamInfo teamInfo = openDataService.getTeamInfo(teamId);
            teamInfo = openDataService.fillPlayerInfo(teamInfo);
            teamInfoDAO.insertOrUpdate(teamInfo);
            logger.info("Finish the execution of teamInfoHandle, the cost time is "+
                    (System.currentTimeMillis()-starting));
            barrier.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (BrokenBarrierException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void matchInfoHandle(long teamId) {
        try {
            logger.info("Begin to execute matchInfoHandle");
            long starting = System.currentTimeMillis();
            List<MatchInfo> matchInfos = openDataService.getSortedMatchInfo(teamId);
            CountDownLatch countDownLatch = new CountDownLatch(matchInfos.size());
            for (MatchInfo matchInfo : matchInfos){
                matchInfoDAO.insertIfNotExist(matchInfo);
                Runnable r = ()->matchDetailHandle(matchInfo.getMatch_id(),countDownLatch);
                executor.execute(r);
            }
            logger.info("Finish the execution of matchInfoHandle, the cost time is "+
                    (System.currentTimeMillis()-starting));
            countDownLatch.await();
            barrier.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (BrokenBarrierException e) {
            e.printStackTrace();
        }

    }

    @Override
    public void matchDetailHandle(long matchId,CountDownLatch count) {
        try{
            logger.info("Begin to execute matchDetailHandle");
            long starting = System.currentTimeMillis();
            if (!matchDetailDAO.isExist(matchId)){
                MatchDetail matchDetail = openDataService.getMatchDetail(matchId);
                matchDetailDAO.insertIfNotExist(matchDetail);
            }
            logger.info("Finish the execution of matchDetailHandle, the cost time is "+
                    (System.currentTimeMillis()-starting));
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            count.countDown();
        }
    }


    @Override
    public void mainHandle() {
        List<Long> teamIds = openDataService.getSortedTeamId();
        for (Long id:teamIds){
            Runnable r1 = ()-> teamInfoHandle(id);
            Runnable r2 = ()-> matchInfoHandle(id);
            executor.execute(r1);
            executor.execute(r2);
            try {
                barrier.await();
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (BrokenBarrierException e) {
                e.printStackTrace();
            }
        }
    }
}

