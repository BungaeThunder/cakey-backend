package bungae.thunder.cakey.report.service;

import static org.assertj.core.api.Assertions.assertThat;

import bungae.thunder.cakey.message.domain.Message;
import bungae.thunder.cakey.report.domain.Report;
import bungae.thunder.cakey.report.repository.MemoryReportRepository;
import java.util.List;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class ReportServiceTest {
    ReportService reportService;
    MemoryReportRepository reportRepository;

    @BeforeEach
    void beforeEach() {
        reportRepository = new MemoryReportRepository();
        reportService = new ReportService(reportRepository);
    }

    @AfterEach
    void afterEach() {
        reportRepository.clearStore();
    }

    @Test
    void createReport() {
        // given
        Report report = Report.builder().messageId(123L).build();

        // when
        Long reportId = reportService.createReport(report);

        // then
        Report result = reportService.getReport(report.getId());
        assertThat(result.getId()).isEqualTo(reportId);
    }

    @Test
    void getAllReportsByMessageId() {
        // given
        Message message = Message.builder().build();

        Report report1 = Report.builder().messageId(message.getId()).contents("hwy").build();
        reportService.createReport(report1);
        Report report2 = Report.builder().messageId(message.getId()).contents("qhyyy~").build();
        reportService.createReport(report2);

        // when
        List<Report> result = reportService.getAllReportsByMessageId(message.getId());

        // then
        assertThat(result.size()).isEqualTo(2);
    }
}
