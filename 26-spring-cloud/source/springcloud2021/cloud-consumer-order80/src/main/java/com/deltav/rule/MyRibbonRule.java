package com.deltav.rule;

import com.netflix.loadbalancer.IRule;
import com.netflix.loadbalancer.RandomRule;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author DeltaV235
 * @version 1.0
 */
@Configuration
public class MyRibbonRule {

    @Bean
    public IRule customRule() {
        return new RandomRule();
    }
}
