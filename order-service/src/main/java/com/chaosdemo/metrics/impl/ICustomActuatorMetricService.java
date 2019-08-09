package com.chaosdemo.metrics.impl;

public interface ICustomActuatorMetricService {
        void increaseStatusWideCount(final int status);
        void increaseRequestCount();
       // Object[][] getGraphData();

}
