package bungae.thunder.cakey.service;

import bungae.thunder.cakey.domain.Message;
import bungae.thunder.cakey.domain.Report;
import bungae.thunder.cakey.repository.ReportRepository;
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
    public Long createReport(Report report, Message message, String content) {
        report.setMessageId(message.getId());
        report.setContents(content);

        reportRepository.save(report);
        return report.getId();
    }

    public Optional<Report> getReport(Long id){
        return reportRepository.findById(id);
    }

    /**
     * 특정 메세지 신고 내역 조회
     */
    public List<Report> getAllReportsByMessage(Message message) {
        return reportRepository.findAllByMessageId(message.getId());
    }


}
