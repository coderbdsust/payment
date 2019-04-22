package com.dbbl.payment.controller;

import com.dbbl.payment.dto.ProfileViewDto;
import com.dbbl.payment.model.Profile;
import com.dbbl.payment.model.UserAccount;
import com.dbbl.payment.repository.ProfileRepository;
import com.dbbl.payment.repository.UserAccountRepository;
import com.dbbl.payment.service.ProfileService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import javax.naming.Binding;
import javax.validation.Valid;
import java.lang.reflect.Field;
import java.security.Principal;

@Controller
public class ProfileController {


    @Autowired
    ProfileService profileService;


    @GetMapping("/profile")
    public String showProfile(Model model, Principal principal) {
        String username = principal.getName();
        ProfileViewDto profileViewDto = profileService.getUserAccountProfile(username);
        model.addAttribute("profileViewDto", profileViewDto);
        return "pages/profile/profile";
    }

    @GetMapping("/profile/edit")
    public String editProfile(Model model, Principal principal) {
        String username = principal.getName();
        ProfileViewDto profileViewDto = profileService.getUserAccountProfile(username);
        model.addAttribute("profileViewDto", profileViewDto);
        return "pages/profile/edit";
    }

    @PostMapping("/profile")
    public String saveProfile(@Valid ProfileViewDto profileViewDto, BindingResult errors){
        if(errors.hasErrors()){
            return "pages/profile/edit";
        }
        Profile profileRes = profileService.saveOrUpdateUserAccountProfile(profileViewDto);
        return "redirect:/profile";
    }
}
