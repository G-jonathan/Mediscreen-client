package com.openclassroomsProject.Mediscreenclient.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Controller class for handling home page related operations
 *
 * @author jonathan GOUVEIA
 * @version 1.0
 */
@Controller
public class HomeController {
    private static final Logger LOGGER = LoggerFactory.getLogger(HomeController.class);

    /**
     * Displays the home page.
     *
     * @return The view name for the home page.
     */
    @RequestMapping("/")
    public String home() {
        LOGGER.info("[CONTROLLER]-> call method : home");
        return "home";
    }
}