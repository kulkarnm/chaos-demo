package com.chaosdemo.metrics.filter;

import com.chaosdemo.metrics.impl.CustomActuatorMetricService;
import com.chaosdemo.metrics.impl.ICustomActuatorMetricService;
import com.chaosdemo.metrics.impl.IMetricService;
import com.chaosdemo.metrics.impl.MetricService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.web.context.support.WebApplicationContextUtils;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Component
@WebFilter
@Order(2)
public class MetricsFilter implements Filter {
/*
    @Autowired
    private IMetricService metricService;

*/
    @Autowired
    private ICustomActuatorMetricService actMetricService;

    @Override
    public void init(final FilterConfig config) throws ServletException {
        if (actMetricService == null) {
            //metricService = (IMetricService) WebApplicationContextUtils.getRequiredWebApplicationContext(config.getServletContext()).getBean(MetricService.class);
            actMetricService = WebApplicationContextUtils.getRequiredWebApplicationContext(config.getServletContext()).getBean(CustomActuatorMetricService.class);
        }
    }

    @Override
    public void doFilter(final ServletRequest request, final ServletResponse response, final FilterChain chain) throws java.io.IOException, ServletException {
        final HttpServletRequest httpRequest = ((HttpServletRequest) request);
        actMetricService.increaseRequestCount();
        chain.doFilter(request, response);

        final int status = ((HttpServletResponse) response).getStatus();
        //metricService.increaseCount(req, status);
        actMetricService.increaseStatusWideCount(status);
    }

    @Override
    public void destroy() {

    }
} 
