package bungae.thunder.cakey.report.controller;

import bungae.thunder.cakey.report.converter.ReportResponseDtoConverter;
import bungae.thunder.cakey.report.domain.Report;
import bungae.thunder.cakey.report.dto.ReportRequestDto;
import bungae.thunder.cakey.report.dto.ReportResponseDto;
import bungae.thunder.cakey.report.service.ReportService;
import java.util.List;
import java.util.stream.Collectors;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/reports")
public class ReportController {
    private final ReportService reportService;
    private final ReportResponseDtoConverter reportResponseDtoConverter;

    @Autowired
    public ReportController(ReportService reportService, ReportResponseDtoConverter reportResponseDtoConverter) {
        this.reportService = reportService;
        this.reportResponseDtoConverter = reportResponseDtoConverter;
    }


    @PostMapping()
    public ResponseEntity<Long> createReport(@RequestBody ReportRequestDto reportRequestDto) {
        return ResponseEntity.ok(reportService.createReport(reportRequestDto));
    }

    /*
    report Id 로 report받아오기
     */
    @GetMapping("/{reportId}")
    public ResponseEntity<ReportResponseDto> getReport(@PathVariable Long reportId) {
        return ResponseEntity.ok(reportResponseDtoConverter.convert(reportService.getReport(reportId)));
    }

    /*
    해당 메세지가 받은 모든 신고내역 조회하기
     */
    @GetMapping()
    public ResponseEntity<List<ReportResponseDto>> getAllReports(@RequestParam Long messageId) {
        List<Report> reports = reportService.getAllReportsByMessageId(messageId);
        List<ReportResponseDto> responses = reports.stream().map(reportResponseDtoConverter::convert).collect(Collectors.toList());
        return ResponseEntity.ok(responses);
    }
}
