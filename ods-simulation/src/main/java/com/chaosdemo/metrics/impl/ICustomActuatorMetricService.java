package com.chaosdemo.metrics.impl;

public interface ICustomActuatorMetricService {
        void increaseStatusWideCount(final int status);
        void increaseRequestCount();
        public void captureResponseTime(long responseTimeMilli);
        void clearRegistry();

}
