package com.bk.spring.security.oauth2.social.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.security.oauth2.core.user.DefaultOAuth2User;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.util.SerializationUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collections;
import java.util.Map;

@RestController
public class UsersController {

    Logger LOG = LoggerFactory.getLogger(UsersController.class);

    @Autowired ObjectMapper objectMapper;

    @GetMapping("/user")
    public Map<String, Object> user(@AuthenticationPrincipal OAuth2User principal)  throws Exception{

        SecurityContext context = SecurityContextHolder.getContext();
        OAuth2AuthenticationToken authToken = (OAuth2AuthenticationToken)context.getAuthentication();
        String authClientID = authToken.getAuthorizedClientRegistrationId();
        String principalID = (authToken.getPrincipal()).getName();
        LOG.info(principal.getClass().getName() + " " + principal.getClass().getCanonicalName());
        LOG.debug("Calling User Details Endpoint.");
        String json = objectMapper.writeValueAsString(principal.getAttributes());
        LOG.info("OAuth JSON: " + json);
        for(String attributeName : principal.getAttributes().keySet())
        {
            LOG.debug(attributeName);
        }
        String userName = authClientID.equalsIgnoreCase("google") ? principal.getAttribute("name") : (authClientID.equalsIgnoreCase("github") ? principal.getAttribute("login") : principalID);
        return Collections.singletonMap("name", userName + " (" + principalID + ")");
    }
}
