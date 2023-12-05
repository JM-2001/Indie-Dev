package com.csc340.IndieDev.termsOfService;

import com.csc340.IndieDev.termsOfService.TermsOfService;
import com.csc340.IndieDev.termsOfService.TermsOfServiceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class TermsOfServiceService {

    @Autowired
    TermsOfServiceRepository tosRepo;

    public List<TermsOfService> getBody(String body){
        return tosRepo.findByBody(body);
    }
    public void updateTos(TermsOfService tos) {
        TermsOfService existing = (TermsOfService) tosRepo.findByBody(tos.getBody());

        if (existing != null) {
            if (tos.getBody() != null) {
                existing.setBody(tos.getBody());
            }

            tosRepo.save(existing);
        }
    }


}
