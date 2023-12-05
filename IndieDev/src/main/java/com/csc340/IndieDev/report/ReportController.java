package com.csc340.IndieDev.report;

import com.csc340.IndieDev.user.User;
import com.csc340.IndieDev.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@Controller
@RequestMapping("/")
public class ReportController {
    @Autowired
    public ReportService service;

    @Autowired
    public UserService userService;

    @GetMapping("/dashboard/{username}/reports")
    public String viewUserReports(@PathVariable String username, Model model) {
        String currentUsername = SecurityContextHolder.getContext().getAuthentication().getName();
        User currentUser = userService.getUserByUserName(currentUsername);
        User viewedUser = userService.getUserByUserName(username);

        List<Report> reportList = service.getReportsByUserId(viewedUser.getId());

        model.addAttribute("currentUser", currentUser);
        model.addAttribute("viewedUser", viewedUser);
        model.addAttribute("reportList", reportList);

        return "reportsheet";
    }

    @PostMapping("/submitReportProfile")
    public String submitReportProfile(@RequestParam("reportedUserName") String reportedUserName,
                               @RequestParam("reasoningInput") String reasoningInput,
                               Model model) {
        String currentUsername = SecurityContextHolder.getContext().getAuthentication().getName();
        User currentUser = userService.getUserByUserName(currentUsername);

        // Assuming you have a method to retrieve the user being reported
        User reportedUser = userService.getUserByUserName(reportedUserName);


        Report report = new Report();
        report.setReporterUserId(currentUser.getId());
        report.setReportedUserId(reportedUser.getId()); // Set the reported user ID
        report.setReportDescription(reasoningInput);
        report.setReportClassification("USER");
        report.setCreationDate(LocalDateTime.now()); // Set the current date and time

        service.saveReport(report);

        // Redirect to the user's profile page or any other appropriate page
        return "redirect:/id=" + reportedUserName;
    }


    @PostMapping("/submitReportPost")
    public String submitReportPost(@RequestParam("reportedUserId") Long reportedUserId,
                                      @RequestParam("reportedPostId") Long reportedPostId,
                                      @RequestParam("reasoningInput") String reasoningInput,
                                      Model model) {
        String currentUsername = SecurityContextHolder.getContext().getAuthentication().getName();
        User currentUser = userService.getUserByUserName(currentUsername);



        Report report = new Report();
        report.setReporterUserId(currentUser.getId());
        report.setReportedUserId(reportedUserId); // Set the reported user ID
        report.setReportDescription(reasoningInput);
        report.setReportClassification("POST");
        report.setReportedPostId(reportedPostId);
        report.setCreationDate(LocalDateTime.now()); // Set the current date and time

        service.saveReport(report);

        // Redirect to the user's profile page or any other appropriate page
        return "redirect:/home";
    }


}
