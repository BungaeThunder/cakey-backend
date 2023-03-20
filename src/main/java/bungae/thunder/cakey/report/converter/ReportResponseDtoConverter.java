package bungae.thunder.cakey.report.converter;

import bungae.thunder.cakey.common.converter.CommonConverter;
import bungae.thunder.cakey.report.domain.Report;
import bungae.thunder.cakey.report.dto.ReportResponseDto;
import org.springframework.stereotype.Component;

@Component
public class ReportResponseDtoConverter implements CommonConverter<Report, ReportResponseDto> {

    @Override
    public ReportResponseDto convert(Report source) {
        return ReportResponseDto.builder()
                .id(source.getId())
                .contents(source.getContents())
                .messageId(source.getMessage().getId())
                .build();
    }
}
