package bungae.thunder.cakey.report.converter;

import bungae.thunder.cakey.common.converter.CommonConverter;
import bungae.thunder.cakey.report.domain.Report;
import bungae.thunder.cakey.report.dto.CreateReportRequestDto;
import org.springframework.stereotype.Component;

@Component
public class CreateReportRequestDtoConverter
        implements CommonConverter<CreateReportRequestDto, Report> {

    @Override
    public Report convert(CreateReportRequestDto source) {
        //        return Report.builder()
        //                .contents(source.getContents())
        //                .message(source.getMessageId())
        //                .build();
        return null;
    }
}
