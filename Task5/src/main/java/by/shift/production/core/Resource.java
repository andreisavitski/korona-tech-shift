package by.shift.production.core;

import lombok.Getter;

import java.util.UUID;

@Getter
public class Resource {

    private final UUID uuid;

    public Resource() {
        this.uuid = UUID.randomUUID();
    }
}
