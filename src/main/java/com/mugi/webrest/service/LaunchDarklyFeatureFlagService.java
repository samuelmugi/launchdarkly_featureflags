package com.mugi.webrest.service;

import com.launchdarkly.sdk.LDUser;
import com.launchdarkly.sdk.server.LDClient;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

import java.time.format.DateTimeFormatter;

/**
 * @author Mugi
 */
@Component("launchdarkly")
@Primary
@Slf4j
public class LaunchDarklyFeatureFlagService  implements FeatureFlagService{
    @Value("${launchdarkly.user}")
    String launchdarklyUser;

    @Value("${erpbroker.feature.pocflag}")
    String pocflag;
     private final LDClient launchdarklyClient;
     private final DateTimeFormatter dateFormatter = DateTimeFormatter.ISO_OFFSET_DATE_TIME;

    public LaunchDarklyFeatureFlagService(LDClient launchdarklyClient) {

        this.launchdarklyClient = launchdarklyClient;
     }

    @Override
    public Boolean isErpBrokerPOCBooleanFeatureActive() {
        return launchdarklyClient.boolVariation(pocflag, getLaunchdarklyUserFromSession(), false);
    }

    private LDUser getLaunchdarklyUserFromSession() {
        return new LDUser.Builder(launchdarklyUser)
                 .build();
    }
}
