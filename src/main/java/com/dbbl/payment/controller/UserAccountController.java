package com.dbbl.payment.controller;

import com.dbbl.payment.constants.MessageType;
import com.dbbl.payment.dto.UserAccountDto;
import com.dbbl.payment.model.UserAccount;
import com.dbbl.payment.service.IUserAccountService;
import com.dbbl.payment.service.SelfUserOperationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.security.Principal;
import java.util.List;

@Controller
public class UserAccountController {


    @Autowired
    IUserAccountService userAccountService;

    @GetMapping("/admin/user/create")
    public String showAdminUserPage(Model model) {
        model.addAttribute("userAccountDto", new UserAccountDto());
        return "admin/admin-user-create";
    }

    @PostMapping("/admin/user/create")
    public String createAdminUser(@Valid UserAccountDto user, BindingResult bindingResult) {
        if(bindingResult.hasErrors()){
            return "admin/admin-user-create";
        }

        if(!user.getPassword().equals(user.getConfirmPassword())){
            bindingResult.rejectValue("confirmPassword","field.password", "Password not matched!");
            return "admin/admin-user-create";
        }

        UserAccount userAccount = userAccountService.createSystemUser(user);
        return "redirect:/admin/user";
    }

    @GetMapping("/admin/user")
    public String showAdminUser(Model model) {
        List<UserAccount> userAccountList = userAccountService.findAll();
        model.addAttribute("adminUsers", userAccountList);
        return "admin/admin-user-list";
    }

    @GetMapping("/admin/user/delete/{id}")
    public String deleteAdminUser(@PathVariable Long id, Principal principal, RedirectAttributes redirectAttributes) {
        try {
            userAccountService.deleteSystemUserById(id, principal.getName());
        }catch (SelfUserOperationException ex){
            redirectAttributes.addAttribute("messageType", MessageType.ERROR);
            redirectAttributes.addAttribute("message", ex.getLocalizedMessage());
            return "redirect:/admin/user";
        }
        return "redirect:/admin/user";
    }

    @GetMapping("/admin/user/activeorinactive/{id}")
    public String disableAdminUser(@PathVariable Long id, Principal principal, RedirectAttributes redirectAttributes) {
        try{
            userAccountService.disableAdminUser(id, principal.getName());
        }catch(UsernameNotFoundException e){
            redirectAttributes.addAttribute("messageType", MessageType.ERROR);
            redirectAttributes.addAttribute("message",e.getLocalizedMessage());
        }catch (SelfUserOperationException e){
            redirectAttributes.addAttribute("messageType", MessageType.ERROR);
            redirectAttributes.addAttribute("message",e.getLocalizedMessage());
        }

        return "redirect:/admin/user";
    }
}
