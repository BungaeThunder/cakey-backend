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
    private final MessageService messageService;

    @Autowired //bean 연결
    public ReportController(ReportService reportService, MessageService messageService) {
        this.reportService = reportService;
        this.messageService = messageService;
    }

    @PostMapping()
    public ResponseEntity<Long> createReport(@RequestBody ReportDto reportDto) {
        Report report = Report.builder().messageId(reportDto.getMessageId()).contents(reportDto.getContents()).build();
        Optional<Message> message = messageService.getMessage(reportDto.getMessageId());

        if (message.isEmpty()) {
            String err = String.format("No such message(%d)", reportDto.getMessageId());
            throw new NotFoundException(err);
        }
        Long reportId = reportService.createReport(report, message.get());
        return ResponseEntity.created(URI.create("/reports" + reportId)).body(reportId);
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
