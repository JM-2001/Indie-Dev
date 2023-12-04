package com.csc340.IndieDev.report;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ReportRepository extends JpaRepository<Report, Long> {

    List<Report> findReportByReportedUserId(Long UserId);
}
