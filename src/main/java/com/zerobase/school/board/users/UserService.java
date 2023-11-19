package com.zerobase.school.board.users;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;

import javax.transaction.Transactional;
@Transactional
@Service @RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    @PostMapping("/user/create")
    public User createUser(UserSignUp userSignUp){
        User user=UserSignUp.userForm(userSignUp);
    return userRepository.save(user);}
    public User modifyUser(Long id, UserSignUp userSignUp) {
        User saved=userRepository.findByUserId(id);
        saved.userForm(userSignUp); return saved;}
    //@Transactional public User getUser(Long id){
    //    return userRepository.findByUserId(id).get();}
}
