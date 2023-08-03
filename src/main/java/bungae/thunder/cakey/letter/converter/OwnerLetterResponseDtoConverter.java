package bungae.thunder.cakey.letter.converter;

import bungae.thunder.cakey.common.converter.CommonConverter;
import bungae.thunder.cakey.letter.domain.Letter;
import bungae.thunder.cakey.letter.dto.OwnerLetterResponseDto;

public class OwnerLetterResponseDtoConverter
        implements CommonConverter<Letter, OwnerLetterResponseDto> {

    @Override
    public OwnerLetterResponseDto convert(Letter source) {
        return OwnerLetterResponseDto.builder()
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
