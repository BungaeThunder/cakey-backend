package bungae.thunder.cakey.report.converter;

import bungae.thunder.cakey.common.converter.CommonConverter;
import bungae.thunder.cakey.report.domain.Report;
import bungae.thunder.cakey.report.dto.ReportRequestDto;
import org.springframework.stereotype.Component;

@Component
public class ReportRequestDtoConverter implements CommonConverter<ReportRequestDto, Report> {

    @Override
    public Report convert(ReportRequestDto source) {
        //TODO: 아이디를 save에서 넣어주는데 어케 convert를 하지??
        return null;
    }
}
