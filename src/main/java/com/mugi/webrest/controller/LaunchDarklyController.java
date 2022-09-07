package com.mugi.webrest.controller;

import com.mugi.webrest.service.LaunchDarklyFeatureFlagService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author Mugi
 */
@Slf4j
@RestController
@RequestMapping(value = "/LaunchDarkly")
public class LaunchDarklyController {

    private final LaunchDarklyFeatureFlagService launchDarklyFeatureFlagService;

    public LaunchDarklyController(LaunchDarklyFeatureFlagService launchDarklyFeatureFlagService) {
        this.launchDarklyFeatureFlagService = launchDarklyFeatureFlagService;
    }

    @GetMapping(path = {"/"})
    ModelAndView chooseUser() {
        return new ModelAndView("/choose-user.html");
    }

    @GetMapping(path = {"/poc"})
    ResponseEntity alice() {
        var poc=launchDarklyFeatureFlagService.isErpBrokerPOCBooleanFeatureActive();
        log.info("erp broker poc value is ["+poc+"]");
         return new ResponseEntity("erp broker poc value is ["+poc+"]", HttpStatus.OK);
    }
}
