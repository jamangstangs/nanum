package com.nanum.market.verification.application.signup;


import com.nanum.market.verification.domain.SignUpVerificationInfo;
import com.nanum.market.verification.domain.SignUpVerificationInfoRepository;
import org.springframework.context.ApplicationContextException;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class SignUpValidatorImpl implements SignUpValidator {
    private final SignUpVerificationInfoRepository signUpVerificationInfoRepository;

    public SignUpValidatorImpl(SignUpVerificationInfoRepository signUpVerificationInfoRepository) {
        this.signUpVerificationInfoRepository = signUpVerificationInfoRepository;
    }

    @Override
    public void checkIsVerified(String username, String verificationCode) {
        SignUpVerificationInfo verificationInfo = signUpVerificationInfoRepository.findByUsernameAndVerificationCode(username, verificationCode);

        if (!verificationInfo.isConfirmed()) {
            throw new ApplicationContextException("인증되지 않았습니다.");
        }

        if (!verificationInfo.isConfirmationValidAt(LocalDateTime.now())) {
            throw new ApplicationContextException("유효하지않은 인증정보입니다.");
        }
    }
}
