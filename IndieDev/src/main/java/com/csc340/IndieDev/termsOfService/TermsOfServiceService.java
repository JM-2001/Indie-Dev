package com.csc340.IndieDev.termsOfService;

import com.csc340.IndieDev.termsOfService.TermsOfService;
import com.csc340.IndieDev.termsOfService.TermsOfServiceRepository;

import com.csc340.IndieDev.user.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TermsOfServiceService {
    @Autowired
    TermsOfServiceRepository tosRepo;

    public List<TermsOfService> getPublicTermsOfService() {
        return tosRepo.findByTosPublicIsTrue();
    }

    public List<TermsOfService> getPrivateTermsOfService() {
        return tosRepo.findByTosPublicIsFalse();
    }

    public void updatePrivateTermsOfService(String body, User admin) {
        tosRepo.findByBodyAndTosPublicIsFalse(body).ifPresent(privateTerms -> {
            privateTerms.setLastEditedBy(admin);
            // You might want to add more logic here for updating other fields
            tosRepo.save(privateTerms);
        });
    }

    public void publishPrivateTerms(String body) {
        tosRepo.findByBodyAndTosPublicIsFalse(body).ifPresent(privateTerms -> {
            privateTerms.setTosPublic(true);
            tosRepo.save(privateTerms);
        });
    }

    public Optional<TermsOfService> getCurrentPublicTermsOfService() {
        return tosRepo.findFirstByTosPublicTrueOrderByTosIdDesc();
    }

    public Optional<TermsOfService> getLatestPrivateOrPublicTerms() {
        Optional<TermsOfService> latestPrivate = tosRepo.findFirstByTosPublicIsFalseOrderByTosIdDesc().stream().findFirst();
        return latestPrivate.or(() -> tosRepo.findFirstByTosPublicTrueOrderByTosIdDesc());
    }

    public void savePrivateTerms(String body, User admin) {
        TermsOfService privateTerms = new TermsOfService();
        privateTerms.setBody(body);
        privateTerms.setLastEditedBy(admin);
        privateTerms.setTosPublic(false); // This is a private term
        tosRepo.save(privateTerms);
    }
}
