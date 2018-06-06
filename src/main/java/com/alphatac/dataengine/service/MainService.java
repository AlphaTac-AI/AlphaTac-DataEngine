package com.alphatac.dataengine.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.core.task.TaskExecutor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

@Service
public class MainService {
    @Autowired
    private TaskExecutor executor;
    @Autowired
    private DataEngineService dataEngineService;
    //@EventListener(ApplicationReadyEvent.class)
    @Scheduled(fixedDelay = 1000*60*60*24, initialDelay = 1000*60*2)
    public void main(){
        Runnable r = ()->dataEngineService.mainHandle();
        executor.execute(r);
    }
}
