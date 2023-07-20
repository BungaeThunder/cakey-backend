package bungae.thunder.cakey.report.repository;

import bungae.thunder.cakey.report.domain.Report;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ReportJpaRepository extends JpaRepository<Report, Long> {
    List<Report> findByLetterId(Long letterId);
}
