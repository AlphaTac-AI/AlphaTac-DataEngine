package com.alphatac.dataengine.source;

import com.alphatac.dataengine.entity.match_schedule.MatchScheduleEntity;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import javax.jms.Message;
import javax.jms.TextMessage;
import java.util.List;

import static org.junit.Assert.*;

@SpringBootTest
@RunWith(SpringRunner.class)
public class VPGameLiveGameSpiderTest {
    @Autowired
    private VPGameLiveGameSpider spider;
    @Autowired
    private ObjectMapper mapper;
    @Autowired
    private JmsTemplate jmsTemplate;
    @Test
    public void getLiveGame() throws Exception {
        List<MatchScheduleEntity> matches = spider.getLiveGame();
        Assert.assertNotNull(matches);
        Assert.assertTrue(matches.size()>0);
    }

    @Test
    public void handleTest() throws Exception{
        Thread.sleep(10000);
        spider.handle();
        Message message = jmsTemplate.receive("live_match.topic");
        Assert.assertNotNull(message);
        System.out.print(((TextMessage)message).getText());
    }
}