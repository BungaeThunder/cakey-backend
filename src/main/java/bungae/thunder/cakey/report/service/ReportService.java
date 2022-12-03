package bungae.thunder.cakey.report.service;

import bungae.thunder.cakey.message.domain.Message;
import bungae.thunder.cakey.report.domain.Report;
import bungae.thunder.cakey.report.repository.ReportRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ReportService {
    private final ReportRepository reportRepository;

    @Autowired
    public ReportService(ReportRepository reportRepository) {
        this.reportRepository = reportRepository;
    }

    /**
     * message 신고
     */
    public Long createReport(Report report) {

        reportRepository.save(report);
        return report.getId();
    }

    public Optional<Report> getReport(Long id){
        return reportRepository.findById(id);
    }

    /**
     * 특정 메세지 신고 내역 조회
     */
    public List<Report> getAllReportsByMessageId(Long messageId) {
        return reportRepository.findAllByMessageId(messageId);
    }


}
