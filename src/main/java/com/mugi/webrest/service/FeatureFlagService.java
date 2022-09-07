package com.mugi.webrest.service;
/**
 * @author Mugi
 */
public interface FeatureFlagService {

    /**
     * A simple Boolean feature flag that returns either true or false.
     */
    Boolean isErpBrokerPOCBooleanFeatureActive();
}
