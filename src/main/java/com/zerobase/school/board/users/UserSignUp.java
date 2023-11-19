package com.zerobase.school.board.users;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@AllArgsConstructor
@NoArgsConstructor
@Builder @Getter @Setter
public class UserSignUp { @Id
    @GeneratedValue
    private String name;
    @Column
    private Long phoneNumber;
    @Column
    private String email;
    @Column
    private Long pw;
    public static User userForm(UserSignUp userSignUp){
        User communityUser= User.builder().name(userSignUp.getName())
                .phoneNumber(userSignUp.getPhoneNumber())
                .email(userSignUp.getEmail())
                .pw(userSignUp.getPw()).build();return communityUser;}}