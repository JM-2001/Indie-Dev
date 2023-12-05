package com.csc340.IndieDev.termsOfService;

import com.csc340.IndieDev.user.User;
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

    @Getter
    @Column(name = "terms_of_service", columnDefinition = "LONGTEXT")
    private String body;

    @Column(name = "tosPublic")
    private boolean tosPublic;

    @Getter
    @ManyToOne
    @JoinColumn(name = "admin_id")
    private User lastEditedBy;

    public TermsOfService(Long tosId, String body, boolean tosPublic, User lastEditedBy) {
        this.tosId = tosId;
        this.body = body;
        this.tosPublic = tosPublic;
        this.lastEditedBy = lastEditedBy;
    }
}
