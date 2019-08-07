package com.chaosdemo.metrics.impl;

public interface ICustomActuatorMetricService {
        void increaseCount(final int status);
        Object[][] getGraphData();

}
