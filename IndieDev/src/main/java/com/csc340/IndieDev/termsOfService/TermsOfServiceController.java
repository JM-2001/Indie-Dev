package com.csc340.IndieDev.termsOfService;

import com.csc340.IndieDev.termsOfService.TermsOfService;
import com.csc340.IndieDev.termsOfService.TermsOfServiceRepository;
import com.csc340.IndieDev.termsOfService.TermsOfServiceService;

import com.csc340.IndieDev.report.Report;
import com.csc340.IndieDev.user.User;
import com.csc340.IndieDev.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
public class TermsOfServiceController {

    @Autowired
    public TermsOfServiceService tosService;

    @Autowired
    public UserService userService;

    @GetMapping("/termsOfService")
    public String viewTermsOfService(Model model) {
        String currentUsername = SecurityContextHolder.getContext().getAuthentication().getName();
        User currentUser = userService.getUserByUserName(currentUsername);

        model.addAttribute("currentUser", currentUser);

        // Add current public TOS to the model
        Optional<TermsOfService> currentPublicTOS = tosService.getCurrentPublicTermsOfService();
        model.addAttribute("currentPublicTOS", currentPublicTOS.orElse(null));

        return "termsOfService";
    }

    @GetMapping("/termsOfServiceAdmin")
    public String viewTermsOfServiceAdmin(Model model) {
        String currentUsername = SecurityContextHolder.getContext().getAuthentication().getName();
        User currentUser = userService.getUserByUserName(currentUsername);

        model.addAttribute("currentUser", currentUser);

        // Add the latest private or public TOS to the model
        Optional<TermsOfService> latestPrivateOrPublicTOS = tosService.getLatestPrivateOrPublicTerms();
        model.addAttribute("latestPrivateOrPublicTOS", latestPrivateOrPublicTOS.orElse(null));

        return "termsOfServiceAdmin";
    }


    @PostMapping("/saveTerms")
    public String saveTerms(@RequestParam String action,
                            @RequestParam String body,
                            @RequestParam String lastEditedBy,
                            @RequestParam Long userId,
                            Model model) {

        String currentUsername = SecurityContextHolder.getContext().getAuthentication().getName();
        User currentUser = userService.getUserByUserName(currentUsername);
        model.addAttribute("currentUser", currentUser);

        if ("save".equals(action)) {
            tosService.savePrivateTerms(body, currentUser);
        } else if ("publish".equals(action)) {
            tosService.publishPrivateTerms(body);
        }

        Optional<TermsOfService> latestPrivateOrPublicTOS = tosService.getLatestPrivateOrPublicTerms();
        model.addAttribute("latestPrivateOrPublicTOS", latestPrivateOrPublicTOS.orElse(null));

        return "termsOfServiceAdmin";
    }

}

