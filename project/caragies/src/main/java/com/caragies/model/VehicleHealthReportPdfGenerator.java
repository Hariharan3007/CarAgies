package com.caragies.model;

import com.caragies.entitymodel.VehicleHealthReport;
import com.lowagie.text.*;
import com.lowagie.text.pdf.PdfWriter;

import org.springframework.stereotype.Component;

import java.io.ByteArrayOutputStream;


@Component
public class VehicleHealthReportPdfGenerator {

    public byte[] generatePdf(VehicleHealthReport report) {
        try {
            ByteArrayOutputStream out = new ByteArrayOutputStream();
            Document document = new Document();
            PdfWriter.getInstance(document, out);

            document.open();

            // Title
            Font titleFont = new Font(Font.HELVETICA, 18, Font.BOLD);
            document.add(new Paragraph("Vehicle Health Report", titleFont));
            document.add(new Paragraph(" "));

            // Basic info
            document.add(new Paragraph("Report ID: " + report.getId()));
            document.add(new Paragraph("Report Date: " + report.getReportDate()));
            document.add(new Paragraph("Car: " + (report.getCar() != null ? report.getCar().getId() : "N/A")));
            document.add(new Paragraph(" "));

            // Scores
            document.add(new Paragraph("Tyre Score: " + report.getTyreScore()));
            document.add(new Paragraph("Engine Score: " + report.getEngineScore()));
            document.add(new Paragraph("Brakes Score: " + report.getBrakesScore()));
            document.add(new Paragraph("Electrical Score: " + report.getElectricalScore()));
            document.add(new Paragraph("Total Cost: " + report.getFinalCost()));
            document.add(new Paragraph(" "));

            // Summary
            Font sectionTitleFont = new Font(Font.HELVETICA, 14, Font.BOLD);
            document.add(new Paragraph("Summary:", sectionTitleFont));
            document.add(new Paragraph(report.getSummary() != null ? report.getSummary() : "No summary provided"));
            document.add(new Paragraph(" "));

            // Advisory
            document.add(new Paragraph("Advisory:", sectionTitleFont));
            document.add(new Paragraph(report.getAdvisory() != null ? report.getAdvisory() : "No advisory provided"));

            document.close();

            return out.toByteArray();
        } catch (Exception e) {
            throw new RuntimeException("Error generating PDF", e);
        }
    }
}
