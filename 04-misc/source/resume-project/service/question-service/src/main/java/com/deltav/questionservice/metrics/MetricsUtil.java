package com.deltav.questionservice.metrics;

import io.micrometer.core.instrument.MeterRegistry;
import io.micrometer.core.instrument.Timer;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.concurrent.TimeUnit;

@Component
@RequiredArgsConstructor
public class MetricsUtil {
    private final MeterRegistry registry;

    public Timer.Sample startTimer() {
        return Timer.start(registry);
    }

    public void stopTimer(Timer.Sample sample, String name) {
        sample.stop(Timer.builder(name)
                .tag("type", "response_time")
                .publishPercentiles(0.5, 0.95, 0.99)
                .publishPercentileHistogram()
                .register(registry));
    }

    public void recordResponseTime(String name, long timeInMillis) {
        Timer.builder(name)
                .tag("type", "response_time")
                .publishPercentiles(0.5, 0.95, 0.99)
                .publishPercentileHistogram()
                .register(registry)
                .record(timeInMillis, TimeUnit.MILLISECONDS);
    }
} 