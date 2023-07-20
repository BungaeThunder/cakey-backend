package bungae.thunder.cakey.report.service;

import static org.mockito.Mockito.*;

import bungae.thunder.cakey.letter.domain.Letter;
import bungae.thunder.cakey.letter.service.LetterService;
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

    ReportJpaRepository mockedReportRepository;
    LetterService mockedLetterService;

    @BeforeEach
    void beforeEach() {
        mockedReportRepository = mock(ReportJpaRepository.class);
        mockedLetterService = mock(LetterService.class);
        reportService = new ReportService(mockedReportRepository, mockedLetterService);
    }

    @Test
    @Rollback(value = false)
    void createReport() {
        // given
        Long letterId = 1L;
        String content = "This letter is inappropriate.";
        Letter letter = mock(Letter.class);
        Report report = mock(Report.class);
        // when
        when(mockedLetterService.getLetter(letterId)).thenReturn(letter);
        when(mockedReportRepository.save(any(Report.class))).thenReturn(report);

        Report result = reportService.createReport(content, letterId);

        // then
        verify(mockedLetterService).getLetter(letterId);
        verify(mockedReportRepository).save(any(Report.class));

        Assertions.assertNotNull(result);
        Assertions.assertNotNull(result.getId());
    }

    @Test
    void getAllReportsByLetterId() {
        // given
        Long letterId = 1L;
        Report report = mock(Report.class);
        List<Report> reports = Arrays.asList(report, report, report);

        // when
        when(mockedReportRepository.findByLetterId(letterId)).thenReturn(reports);

        List<Report> result = reportService.getAllReportsByLetterId(letterId);

        // then
        verify(mockedReportRepository).findByLetterId(letterId);

        Assertions.assertNotNull(result);
        Assertions.assertEquals(3, result.size());
    }
}
