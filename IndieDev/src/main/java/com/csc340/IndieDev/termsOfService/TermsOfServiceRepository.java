package com.csc340.IndieDev.termsOfService;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface TermsOfServiceRepository extends JpaRepository<TermsOfService, Long>{

    List<TermsOfService> findByTosPublicIsTrue();

    List<TermsOfService> findByTosPublicIsFalse();

    Optional<TermsOfService> findByBodyAndTosPublicIsFalse(String body);

    Optional<TermsOfService> findFirstByTosPublicTrueOrderByTosIdDesc();

    Optional<TermsOfService> findFirstByTosPublicIsFalseOrderByTosIdDesc();

}
