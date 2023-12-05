package com.csc340.IndieDev.termsOfService;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TermsOfServiceRepository extends JpaRepository<TermsOfService, String>{
    List<TermsOfService> findByBody(String body);
}
