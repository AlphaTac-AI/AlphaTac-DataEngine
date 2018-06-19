package com.alphatac.dataengine.source;
import com.alphatac.dataengine.entity.match_schedule.MatchScheduleEntity;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.micrometer.core.instrument.MeterRegistry;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.retry.support.RetryTemplate;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Component
public class VPGameLiveGameSpider {
    final private Logger logger = LoggerFactory.getLogger(VPGameLiveGameSpider.class);
    @Autowired
    private RestTemplate restTemplate;
    @Autowired
    private RetryTemplate retryTemplate;
    @Autowired
    private MeterRegistry meterRegistry;
    @Autowired
    private ObjectMapper mapper;
    @Autowired
    private JmsTemplate jmsTemplate;
    final private String url = "http://www.vpgame.com/gateway/v1/match/schedule/pc_index";
    final private String topic = "live_match.topic";

    public void handle(){
        jmsTemplate.convertAndSend(topic,"testmsg");
    }
    public List<MatchScheduleEntity> getLiveGame(){
        try{
            ResponseEntity<String> response = retryTemplate.execute(
                    context -> restTemplate.getForEntity(url, String.class)
            );
            meterRegistry.counter("VPLIVE_API").increment();
            return mappingJsonToEntity(response.getBody());
        }catch (Exception e){
            logger.error("After three times reties, getLiveGame from opendata failed"
                    +e.getMessage());
        }
        return null;
    }

    private List<MatchScheduleEntity> mappingJsonToEntity(String jsonStr) throws IOException {
        try {
            JsonNode json = mapper.readTree(jsonStr);
            JsonNode liveMatchList = json.get("body").get("list");
            List<MatchScheduleEntity> matchScheduleEntityList = new ArrayList<>();
            for (JsonNode node : liveMatchList){
                matchScheduleEntityList.add(mapper.convertValue(node,MatchScheduleEntity.class));
            }
            return matchScheduleEntityList;
        } catch (Exception e) {
                logger.error("Jackson transfer map to json string error.",e);
        }
        return null;
    }
}
