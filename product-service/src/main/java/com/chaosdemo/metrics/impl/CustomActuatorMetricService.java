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

    private final List<String> statusList;
    private static final SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm");

    public CustomActuatorMetricService() {
        super();
        statusList = new ArrayList<String>();
    }

    // API

    @Override
    public void increaseStatusWideCount(final int status) {
        String counterName = "product.counter.status." + status;
        System.out.println("()()()()response status: " + status);

        registry.counter(counterName).increment(1);
/*
        if (!statusList.contains(counterName)) {
            statusList.add(counterName);
        }
*/
    }

    @Override
    public void increaseRequestCount() {
        String counterName = "product.counter.request";
        registry.counter(counterName).increment(1);

/*
        if (!statusList.contains(counterName)) {
            statusList.add(counterName);
        }
*/
    }

    @Override
    public void captureResponseTime(long responseTimeMilli) {
        String counterName = "product.counter.responsetime";
        registry.gauge(counterName,responseTimeMilli);
    }

    @Override
    public void clearRegistry() {
        registry.clear();
    }


/*
    @Override
    public Object[][] getGraphData() {
        final Date current = new Date();
        final int colCount = statusList.size() + 1;
        final int rowCount = statusMetricsByMinute.size() + 1;
        final Object[][] result = new Object[rowCount][colCount];
        result[0][0] = "Time";

        int j = 1;
        for (final String status : statusList) {
            result[0][j] = status;
            j++;
        }

        for (int i = 1; i < rowCount; i++) {
            result[i][0] = dateFormat.format(new Date(current.getTime() - (60000 * (rowCount - i))));
        }

        List<Integer> minuteOfStatuses;
        for (int i = 1; i < rowCount; i++) {
            minuteOfStatuses = statusMetricsByMinute.get(i - 1);
            for (j = 1; j <= minuteOfStatuses.size(); j++) {
                result[i][j] = minuteOfStatuses.get(j - 1);
            }
            while (j < colCount) {
                result[i][j] = 0;
                j++;
            }
        }
        return result;
    }

    // Non - API

    @Scheduled(fixedDelay = 60000)
    private void exportMetrics() {
        final ArrayList<Integer> statusCount = new ArrayList<Integer>();
        for (final String status : statusList) {
            Search search = registry.find(status);
            if (search != null) {
*/
/*
                Counter counter = search.counter();
                statusCount.add(counter != null ? ((int) counter.count()) : 0);
                registry.remove(counter);
*//*

            } else {
                statusCount.add(0);
            }
        }
        statusMetricsByMinute.add(statusCount);
    }
*/
}