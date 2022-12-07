package bungae.thunder.cakey.report.repository;

import bungae.thunder.cakey.report.domain.Report;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class MemoryReportRepositoryTest {
    MemoryReportRepository reportRepository = new MemoryReportRepository();

    @AfterEach
    public void afterEach() {
        reportRepository.clearStore();
    }

    @Test
    public void save() {
        // given
        Report report = Report.builder().messageId(111L).contents("this is VERY bad").build();

        // when
        reportRepository.save(report);

        //then

        Report result = reportRepository.findById(report.getId());
        assertThat(result).isEqualTo(result);
    }

    @Test
    public void findAllByMessageId() {
        // given
        Report report1 = Report.builder().messageId(111L).contents("this is VERY bad").build();
        reportRepository.save(report1);

        Report report2 = Report.builder().messageId(222L).contents("this is VERYVERY bad").build();
        reportRepository.save(report2);


        Report report3 = Report.builder().messageId(111L).contents("this is VERYVERYVERY bad").build();
        reportRepository.save(report3);


        Report report4 = Report.builder().messageId(11L).contents("this is bad. you could DIE").build();
        reportRepository.save(report4);

        Report report5 = Report.builder().messageId(11L).contents("NOPE").build();
        reportRepository.save(report5);

        // when
        List<Report> result1 = reportRepository.findAllByMessageId(111L);
        List<Report> result2 = reportRepository.findAllByMessageId(11L);
        List<Report> result3 = reportRepository.findAllByMessageId(222L);


        // then
        assertThat(result1.size()).isEqualTo(2);
        assertThat(result2.size()).isEqualTo(2);
        assertThat(result3.size()).isEqualTo(1);

    }

    @Test
    public void findAll() {
        // given
        Report report1 = Report.builder().messageId(111L).contents("this is VERY bad").build();
        reportRepository.save(report1);

        Report report2 = Report.builder().messageId(222L).contents("this is VERYVERY bad").build();
        reportRepository.save(report2);


        Report report3 = Report.builder().messageId(111L).contents("this is VERYVERYVERY bad").build();
        reportRepository.save(report3);


        Report report4 = Report.builder().messageId(11L).contents("this is bad. you could DIE").build();
        reportRepository.save(report4);

        Report report5 = Report.builder().messageId(11L).contents("NOPE").build();
        reportRepository.save(report5);

        List<Report> result = reportRepository.findAll();

        //then
        assertThat(result.size()).isEqualTo(5);
    }

}
