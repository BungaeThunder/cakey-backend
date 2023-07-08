package bungae.thunder.cakey.report.controller;

import bungae.thunder.cakey.report.converter.ReportResponseDtoConverter;
import bungae.thunder.cakey.report.domain.Report;
import bungae.thunder.cakey.report.dto.CreateReportRequestDto;
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
    public ReportController(
            ReportService reportService, ReportResponseDtoConverter reportResponseDtoConverter) {
        this.reportService = reportService;
        this.reportResponseDtoConverter = reportResponseDtoConverter;
    }

    @PostMapping()
    public ResponseEntity<ReportResponseDto> createReport(
            @RequestBody CreateReportRequestDto createReportRequestDto) {
        Report report =
                reportService.createReport(
                        createReportRequestDto.getContents(), createReportRequestDto.getLetterId());
        return ResponseEntity.ok(reportResponseDtoConverter.convert(report));
    }

    /*
    report Id 로 report받아오기
     */
    @GetMapping("/{reportId}")
    public ResponseEntity<ReportResponseDto> getReport(@PathVariable Long reportId) {
        return ResponseEntity.ok(
                reportResponseDtoConverter.convert(reportService.getReport(reportId)));
    }

    /*
    해당 편지가 받은 모든 신고내역 조회하기
     */
    @GetMapping()
    public ResponseEntity<List<ReportResponseDto>> getAllReports(@RequestParam Long letterId) {
        List<Report> reports = reportService.getAllReportsByLetterId(letterId);
        List<ReportResponseDto> responses =
                reports.stream()
                        .map(reportResponseDtoConverter::convert)
                        .collect(Collectors.toList());
        return ResponseEntity.ok(responses);
    }
}
