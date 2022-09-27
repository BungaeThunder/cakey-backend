package bungae.thunder.cakey.service;

import bungae.thunder.cakey.domain.Message;
import bungae.thunder.cakey.domain.Report;
import bungae.thunder.cakey.repository.ReportRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReportService {
    private final ReportRepository reportRepository;

    @Autowired
    public ReportService(ReportRepository reportRepository){
        this.reportRepository = reportRepository;
    }

    /**
     * message 신고
     */
    public Long makeReport(Report report, Message message, String content){
        report.setMessageId(message.getId());
        report.setContents(content);

        reportRepository.save(report);
        return report.getId();

    }

    /**
     * 특정 메세지 신고 내역 조회
     */
    public List<Report> getAllReports(Long messageId){
        return reportRepository.findAllByMessageId(messageId);
    }


}
