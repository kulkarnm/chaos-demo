package com.cheosdemo.metrics.filter;

import io.micrometer.core.annotation.Timed;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.web.context.support.WebApplicationContextUtils;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Component
@WebFilter
@Order(Ordered.HIGHEST_PRECEDENCE)
public class MetricsFilter implements Filter {
    @Autowired
    private IMetricService metricService;

    @Autowired
    private ICustomActuatorMetricService actMetricService;

    @Override
    public void init(final FilterConfig config) throws ServletException {
        if (metricService == null || actMetricService == null) {
            metricService = (IMetricService) WebApplicationContextUtils.getRequiredWebApplicationContext(config.getServletContext()).getBean("metricService");
            actMetricService = WebApplicationContextUtils.getRequiredWebApplicationContext(config.getServletContext()).getBean(CustomActuatorMetricService.class);
        }
    }

    @Override
    public void doFilter(final ServletRequest request, final ServletResponse response, final FilterChain chain) throws java.io.IOException, ServletException {
        System.out.println("#######IN Filter");
        final HttpServletRequest httpRequest = ((HttpServletRequest) request);
        final String req = httpRequest.getMethod() + " " + httpRequest.getRequestURI();

        chain.doFilter(request, response);

        final int status = ((HttpServletResponse) response).getStatus();
        metricService.increaseCount(req, status);
        actMetricService.increaseCount(status);
    }

    @Override
    public void destroy() {

    }
} 
