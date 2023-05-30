package bungae.thunder.cakey.message.converter;

import bungae.thunder.cakey.common.converter.CommonConverter;
import bungae.thunder.cakey.message.domain.Message;
import bungae.thunder.cakey.message.dto.MessageResponseDto;
import org.springframework.stereotype.Component;

@Component
public class MessageResponseDtoConverter implements CommonConverter<Message, MessageResponseDto> {

    @Override
    public MessageResponseDto convert(Message source) {
        return MessageResponseDto.builder()
                .id(source.getId())
                .contents(source.getContents())
                .reply(source.getReply())
                .audioUrl(source.getAudioUrl())
                .cakeId(source.getCake().getId())
                .senderId(source.getSender().getId())
                .build();
    }
}
