package bungae.thunder.cakey.report.repository;

import bungae.thunder.cakey.report.domain.Report;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ReportRepository {
    Report save(Report report);

    Report findById(Long id);

    List<Report> findAllByMessageId(Long messageId);

    List<Report> findAll();

}
