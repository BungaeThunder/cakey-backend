package bungae.thunder.cakey.report.repository;

import bungae.thunder.cakey.report.domain.Report;
import java.util.List;
import org.springframework.stereotype.Repository;

@Repository
public interface ReportRepository {
    Report save(Report report);

    Report findById(Long id);

    List<Report> findAllByLetterId(Long letterId);

    List<Report> findAll();
}
