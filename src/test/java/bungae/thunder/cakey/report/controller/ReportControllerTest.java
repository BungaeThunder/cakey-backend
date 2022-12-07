package bungae.thunder.cakey.report.controller;

import bungae.thunder.cakey.message.service.MessageService;
import bungae.thunder.cakey.report.domain.Report;
import bungae.thunder.cakey.report.service.ReportService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.hamcrest.Matchers;
import org.json.JSONObject;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.context.WebApplicationContext;


import java.util.*;

import static org.hamcrest.core.StringContains.containsString;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.content;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


// ref
// https://goodteacher.tistory.com/257
@WebMvcTest(controllers = ReportController.class)
class ReportControllerTest {
    @Autowired
    WebApplicationContext ctx;

    private MockMvc mvc;

    @MockBean
    ReportService reportService;

    @MockBean
    MessageService messageService;

    @Autowired
    private ObjectMapper objectMapper;


    @BeforeEach
    public void test() {
        mvc = MockMvcBuilders.webAppContextSetup(ctx).build();
    }

    @Test
    @DisplayName("get A report by report id")
    public void getReportById() throws Exception {
        String contents = "This is something";
        Report report = Report.builder().id(123L).messageId(321L).contents(contents).build();
        Report report2 = Report.builder().id(321L).messageId(321L).contents(contents).build();

        given(reportService.getReport(321L)).willReturn(Optional.ofNullable(report));

        mvc.perform(get("/reports/{reportId}", 321L))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.content().string(Matchers.containsString(contents)))
                .andDo(print());
    }


    @Test
    @DisplayName("get all reports by message id")
    public void getAllReportByMessageId() throws Exception {
        Report report1 = Report.builder().id(1L).messageId(321L).contents("hello").build();
        Report report2 = Report.builder().id(2L).messageId(123L).contents("bro").build();
        Report report3 = Report.builder().id(3L).messageId(123L).contents("goot to see you").build();

        List<Report> reports = new ArrayList<>();
        Collections.addAll(reports, report2, report3);

        given(reportService.getAllReportsByMessageId(123L)).willReturn(reports);

        mvc.perform(get("/reports").param("messageId", "123"))
                .andExpect(status().isOk())
                .andDo(print());

    }

    @Test
    @DisplayName("create a report")
    public void createReport() throws Exception {
        JSONObject newReport = new JSONObject();
        newReport.put("messageId", 123L);
        newReport.put("contents", "this is bad message!!!");
        System.out.println("report = " + newReport);

        mvc.perform(post("/reports")
                        .content(newReport.toString())
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.content()
                        .string(objectMapper.writeValueAsString(0L)))
                .andDo(print());
    }

}