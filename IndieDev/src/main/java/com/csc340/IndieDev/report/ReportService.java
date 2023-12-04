package com.csc340.IndieDev.report;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReportService {
    @Autowired
    ReportRepository repo;

    public List<Report> getAllReports() {
        return repo.findAll();
    }

    public List<Report> getReportsByUserId(Long userId) {
        return repo.findReportByReportedUserId(userId);
    }

    public void saveReport(Report report) {
        repo.save(report);
    }
}
