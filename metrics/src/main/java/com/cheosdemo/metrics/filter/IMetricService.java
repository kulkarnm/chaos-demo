package com.cheosdemo.metrics.filter;

import java.util.Map;

public interface IMetricService {
        void increaseCount(final String request, final int status);

        Map getFullMetric();

        Map getStatusMetric();

        Object[][] getGraphData();

}
