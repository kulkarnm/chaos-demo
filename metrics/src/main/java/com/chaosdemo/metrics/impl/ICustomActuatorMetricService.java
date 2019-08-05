package com.chaosdemo.metrics.impl;

import java.util.Map;

public interface ICustomActuatorMetricService {
        void increaseCount(final int status);
        Object[][] getGraphData();

}
