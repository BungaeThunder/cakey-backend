package bungae.thunder.cakey.report.service;

import static org.mockito.Mockito.mock;

import bungae.thunder.cakey.report.repository.ReportJpaRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.test.annotation.Rollback;

public class ReportServiceTest {
    ReportService reportService;
    ReportJpaRepository mokedReportRepository;

    @BeforeEach
    void beforeEach() {
        mokedReportRepository = mock(ReportJpaRepository.class);
        reportService = new ReportService(mokedReportRepository);
    }

    @Test
    @Rollback(value = false)
    void createReport() {
        //        for (int i =0; i < 10; i++) {
        //            Message message = Message.builder().contents("hello" + i).build();
        //            System.out.println("message = " + message);
        //            Message savedMessage = messageRepository.save(message);
        //            System.out.println("savedMessage = " + savedMessage);
        //        }

        // given
        //        Report report = Report.builder().messageId(123L).build();
        //
        //        // when
        //        Long reportId = reportService.createReport(report);
        //
        //        // then
        //        Report result = reportService.getReport(report.getId());
        //        assertThat(result.getId()).isEqualTo(reportId);
    }

    @Test
    void getAllReportsByMessageId() {
        //        // given
        //        Message message = Message.builder().id(123L).build();
        //
        //        Report report1 =
        // Report.builder().messageId(message.getId()).contents("hwy").build();
        //        reportService.createReport(report1);
        //        Report report2 =
        // Report.builder().messageId(message.getId()).contents("qhyyy~").build();
        //        reportService.createReport(report2);
        //
        //        // when
        //        List<Report> result = reportService.getAllReportsByMessageId(message.getId());
        //
        //        // then
        //        assertThat(result.size()).isEqualTo(2);
    }
}
