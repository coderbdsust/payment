package com.dbbl.payment.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class HomeController {

    @RequestMapping(value = {"/", "/index", "/home"}, method = RequestMethod.GET)
    private String home() {
        return "home";
    }

    @RequestMapping(value = {"/icons"}, method = RequestMethod.GET)
    private String icons() {
        return "icons";
    }

    @RequestMapping(value = {"/map"}, method = RequestMethod.GET)
    private String map() {
        return "map";
    }

    @RequestMapping(value = {"/notifications"}, method = RequestMethod.GET)
    private String notifications() {
        return "notifications";
    }

    @RequestMapping(value = {"/tables"}, method = RequestMethod.GET)
    private String tables() {
        return "tables";
    }

    @RequestMapping(value = {"/typography"}, method = RequestMethod.GET)
    private String typograhpy() {
        return "typography";
    }

    @RequestMapping(value = {"/user"}, method = RequestMethod.GET)
    private String user() {
        return "user";
    }
}
