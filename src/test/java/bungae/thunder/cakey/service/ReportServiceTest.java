package bungae.thunder.cakey.service;

import bungae.thunder.cakey.domain.Message;
import bungae.thunder.cakey.domain.Report;
import bungae.thunder.cakey.repository.MemoryReportRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;


public class ReportServiceTest {
    ReportService reportService;
    MemoryReportRepository reportRepository;

    @BeforeEach
    void beforeEach(){
        reportRepository = new MemoryReportRepository();
        reportService = new ReportService(reportRepository);
    }

    @AfterEach
    void afterEach(){
        reportRepository.clearStore();
    }

    @Test
    void createReport() {
        //given
        Report report = Report.builder().build();
        Message message = Message.builder().id(123L).build();

        //when
        Long reportId = reportService.createReport(report, message);

        //then
        Report result = reportService.getReport(report.getId()).get();
        assertThat(result.getId()).isEqualTo(reportId);

    }

    @Test
    void getAllReportsByMessageId() {
        // given
        Message message = Message.builder().id(123L).build();

        Report report1 = Report.builder().messageId(message.getId()).contents("hwy").build();
        reportService.createReport(report1, message);
        Report report2 = Report.builder().messageId(message.getId()).contents("qhyyy~").build();
        reportService.createReport(report2, message);

        // when
        List<Report> result = reportService.getAllReportsByMessageId(message.getId());
//        System.out.println("result = " + result);

        // then
        assertThat(result.size()).isEqualTo(2);


    }
}

