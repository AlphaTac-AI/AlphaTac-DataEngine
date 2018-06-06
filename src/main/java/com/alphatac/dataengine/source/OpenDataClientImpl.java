package com.alphatac.dataengine.source;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.micrometer.core.instrument.MeterRegistry;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.http.ResponseEntity;
import org.springframework.retry.support.RetryTemplate;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.text.MessageFormat;

@Component
@PropertySource("classpath:datasource.properties")
public class OpenDataClientImpl implements OpenDataClient {
    final private Logger logger = LoggerFactory.getLogger(OpenDataClientImpl.class);

    @Autowired
    private RestTemplate restTemplate;
    @Autowired
    private ObjectMapper mapper;
    @Value("${datasource.opendata.url}")
    private String URL;
    @Value("${datasource.opendata.api.teams}")
    private String TEAM_API;
    @Value("${datasource.opendata.api.teamwithid}")
    private String TEAM_WITH_ID_API;
    @Value("${datasource.opendata.api.teamplayers}")
    private String TEAM_PLAYER_API;
    @Value("${datasource.opendata.api.teammatches}")
    private String TEAM_MATCH_API;
    @Value("${datasource.opendata.api.player}")
    private String PLAYER_API;
    @Value("${datasource.opendata.api.match}")
    private String MATCH_API;
@Autowired
private MeterRegistry meterRegistry;

    @Autowired
    private RetryTemplate retryTemplate;

    @Override
    public String getTeamInfo() {
        try {
            String url = URL+TEAM_API;
            ResponseEntity<String> response = retryTemplate.execute(
                    context -> restTemplate.getForEntity(url, String.class)
            );
            meterRegistry.counter("TEAM_API").increment();
            meterRegistry.counter("OPENDOTA_API").increment();
            return response.getBody();
        }catch (Exception e){
            if (e instanceof JsonProcessingException) {
                logger.error("Jackson transfer map to json string error.");
            }else {
                logger.error("After three times reties, getTeamInfo from opendata failed"
                        +e.getMessage());
            }
        }
        return null;
    }

    @Override
    public String getTeamPlayerByTeamId(long teamId) {
        try {
            String url = MessageFormat.format(URL+TEAM_PLAYER_API,teamId);
            ResponseEntity<String> response = retryTemplate.execute(
                    context -> restTemplate.getForEntity(url, String.class)
            );
            meterRegistry.counter("TEAM_PLAYER_API").increment();
            meterRegistry.counter("OPENDOTA_API").increment();
            return response.getBody();
        }catch (Exception e){
            if (e instanceof JsonProcessingException) {
                logger.error("Jackson transfer map to json string error.");
            }else {
                logger.error("After three times reties, getTeamPlayerByTeamId for teamId "+
                        teamId+"from opendata failed./n"+ e.getMessage());
            }
        }
        return null;
    }

    @Override
    public String getPlayerById(long playerId) {
        try {
            String url = MessageFormat.format(URL+PLAYER_API,playerId);
            ResponseEntity<String> response = retryTemplate.execute(
                    context -> restTemplate.getForEntity(url, String.class)
            );
            meterRegistry.counter("PLAYER_API").increment();
            meterRegistry.counter("OPENDOTA_API").increment();
            return response.getBody();
        }catch (Exception e){
            if (e instanceof JsonProcessingException) {
                logger.error("Jackson transfer map to json string error.");
            }else {
                logger.error("After three times reties, getPlayerById for playerId "+
                        playerId+"from opendata failed./n"+ e.getMessage());
            }
        }
        return null;
    }

    @Override
    public String getMatchInfoByTeamId(long teamId) {
        try {
            String url = MessageFormat.format(URL+TEAM_MATCH_API,teamId);
            ResponseEntity<String> response = retryTemplate.execute(
                    context -> restTemplate.getForEntity(url, String.class)
            );
            meterRegistry.counter("TEAM_MATCH_API").increment();
            meterRegistry.counter("OPENDOTA_API").increment();
            return response.getBody();
        }catch (Exception e){
            if (e instanceof JsonProcessingException) {
                logger.error("Jackson transfer map to json string error.");
            }else {
                logger.error("After three times reties, getMatchInfoByTeamId for teamId "+
                        teamId+"from opendata failed./n"+ e.getMessage());
            }
        }
        return null;
    }


    @Override
    public String getMatchDetailByMatchId(long matchId) {
        try {
            String url = MessageFormat.format(URL+MATCH_API,matchId);
            ResponseEntity<String> response = retryTemplate.execute(
                    context -> restTemplate.getForEntity(url, String.class)
            );
            meterRegistry.counter("MATCH_API").increment();
            meterRegistry.counter("OPENDOTA_API").increment();
            return response.getBody();
        }catch (Exception e){
            if (e instanceof JsonProcessingException) {
                logger.error("Jackson transfer map to json string error.");
            }else {
                logger.error("After three times reties, getMatchDetailByMatchId for matchId "+
                        matchId+"from opendata failed./n"+ e.getMessage());
            }
        }
        return null;
    }

    @Override
    public String getTeamInfoById(long teamId) {
        try {
            String url = MessageFormat.format(URL+TEAM_WITH_ID_API,teamId);
            ResponseEntity<String> response = retryTemplate.execute(
                    context -> restTemplate.getForEntity(url, String.class)
            );
            meterRegistry.counter("TEAM_WITH_ID_API").increment();
            meterRegistry.counter("OPENDOTA_API").increment();
            return response.getBody();
        }catch (Exception e){
            if (e instanceof JsonProcessingException) {
                logger.error("Jackson transfer map to json string error.");
            }else {
                logger.error("After three times reties, getTeamInfoById for teamId "+
                        teamId+"from opendata failed./n"+ e.getMessage());
            }
        }
        return null;
    }
}
