package com.dbbl.payment.controller;

import com.dbbl.payment.dto.UserAccountDto;
import com.dbbl.payment.model.UserAccount;
import com.dbbl.payment.security.SystemUser;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.method.annotation.AuthenticationPrincipalArgumentResolver;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.support.SessionStatus;

import javax.servlet.http.HttpSession;
import java.security.Principal;

@Controller
public class LoginController {

    @GetMapping("/login")
    public String loginPage(Model model, Principal principal){
        if(principal!=null){
            System.out.println(principal);
            return "redirect:/";
        }

        model.addAttribute("loginUser", new UserAccountDto());
        return "login";
    }

    @PostMapping("/login")
    public String doLogin(UserAccount loginUser, HttpSession session){
        UsernamePasswordAuthenticationToken authentication =
                (UsernamePasswordAuthenticationToken)
                        SecurityContextHolder.getContext().getAuthentication();
        validatePrinciple(authentication.getPrincipal());
        SystemUser loggedInUser = ((SystemUser) authentication.getPrincipal()).getUserDetails();
        session.setAttribute("loginUserName", loggedInUser.getUsername());
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
