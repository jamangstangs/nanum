package com.nanum.market.verification.application.signup;

public interface SignUpValidator {
    void checkIsVerified(String username, String verificationCode);
}
