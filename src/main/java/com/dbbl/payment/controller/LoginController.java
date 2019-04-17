package com.dbbl.payment.controller;

import com.dbbl.payment.dto.UserAccountDto;
import com.dbbl.payment.model.UserAccount;
import com.dbbl.payment.security.SystemUser;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.support.SessionStatus;

import javax.servlet.http.HttpSession;

@Controller
public class LoginController {

    @GetMapping("/login")
    public String loginPage(Model model){
        model.addAttribute("loginUser", new UserAccountDto());
        return "login";
    }

    @PostMapping("/login")
    public String doLogin(UserAccount loginUser, HttpSession session){
        System.out.println(loginUser);
        UsernamePasswordAuthenticationToken authentication =
                (UsernamePasswordAuthenticationToken)
                        SecurityContextHolder.getContext().getAuthentication();
        validatePrinciple(authentication.getPrincipal());
        SystemUser loggedInUser = ((SystemUser) authentication.getPrincipal()).getUserDetails();
        System.out.println("loggedInUser");
        System.out.println(loggedInUser);
        session.setAttribute("loginUserId", loggedInUser.getId());
        return "redirect:/";
    }

    @GetMapping("/logout")
    public String logout(SessionStatus session){
        SecurityContextHolder.getContext().setAuthentication(null);
        session.setComplete();
        return "redirect:/login";
    }

    private void validatePrinciple(Object principal) {
        if (!(principal instanceof SystemUser)) {
            throw new  IllegalArgumentException("Principal can not be null!");
        }
    }
}
