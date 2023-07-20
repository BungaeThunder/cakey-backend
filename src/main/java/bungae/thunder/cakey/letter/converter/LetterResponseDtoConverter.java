package bungae.thunder.cakey.letter.converter;

import bungae.thunder.cakey.common.converter.CommonConverter;
import bungae.thunder.cakey.letter.domain.Letter;
import bungae.thunder.cakey.letter.dto.LetterResponseDto;
import org.springframework.stereotype.Component;

@Component
public class LetterResponseDtoConverter implements CommonConverter<Letter, LetterResponseDto> {

    @Override
    public LetterResponseDto convert(Letter source) {
        return LetterResponseDto.builder()
                .id(source.getId())
                .contents(source.getContents())
                .reply(source.getReply())
                .audioUrl(source.getAudioUrl())
                .bookmark(source.getBookmark())
                .isRead(source.getIsRead())
                .cakeId(source.getCake().getId())
                .senderId(source.getSender().getId())
                .build();
    }
}
