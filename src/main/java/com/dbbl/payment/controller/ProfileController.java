package com.dbbl.payment.controller;

import com.dbbl.payment.model.Profile;
import com.dbbl.payment.model.UserAccount;
import com.dbbl.payment.repository.ProfileRepository;
import com.dbbl.payment.repository.UserAccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.security.Principal;

@Controller
public class ProfileController {

    @Autowired
    ProfileRepository profileRepository;

    @Autowired
    UserAccountRepository userAccountRepository;


    @GetMapping("/profile")
    public String showProfile(Model model, Principal principal) {
        String username = principal.getName();
        UserAccount account = userAccountRepository.getUserAccountByEmail(username);
        Profile profile = account.getProfileId();
        model.addAttribute("profile", profile);
        return "profile/profile";
    }

    @GetMapping("/profile/edit")
    public String editProfile(Model model, Principal principal) {
        String username = principal.getName();
        UserAccount account = userAccountRepository.getUserAccountByEmail(username);
        Profile profile = account.getProfileId();
        model.addAttribute("profile", profile);
        return "profile/edit";
    }

    @PostMapping("/profile")
    public String saveProfile(Profile profile){
        Profile profileRes = profileRepository.save(profile);
        return "redirect:/profile";
    }
}
