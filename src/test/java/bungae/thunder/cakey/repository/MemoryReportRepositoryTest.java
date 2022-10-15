package bungae.thunder.cakey.repository;

import bungae.thunder.cakey.domain.Report;
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
        Report report = new Report();
        report.setMessageId(111L);
        report.setContents("this is VERY bad");

        // when
        reportRepository.save(report);

        //then

        Report result = reportRepository.findById(report.getId()).get();
        assertThat(result).isEqualTo(result);
    }

    @Test
    public void findAllByMessageId() {
        // given
        Report report1 = new Report();
        report1.setMessageId(111L);
        report1.setContents("this is VERY bad");
        reportRepository.save(report1);

        Report report2 = new Report();
        report2.setMessageId(222L);
        report2.setContents("this is VERYVERY bad");
        reportRepository.save(report2);


        Report report3 = new Report();
        report3.setMessageId(111L);
        report3.setContents("this is VERYVERYVERY bad");
        reportRepository.save(report3);


        Report report4 = new Report();
        report4.setMessageId(11L);
        report4.setContents("this is bad. you could DIE");
        reportRepository.save(report4);

        Report report5 = new Report();
        report5.setMessageId(11L);
        report5.setContents("NOPE");
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
        Report report1 = new Report();
        report1.setMessageId(111L);
        report1.setContents("this is VERY bad");
        reportRepository.save(report1);

        Report report2 = new Report();
        report2.setMessageId(222L);
        report2.setContents("this is VERYVERY bad");
        reportRepository.save(report2);


        Report report3 = new Report();
        report3.setMessageId(111L);
        report3.setContents("this is VERYVERYVERY bad");
        reportRepository.save(report3);


        Report report4 = new Report();
        report4.setMessageId(11L);
        report4.setContents("this is bad. you could DIE");
        reportRepository.save(report4);

        Report report5 = new Report();
        report5.setMessageId(11L);
        report5.setContents("NOPE");
        reportRepository.save(report5);

        List<Report> result = reportRepository.findAll();

        //then
        assertThat(result.size()).isEqualTo(5);
    }

}
