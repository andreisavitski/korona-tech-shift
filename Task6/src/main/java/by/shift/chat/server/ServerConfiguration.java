package by.shift.chat.server;

import by.shift.chat.common.exception.InvalidConfigurationException;
import by.shift.chat.common.exception.PropertiesLoadException;
import lombok.Getter;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

@Getter
class ServerConfiguration {

    private static final String SERVER_PROPERTIES = "server.properties";

    private static final String SERVER_PORT = "server.port";

    private static final String DEFAULT_PORT = "8085";

    private static final int MIN_PORT = 1;

    private static final int MAX_PORT = 65535;

    private final int port;

    ServerConfiguration() {
        Properties properties = new Properties();
        try (InputStream in = ServerConfiguration.class.getClassLoader().getResourceAsStream(SERVER_PROPERTIES)) {
            properties.load(in);
            this.port = Integer.parseInt(properties.getProperty(SERVER_PORT, DEFAULT_PORT));
        } catch (IOException e) {
            throw new PropertiesLoadException(
                    String.format("Failed to load server configuration from %s", SERVER_PROPERTIES), e
            );
        } catch (NullPointerException e) {
            throw new PropertiesLoadException(String.format("Unable to find file %s", SERVER_PROPERTIES), e);
        } catch (NumberFormatException e) {
            throw new InvalidConfigurationException("The server port must be a number", e);
        }
        if (this.port < MIN_PORT || this.port > MAX_PORT) {
            throw new InvalidConfigurationException(
                    String.format("The server port must be in the range from %d to %d", MIN_PORT, MAX_PORT)
            );
        }
    }
}