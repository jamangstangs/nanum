package com.nanum.market.controller;

import com.nanum.market.config.auth.PrincipalDetails;
import com.nanum.market.model.Heart;
import com.nanum.market.model.Message;
import com.nanum.market.service.HeartService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;

@RestController
@RequiredArgsConstructor
public class HeartController {

    private final HeartService heartService;

    @GetMapping("/boards/{boardId}/heart")
    public HashMap<String, Object> getHeart(@PathVariable Long boardId, @AuthenticationPrincipal PrincipalDetails userDetails) {
        return heartService.getHeart(boardId, userDetails.getUser().getId());
    }

    @PostMapping("/boards/{boardId}/heart")
    public ResponseEntity<Boolean> createHeart(@PathVariable Long boardId, @AuthenticationPrincipal PrincipalDetails userDetails){
        Boolean status = heartService.createHeart(boardId, userDetails.getUser().getId());
        return ResponseEntity.ok(status);
    }

}
