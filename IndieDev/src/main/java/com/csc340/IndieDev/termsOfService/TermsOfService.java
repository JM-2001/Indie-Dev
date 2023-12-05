package com.csc340.IndieDev.termsOfService;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "tos")
@NoArgsConstructor
@Getter
@Setter
public class TermsOfService {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long tosId;

    @Column(name = "terms_of_service")
    private String body;

    public TermsOfService(String body) {
        this.body = body;
    }
}
