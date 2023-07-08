package bungae.thunder.cakey.report.service;

import bungae.thunder.cakey.letter.domain.Letter;
import bungae.thunder.cakey.letter.service.LetterService;
import bungae.thunder.cakey.report.domain.Report;
import bungae.thunder.cakey.report.exception.ReportNotFoundException;
import bungae.thunder.cakey.report.repository.ReportJpaRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ReportService {

    private final ReportJpaRepository reportRepository;
    private final LetterService letterService;

    @Autowired
    public ReportService(ReportJpaRepository reportRepository, LetterService letterService) {
        this.reportRepository = reportRepository;
        this.letterService = letterService;
    }

    /** letter 신고 */
    public Report createReport(String content, Long letterId) {
        Letter letter = letterService.getLetter(letterId);
        Report report = Report.builder().letter(letter).contents(content).build();
        return reportRepository.save(report);
    }

    public Report getReport(Long id) {
        return reportRepository
                .findById(id)
                .orElseThrow(() -> new ReportNotFoundException("리포트가 존재하지 않습니다"));
    }

    /** 특정 편지 신고 내역 조회 */
    public List<Report> getAllReportsByLetterId(Long letterId) {
        return reportRepository.findByLetterId(letterId);
    }
}
