package com.nanum.market.verification.application;

public enum VerificationType {
    SignUp("[나눔] 회원 가입 인증 메일", "sign_up_verification.html");

    private final String subject;
    private final String template;


    VerificationType(String subject, String template) {
        this.subject = subject;
        this.template = template;
    }

    public String getSubject() {
        return subject;
    }

    public String getTemplate() {
        return template;
    }
}
