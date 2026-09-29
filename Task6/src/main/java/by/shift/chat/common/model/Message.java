package by.shift.chat.common.model;

import lombok.Builder;
import lombok.Getter;
import lombok.extern.jackson.Jacksonized;

import java.time.LocalDateTime;

@Builder
@Getter
@Jacksonized
public class Message {

    private final MessageType messageType;

    private final String sender;

    private final String text;

    private final LocalDateTime timestamp;
}
