package com.project.utility;

import org.springframework.stereotype.Component;

@Component
public class LeaveCalculator {
    public Double calculateNumberOfDays (Long startDate, Long endDate) {
        long millisecondsPerDay = 1000 * 60 * 60 * 24;

        long duration = endDate-startDate;
        return (double) duration / millisecondsPerDay;
    }
}
