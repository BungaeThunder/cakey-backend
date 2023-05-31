package bungae.thunder.cakey.report.service;

import static org.mockito.Mockito.*;

import bungae.thunder.cakey.message.domain.Message;
import bungae.thunder.cakey.message.service.MessageService;
import bungae.thunder.cakey.report.domain.Report;
import bungae.thunder.cakey.report.repository.ReportJpaRepository;
import java.util.Arrays;
import java.util.List;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.test.annotation.Rollback;

public class ReportServiceTest {
    ReportService reportService;

    ReportJpaRepository mokedReportRepository;
    MessageService mokedMessageService;

    @BeforeEach
    void beforeEach() {
        mokedReportRepository = mock(ReportJpaRepository.class);
        mokedMessageService = mock(MessageService.class);
        reportService = new ReportService(mokedReportRepository, mokedMessageService);
    }

    @Test
    @Rollback(value = false)
    void createReport() {
        // given
        Long messageId = 1L;
        String content = "This message is inappropriate.";
        Message message = mock(Message.class);
        Report report = mock(Report.class);
        // when
        when(mokedMessageService.getMessage(messageId)).thenReturn(message);
        when(mokedReportRepository.save(any(Report.class))).thenReturn(report);

        Report result = reportService.createReport(content, messageId);

        // then
        verify(mokedMessageService).getMessage(messageId);
        verify(mokedReportRepository).save(any(Report.class));

        Assertions.assertNotNull(result);
        Assertions.assertNotNull(result.getId());
    }

    @Test
    void getAllReportsByMessageId() {
        // given
        Long messageId = 1L;
        Report report = mock(Report.class);
        List<Report> reports = Arrays.asList(report, report, report);

        // when
        when(mokedReportRepository.findByMessageId(messageId)).thenReturn(reports);

        List<Report> result = reportService.getAllReportsByMessageId(messageId);

        // then
        verify(mokedReportRepository).findByMessageId(messageId);

        Assertions.assertNotNull(result);
        Assertions.assertEquals(3, result.size());
    }
}
