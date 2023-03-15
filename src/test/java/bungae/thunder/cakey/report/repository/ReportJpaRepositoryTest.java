package bungae.thunder.cakey.report.repository;

import static org.assertj.core.api.Assertions.assertThat;

import bungae.thunder.cakey.message.domain.Message;
import bungae.thunder.cakey.message.repository.MessageJpaRepository;
import bungae.thunder.cakey.report.domain.Report;
import java.util.Optional;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

@SpringBootTest
@Transactional
class ReportJpaRepositoryTest {

    @Autowired ReportJpaRepository reportRepository;

    @Autowired MessageJpaRepository messageRepository;

    @Test
    @Rollback(value = false)
    public void TestReport() {
        // given
        Message message = Message.builder().contents("hello").build();
        Message savedMessage = messageRepository.save(message);
        System.out.println("savedMessage = " + savedMessage);

        Report report = Report.builder().message(savedMessage).contents("this is VERY bad").build();
        Report savedReport = reportRepository.save(report);

        Optional<Report> findReport = reportRepository.findById(savedReport.getId());
        assertThat(findReport.get()).isEqualTo(report);

        System.out.println("findReport = " + findReport);
    }
}
