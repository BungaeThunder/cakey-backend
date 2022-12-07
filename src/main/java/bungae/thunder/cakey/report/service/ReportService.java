package bungae.thunder.cakey.report.service;

import bungae.thunder.cakey.report.domain.Report;
import bungae.thunder.cakey.report.exception.ReportNotFoundException;
import bungae.thunder.cakey.report.repository.ReportRepository;
import java.util.List;
import java.util.Objects;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ReportService {
    private final ReportRepository reportRepository;

    @Autowired
    public ReportService(ReportRepository reportRepository) {
        this.reportRepository = reportRepository;
    }

    /** message 신고 */
    public Long createReport(Report report) {

        reportRepository.save(report);
        return report.getId();
    }

    public Report getReport(Long id) {
        Report report = reportRepository.findById(id);
        if (Objects.isNull(report)) {
            throw new ReportNotFoundException("레포트가 존재하지 않습니다");
        }
        return report;
    }

    /** 특정 메세지 신고 내역 조회 */
    public List<Report> getAllReportsByMessageId(Long messageId) {
        return reportRepository.findAllByMessageId(messageId);
    }
}
