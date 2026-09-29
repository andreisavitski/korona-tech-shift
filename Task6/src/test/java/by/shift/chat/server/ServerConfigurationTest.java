package by.shift.chat.server;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class ServerConfigurationTest {

    @Test
    void serverConfigurationPortInRange() {
        ServerConfiguration configuration = new ServerConfiguration();

        assertThat(configuration.getPort()).isBetween(1, 65535);
    }
}