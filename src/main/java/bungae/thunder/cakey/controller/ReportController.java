package bungae.thunder.cakey.controller;

import bungae.thunder.cakey.domain.Report;
import bungae.thunder.cakey.exception.NotFoundException;
import bungae.thunder.cakey.service.MessageService;
import bungae.thunder.cakey.service.ReportService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.util.List;
import java.util.Optional;

@Slf4j
@RestController
@RequestMapping("/reports")
public class ReportController {
    private final ReportService reportService;

    @Autowired //bean 연결
    public ReportController(ReportService reportService, MessageService messageService) {
        this.reportService = reportService;
    }

    @GetMapping("/{reportId}")
    public ResponseEntity<Report> getReport(@PathVariable Long reportId) {
        Optional<Report> report = reportService.getReport(reportId);
        if (report.isEmpty()) {
            throw new NotFoundException();
        }
        return ResponseEntity.ok(report.get());
    }

    @GetMapping()
    public ResponseEntity<List<Report>> getAllReports(@RequestParam Long messageId) {
        return ResponseEntity.ok(reportService.getAllReportsByMessageId(messageId));

    }


}
