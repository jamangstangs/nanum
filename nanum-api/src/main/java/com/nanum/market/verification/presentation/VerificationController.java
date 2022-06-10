package com.nanum.market.verification.presentation;

import com.nanum.market.verification.application.EmailVerificationEvent;
import com.nanum.market.verification.application.VerificationType;
import com.nanum.market.verification.application.signup.SignUpVerificationService;
import com.nanum.market.verification.dto.UsernameConfirmationRequest;
import com.nanum.market.verification.dto.VerificationEmailRequest;
import lombok.AllArgsConstructor;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
public class VerificationController {

    private final SignUpVerificationService signUpVerificationService;
    private final ApplicationEventPublisher publisher;

    @PostMapping("/sign-up/verifications")
    public ResponseEntity<Void> createSignUpVerificationCode(@Validated @RequestBody VerificationEmailRequest request) {
        String verificationCode = signUpVerificationService.createVerificationInfo(request);
        publisher.publishEvent(new EmailVerificationEvent(request.getUsername(), verificationCode, VerificationType.SignUp));
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/sign-up/confirm")
    public ResponseEntity<Void> confirmSignUpVerificationCode(@Validated @RequestBody UsernameConfirmationRequest request) {
        signUpVerificationService.confirmUsername(request);
        return ResponseEntity.noContent().build();
    }
}
