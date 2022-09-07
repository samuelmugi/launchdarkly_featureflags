package com.mugi.webrest.config;

import com.launchdarkly.sdk.server.LDClient;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.annotation.PreDestroy;
import java.io.IOException;
/**
 * @author mugi
 */
@Slf4j
@Configuration
public class LaunchDarklyConfiguration {

    private LDClient launchdarklyClient;
    @Value("${launchdarkly.sdkKey}")
    String sdkKey;
    @Bean
    public LDClient launchdarklyClient() {
        log.info("sdkKey>>>>"+sdkKey);
        this.launchdarklyClient = new LDClient(sdkKey);
        return this.launchdarklyClient;
    }

    @PreDestroy
    public void destroy() throws IOException {
        this.launchdarklyClient.close();
    }
}
