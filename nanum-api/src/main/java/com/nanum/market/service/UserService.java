package com.nanum.market.service;


import com.nanum.market.dto.SignupReqeustDto;
import com.nanum.market.model.User;
import com.nanum.market.repository.UserRepository;
import com.nanum.market.utils.email.EmailDomain;
import com.nanum.market.utils.email.EmailParser;
import com.nanum.market.verification.application.signup.SignUpValidator;
import org.springframework.context.ApplicationContextException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.validation.Errors;
import org.springframework.validation.FieldError;

import java.util.HashMap;
import java.util.Map;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;
    private final SignUpValidator signUpValidator;

    public UserService(UserRepository userRepository,BCryptPasswordEncoder bCryptPasswordEncoder, SignUpValidator signUpValidator ) {
        this.userRepository = userRepository;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
        this.signUpValidator = signUpValidator;
    }


    public Map<String,String> validateHandling(Errors errors){
        Map<String,String> validatorResult = new HashMap<>();
        for(FieldError error : errors.getFieldErrors()){
            String validKeyName = error.getField();
            validatorResult.put(validKeyName,error.getDefaultMessage());
        }
        return validatorResult;
    }

    public String signup(SignupReqeustDto reqeustDto){

        String encodPassword = bCryptPasswordEncoder.encode(reqeustDto.getPassword());
        reqeustDto.setPassword(encodPassword);

        String email = reqeustDto.getUsername();
        String verificationCode = reqeustDto.getVerificationCode();

        if (userRepository.existsByUsername(email)) {
            throw new ApplicationContextException("중복된 이메일입니다.");
        }
        if (!EmailDomain.has(EmailParser.parseDomainFrom(email))) {
            throw new ApplicationContextException("지스트 이메일을 이용해주세요.");
        }
        signUpValidator.checkIsVerified(email, verificationCode);
        userRepository.save(reqeustDto.toEntity());
        return "true";
    }

    public String usernameCheck(String username){
        boolean result = userRepository.existsByUsername(username);

        if (!result) {
            return "true";
        }else{
            return "false";
        }

    }


    public User findUserByEmailMethod(String userEmail) {
        return userRepository.findUsersByUsername(userEmail);
    }
    public User findByName(String sender) {
        return userRepository.findByUsername(sender);
    }


    public String emailCheck(String email) {
        boolean result = userRepository.existsByUsername(email);
        if (!result) {
            return "true";
        } else {
            return "false";
        }
    }
}
