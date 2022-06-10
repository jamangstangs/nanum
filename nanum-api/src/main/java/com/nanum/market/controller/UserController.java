package com.nanum.market.controller;


import com.nanum.market.dto.SignupReqeustDto;
import com.nanum.market.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.Map;

@RestController
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/signups")
    public ResponseEntity signup(@RequestBody @Valid SignupReqeustDto reqeustDto, Errors errors)throws Exception{
        /* 에러 메시지 반환 */
        if(errors.hasErrors()){
            Map<String, String> error = userService.validateHandling(errors);
            return new ResponseEntity(error, HttpStatus.INSUFFICIENT_STORAGE);
        }

        String emailCheck = userService.emailCheck(reqeustDto.getUsername());

        if(emailCheck == "false"){
            Map<String, String> emailResult = new HashMap<>();
            emailResult.put("email", "이메일 중복입니다");
            return new ResponseEntity(emailResult,HttpStatus.INSUFFICIENT_STORAGE);

        }else{
            userService.signup(reqeustDto);
        }

        return ResponseEntity.ok().build();
    }

    @GetMapping("/signups/username/{username}")
    public ResponseEntity username(@PathVariable String username){
        return ResponseEntity.ok(userService.usernameCheck(username));
    }

    @GetMapping("/signups/email/{email}")
    public ResponseEntity email(@PathVariable String email){
        return ResponseEntity.ok(userService.emailCheck(email));
    }


}
