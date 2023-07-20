package bungae.thunder.cakey.report.controller;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import bungae.thunder.cakey.letter.domain.Letter;
import bungae.thunder.cakey.report.converter.ReportResponseDtoConverter;
import bungae.thunder.cakey.report.domain.Report;
import bungae.thunder.cakey.report.service.ReportService;
import org.json.JSONObject;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.context.WebApplicationContext;

// ref
// https://goodteacher.tistory.com/257
@WebMvcTest(controllers = ReportController.class)
@Import(ReportResponseDtoConverter.class)
class ReportControllerTest {

    @Autowired WebApplicationContext ctx;

    @Autowired private MockMvc mvc;
    @Autowired ReportResponseDtoConverter reportResponseDtoConverter;

    @MockBean ReportService reportService;

    @Test
    @DisplayName("create a report")
    public void createReport() throws Exception {
        Letter letter = mock(Letter.class);
        Report report = mock(Report.class);

        given(reportService.createReport(any(), any())).willReturn(report);
        given(report.getContents()).willReturn("this is very bad!");
        given(report.getLetter()).willReturn(letter);
        given(report.getId()).willReturn(0L);
        given(letter.getId()).willReturn(0L);

        JSONObject createReportRequestDto = new JSONObject();
        createReportRequestDto.put("letterId", 123L);
        createReportRequestDto.put("contents", "this is very bad!");

        mvc.perform(
                        post("/reports")
                                .content(createReportRequestDto.toString())
                                .contentType(MediaType.APPLICATION_JSON)
                                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andDo(print());
    }
}
