package bungae.thunder.cakey.report.repository;

import static org.assertj.core.api.Assertions.assertThat;

import bungae.thunder.cakey.letter.domain.Letter;
import bungae.thunder.cakey.letter.repository.LetterJpaRepository;
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

    @Autowired LetterJpaRepository letterRepository;

    @Test
    @Rollback(value = false)
    public void TestReport() {
        // given
        Letter letter = Letter.builder().contents("hello").build();
        Letter savedLetter = letterRepository.save(letter);
        System.out.println("savedLetter = " + savedLetter);

        Report report = Report.builder().letter(savedLetter).contents("this is VERY bad").build();
        Report savedReport = reportRepository.save(report);

        Optional<Report> findReport = reportRepository.findById(savedReport.getId());
        assertThat(findReport.get()).isEqualTo(report);

        System.out.println("findReport = " + findReport);
    }
}
