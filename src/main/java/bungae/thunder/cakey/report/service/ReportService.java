package bungae.thunder.cakey.report.service;

import bungae.thunder.cakey.message.domain.Message;
import bungae.thunder.cakey.message.service.MessageService;
import bungae.thunder.cakey.report.domain.Report;
import bungae.thunder.cakey.report.dto.ReportRequestDto;
import bungae.thunder.cakey.report.exception.ReportNotFoundException;
import bungae.thunder.cakey.report.repository.ReportJpaRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ReportService {

    private final ReportJpaRepository reportRepository;
    private final MessageService messageService;

    @Autowired
    public ReportService(ReportJpaRepository reportRepository, MessageService messageService) {
        this.reportRepository = reportRepository;
        this.messageService = messageService;
    }

    /** message 신고 */
    public Long createReport(ReportRequestDto reportRequestDto) {
        Message message = messageService.getMessage(reportRequestDto.getMessageId());
        Report report =
                Report.builder().message(message).contents(reportRequestDto.getContents()).build();
        reportRepository.save(report);
        return report.getId();
    }

    public Report getReport(Long id) {
        return reportRepository
                .findById(id)
                .orElseThrow(() -> new ReportNotFoundException("리포트가 존재하지 않습니다"));
    }

    /** 특정 메세지 신고 내역 조회 */
    public List<Report> getAllReportsByMessageId(Long messageId) {
        return reportRepository.findByMessageId(messageId);
    }
}
