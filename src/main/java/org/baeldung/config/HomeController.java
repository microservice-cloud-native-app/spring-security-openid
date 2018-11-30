package org.baeldung.config;

import org.baeldung.security.OpenIdConnectUserDetails;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;

@Controller
public class HomeController {
    private final Logger logger = LoggerFactory.getLogger(getClass());

    @RequestMapping("/")
    @ResponseBody
    public final String home() {
        final String username = SecurityContextHolder.getContext().getAuthentication().getName();
        logger.info(username);
        return "Welcome, " + username;
    }

    @RequestMapping("/accounts")
    @ResponseBody
    public final String accounts() {
        final String username = SecurityContextHolder.getContext().getAuthentication().getName();

        final Object details = SecurityContextHolder.getContext().getAuthentication().getDetails();
        logger.info("details " + details);

        final Object credentials = SecurityContextHolder.getContext().getAuthentication().getCredentials();
        logger.info("credentials " + credentials);

        final Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if(principal instanceof OpenIdConnectUserDetails) {

            final OpenIdConnectUserDetails openIdConnectUserDetails = (OpenIdConnectUserDetails) principal;

            logger.info("----------openIdConnectUserDetails info------------");

            logger.info("openIdConnectUserDetails.isAccountNonExpired() " + openIdConnectUserDetails.isAccountNonExpired());
            logger.info("openIdConnectUserDetails.isAccountNonLocked() " + openIdConnectUserDetails.isAccountNonLocked());
            logger.info("openIdConnectUserDetails.isEnabled() " + openIdConnectUserDetails.isEnabled());
            logger.info("openIdConnectUserDetails.getUsername() " + openIdConnectUserDetails.getUsername());
            logger.info("openIdConnectUserDetails.getUserId() " + openIdConnectUserDetails.getUserId());


            final OAuth2AccessToken token = openIdConnectUserDetails.getToken();
            logger.info("----------token info------------");
            logger.info("tokenType() " + token.getTokenType());
            logger.info("token.getValue() " + token.getValue());
            logger.info(" getExpiresIn() " + token.getExpiresIn());
            logger.info(" getExpiration() " + token.getExpiration());
            logger.info(" token.getRefreshToken() " + token.getRefreshToken());
            logger.info(" token.getRefreshToken() " + token.getRefreshToken());


        }
        logger.info("principal " + principal);

        logger.info(username);
        return "Retrieving accounts for " + accounts.get(username);
    }

    static HashMap<String,String> accounts = new HashMap<>();

    static {
        accounts.put("ravindra.dissanayaka@citi.com","{ Savings : 1000, Checking : 2000 }");
        accounts.put("sngunjan@gmail.com","{ Savings : 150000, Checking : 222000 }");
    }

}
