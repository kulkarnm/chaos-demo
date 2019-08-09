package com.chaosdemo.metrics.impl;

import io.micrometer.core.instrument.MeterRegistry;
import io.micrometer.core.instrument.search.Search;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class CustomActuatorMetricService implements ICustomActuatorMetricService {

    @Autowired
    private MeterRegistry registry;

    private final List<ArrayList<Integer>> statusMetricsByMinute;
    private final List<String> statusList;
    private static final SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm");

    public CustomActuatorMetricService() {
        super();
        statusMetricsByMinute = new ArrayList<ArrayList<Integer>>();
        statusList = new ArrayList<String>();
    }

    // API

    @Override
    public void increaseStatusWideCount(final int status) {
        String counterName = "ods-simulation.counter.status." + status;
        registry.counter(counterName).increment(1);
        if (!statusList.contains(counterName)) {
            statusList.add(counterName);
        }
    }
    @Override
    public void increaseRequestCount(){
        String counterName = "ods-simulation.request.counter";
        registry.counter(counterName).increment(1);
    }
    @Override
    public void captureResponseTime(long responseTimeMilli) {
        String counterName = "ods-simulation.counter.responsetime";
        registry.gauge(counterName,responseTimeMilli);

    }

}