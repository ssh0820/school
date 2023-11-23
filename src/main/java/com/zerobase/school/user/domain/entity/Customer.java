package com.zerobase.school.user.domain.entity;

import com.zerobase.school.user.domain.UserType;
import com.zerobase.school.user.dto.AuthForm;
import com.zerobase.school.user.dto.SignUpForm;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.envers.AuditOverride;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Locale;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@AuditOverride(forClass = BaseEntity.class)
public class Customer extends BaseEntity{

    @Id
    @Column(name = "customer_id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String email;

    private String name;

    private String password;

    private String phone;

    private LocalDate birth;

    private LocalDateTime verifyExpiredAt;

    private String verifycationCode;
    private boolean verify;

    private String provider; //어떤 OAuth인지(google, naver 등)
    private String provideId; // 해당 OAuth 의 key(id)

    private UserType userType;

    //private String role;

    public static Customer from(SignUpForm form){
        return Customer.builder()
                .email(form.getEmail().toLowerCase(Locale.ROOT))
                .password(form.getPassword())
                .name(form.getName())
                .birth(form.getBirth())
                .phone(form.getPhone())
                .verify(false)
                .userType(form.getUserType())
                .build();
    }

    public static Customer authFrom(AuthForm form){
        return Customer.builder()
                .email(form.getEmail().toLowerCase(Locale.ROOT))
                .password(form.getPassword())
                .name(form.getName())
                .provider(form.getProvider())
                .provideId(form.getProvideId())
                .userType(form.getUserType())
                .verifyExpiredAt(form.getVerifyExpiredAt())
                .verify(false)
                .build();
    }
}
