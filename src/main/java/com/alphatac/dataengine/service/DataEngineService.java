package com.alphatac.dataengine.service;

import java.util.concurrent.CountDownLatch;

public interface DataEngineService {
    void teamInfoHandle(long teamId);
    void matchInfoHandle(long teamId);
    void matchDetailHandle(long matchId,CountDownLatch count);
    void mainHandle();
}
