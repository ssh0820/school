package com.zerobase.school.board.users;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RequiredArgsConstructor
@RestController
public class UserController {//[1]
    private final UserService userService;
    private final UserRepository userRepository;
    @PostMapping("/user/create")
    public User createUser(@RequestBody UserSignUp userSignUp){
        return userService.createUser(userSignUp);}
    @PutMapping("/modify/{id}")
    public User modifyUser(@PathVariable Long id, @RequestBody UserSignUp userSignUp){
         return userService.modifyUser(id,userSignUp);}
    @PostMapping("/email/exception")
    public ResponseEntity<?> EmailException//[2]
            (@RequestBody @Valid UserSignUp userSignUp, Errors errors){
    if(userRepository.emailCount(userSignUp.getEmail())!=0){
        throw new EmailError("이미 있는 이메일");}
    User communityUser=User.builder().name(userSignUp.getName())
            .phoneNumber(userSignUp.getPhoneNumber())
            .email(userSignUp.getEmail())
            .pw(userSignUp.getPw()).build();
    userRepository.save(communityUser);
    return ResponseEntity.ok().build();}
        //=User.builder().email(userSignUp.getEmail())
            //.name(userSignUp.getName()).build();
}