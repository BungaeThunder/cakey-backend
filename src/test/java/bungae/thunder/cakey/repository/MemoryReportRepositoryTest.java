package bungae.thunder.cakey.repository;

import bungae.thunder.cakey.domain.Report;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class MemoryReportRepositoryTest {
    MemoryReportRepository reportRepository = new MemoryReportRepository();

    @AfterEach
    public void afterEach() {
        reportRepository.clearStore();
    }

    @Test
    public void save(){
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
}
