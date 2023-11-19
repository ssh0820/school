package com.zerobase.school.board.users;
import lombok.*;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.time.LocalDateTime;

@Getter @Setter @NoArgsConstructor
@AllArgsConstructor @Builder @Entity
@EntityListeners(AuditingEntityListener.class)
public class User {@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column
    private String name;
    @Column
    private Long phoneNumber;
    @Column
    private String email;
    @Column
    private Long pw;
    @Column
    private LocalDateTime createTime;
    @Column
    private LocalDateTime modifyTime;
    @Column
    private LocalDateTime deleteTime;
    public static User userForm(UserSignUp userSignUp){
        User communityUser= User.builder().name(userSignUp.getName())
                .phoneNumber(userSignUp.getPhoneNumber())
                .email(userSignUp.getEmail())
                .pw(userSignUp.getPw())
                .createTime(LocalDateTime.now()).build();
    return communityUser;}
}