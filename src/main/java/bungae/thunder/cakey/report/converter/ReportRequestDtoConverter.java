package bungae.thunder.cakey.report.converter;

import bungae.thunder.cakey.common.converter.CommonConverter;
import bungae.thunder.cakey.report.domain.Report;
import bungae.thunder.cakey.report.dto.ReportRequestDto;
import bungae.thunder.cakey.report.dto.ReportResponseDto;
import org.springframework.stereotype.Component;

@Component
public class ReportRequestDtoConverter implements CommonConverter<ReportRequestDto, Report> {

    @Override
    public Report convert(ReportRequestDto source) {
//        return Report.builder()
//                .contents(source.getContents())
//                .message(source.getMessageId())
//                .build();
        return null;
    }
}
