package com.caragies.service;

import com.caragies.entitymodel.VehicleHealthReport;
import com.caragies.model.VehicleHealthReportPdfGenerator;
import com.caragies.repositories.HealthReport;
import lombok.RequiredArgsConstructor;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import jakarta.mail.internet.MimeMessage;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class VehicleHealthReportEmailService {

    private final HealthReport reportRepository;
    private final VehicleHealthReportPdfGenerator pdfGenerator;
    private final JavaMailSender mailSender;


    public void sendReportEmail(VehicleHealthReport report) {

        String email = report.getCar().getUsers().getEmail();
        System.out.println(email);

        if (email == null) {
            throw new IllegalStateException("Owner email not found for car " + report.getCar().getId());
        }

        byte[] pdfBytes = pdfGenerator.generatePdf(report);

        try {
            MimeMessage message = mailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message, true);

            helper.setTo(email);
            helper.setSubject("Your Vehicle Health Report #" + report.getId());
            helper.setText("Dear customer,\n\nPlease find attached your vehicle health report.\n\nRegards,\nCarAgies");

            helper.addAttachment("vehicle_health_report_" + report.getId() + ".pdf",
                    new org.springframework.core.io.ByteArrayResource(pdfBytes));

            mailSender.send(message);
        } catch (Exception e) {
            throw new RuntimeException("Failed to send email", e);
        }
    }
//    @Scheduled(cron = "0 35 13 * * *")
   @Scheduled(fixedRate = 1000000000)
   // @Scheduled(cron = "0 0 9 * * *")  // every day at 09:00
    public void sendDailyReports() {
        LocalDate yesterday = LocalDate.now().minusDays(1);
        LocalDateTime start = yesterday.atStartOfDay();
        LocalDateTime end = yesterday.atTime(LocalTime.MAX);

        List<VehicleHealthReport> reports =
              //  reportRepository.findByReportDateBetween(start, end);
reportRepository.findAll();
        for (VehicleHealthReport report : reports) {
            try {
                sendReportEmail(report);
            } catch (Exception e) {

                System.err.println("Failed to send report id " + report.getId() + ": " + e.getMessage());
            }
        }
    }
}
