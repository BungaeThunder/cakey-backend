package bungae.thunder.cakey.controller;

import bungae.thunder.cakey.domain.Message;
import bungae.thunder.cakey.domain.Report;
import bungae.thunder.cakey.dto.ReportDto;
import bungae.thunder.cakey.exception.NotFoundException;
import bungae.thunder.cakey.service.MessageService;
import bungae.thunder.cakey.service.ReportService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.net.URI;
import java.util.List;
import java.util.Optional;

@Slf4j
@RestController
@RequestMapping("/reports")
public class ReportController {
    private final ReportService reportService;

    @Autowired //bean 연결
    public ReportController(ReportService reportService) {
        this.reportService = reportService;
    }

    @PostMapping()
    public ResponseEntity<Long> createReport(@RequestBody Report report) {
        return ResponseEntity.ok(reportService.createReport(report));
    }

    /*
    report Id 로 report받아오기
     */
    @GetMapping("/{reportId}")
    public ResponseEntity<Report> getReport(@PathVariable Long reportId) {
        Optional<Report> report = reportService.getReport(reportId);
        if (report.isEmpty()) {
            throw new NotFoundException();
        }
        return ResponseEntity.ok(report.get());
    }

    /*
    해당 메세지가 받은 모든 신고내역 조회하기
     */
    @GetMapping()
    public ResponseEntity<List<Report>> getAllReports(@RequestParam Long messageId) {
        return ResponseEntity.ok(reportService.getAllReportsByMessageId(messageId));

    }


}
