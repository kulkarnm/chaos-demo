package com.cheosdemo.metrics.filter;

import java.util.Map;

public interface ICustomActuatorMetricService {
        void increaseCount(final int status);
        Object[][] getGraphData();

}
