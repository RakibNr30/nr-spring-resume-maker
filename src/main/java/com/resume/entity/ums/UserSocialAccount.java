package com.resume.entity.ums;

import com.resume.entity.cms.SocialAccount;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Getter @Setter
@Entity
@Table(name = "users_social_accounts", uniqueConstraints = {
        @UniqueConstraint(columnNames = {"user_id", "social_account_id"}),
        @UniqueConstraint(columnNames = {"social_account_id", "username"})
})
public class UserSocialAccount {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    private String username;

    @ManyToOne
    @JoinColumn(name = "social_account_id")
    private SocialAccount socialAccount;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @Column(name = "created_at", updatable = false)
    @CreationTimestamp
    private LocalDateTime createdAt;

    @Column(name = "updated_at")
    @UpdateTimestamp
    private LocalDateTime updatedAt;

    public boolean isEmpty() {
        return this.id == 0;
    }
}
