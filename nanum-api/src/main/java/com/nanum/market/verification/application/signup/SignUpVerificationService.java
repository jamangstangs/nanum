package com.nanum.market.verification.application.signup;

import com.nanum.market.repository.UserRepository;
import com.nanum.market.utils.email.EmailDomain;
import com.nanum.market.utils.email.EmailParser;
import com.nanum.market.verification.application.VerificationCodeGenerator;
import com.nanum.market.verification.domain.SignUpVerificationInfo;
import com.nanum.market.verification.domain.SignUpVerificationInfoRepository;
import com.nanum.market.verification.dto.UsernameConfirmationRequest;
import com.nanum.market.verification.dto.VerificationEmailRequest;
import org.springframework.context.ApplicationContextException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@Service
public class SignUpVerificationService {

    private final SignUpVerificationInfoRepository signUpVerificationInfoRepository;
    private final VerificationCodeGenerator verificationCodeGenerator;
    private final UserRepository userRepository;

    public SignUpVerificationService(SignUpVerificationInfoRepository signUpVerificationInfoRepository, VerificationCodeGenerator verificationCodeGenerator, UserRepository userRepository) {
        this.signUpVerificationInfoRepository = signUpVerificationInfoRepository;
        this.verificationCodeGenerator = verificationCodeGenerator;
        this.userRepository = userRepository;
    }

    @Transactional
    public String createVerificationInfo(VerificationEmailRequest request) {
        String username = request.getUsername();
        if (userRepository.existsByUsername(username)) {
            throw new ApplicationContextException("중복된 이메일입니다.");
        }
        if (!EmailDomain.has(EmailParser.parseDomainFrom(username))) {
            throw new ApplicationContextException("지스트 이메일을 이용해주세요.");
        }
        signUpVerificationInfoRepository.deleteByUsername(username);

        String code = verificationCodeGenerator.generate();
        signUpVerificationInfoRepository.save(new SignUpVerificationInfo(username, code));
        return code;
    }

    @Transactional
    public void confirmUsername(UsernameConfirmationRequest request) {
        String username = request.getUsername();
        String verificationCode = request.getVerificationCode();

        SignUpVerificationInfo info = signUpVerificationInfoRepository.findByUsernameAndVerificationCode(username, verificationCode);

        if (!info.isValidToConfirm(LocalDateTime.now())) {
            throw new ApplicationContextException("만료된 코드입니다.");
        }

        if (info.isConfirmed()) {
            throw new ApplicationContextException("이미 인증된 코드입니다.");
        }
        info.confirm();
    }
}
