package com.caragies.service;
import com.caragies.entitymodel.VerificationCode;
import com.caragies.repositories.VerificationCodeRepository;
import org.springframework.stereotype.Service;
import java.time.Instant;
import java.time.Duration;
import java.util.Random;
@Service
public class VerificationService {
    private final VerificationCodeRepository codeRepo;
    private final EmailService emailService;
    public VerificationService(VerificationCodeRepository codeRepo, EmailService emailService) {
        this.codeRepo = codeRepo;
        this.emailService = emailService;
    }

    private String generate6Digit() {
        Random r = new Random();
        int n = 100000 + r.nextInt(900000);
        return String.valueOf(n);
    }

    public void createAndSendCode(String email) {
        // remove old codes (optional)
        codeRepo.deleteByEmail(email);
        String code = generate6Digit();
        Instant now = Instant.now();
        Instant expires = now.plus(Duration.ofMinutes(10)); // expiry 10 minutes
        VerificationCode vc = VerificationCode.builder()
                .email(email)
                .code(code)
                .createdAt(now)
                .expiresAt(expires)
                .used(false)
                .build();
        codeRepo.save(vc);
        String text = "Your verification code is: " + code + "\nThis code expires at: " + expires.toString();
        emailService.sendPlainText(email, "Your verification code", text);
    }

    public boolean verifyCode(String email, String code) {
        var opt = codeRepo.findTopByEmailAndCodeOrderByCreatedAtDesc(email, code);
        if (opt.isEmpty())
            return false;
        VerificationCode vc = opt.get();
        if (vc.isUsed())
            return false;
        if (vc.getExpiresAt().isBefore(Instant.now()))
            return false;
        vc.setUsed(true);
        codeRepo.save(vc);
        return true;
    }
}
