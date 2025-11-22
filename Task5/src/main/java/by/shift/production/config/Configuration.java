package by.shift.production.config;

import lombok.AllArgsConstructor;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

@AllArgsConstructor
public class Configuration {

    public final int producerCount;

    public final int consumerCount;

    public final long producerTime;

    public final long consumerTime;

    public final int storageSize;

    public static Configuration load(String path) {
        Properties properties = new Properties();
        try (FileInputStream fis = new FileInputStream(path)) {
            properties.load(fis);
        } catch (IOException e) {
            throw new IllegalArgumentException(e.getMessage());
        }
        int producerCount = Integer.parseInt(properties.getProperty("producerCount", "1"));
        int consumerCount = Integer.parseInt(properties.getProperty("consumerCount", "1"));
        long producerTime = Long.parseLong(properties.getProperty("producerTime", "1000"));
        long consumerTime = Long.parseLong(properties.getProperty("consumerTime", "1000"));
        int storageSize = Integer.parseInt(properties.getProperty("storageSize", "10"));
        return new Configuration(producerCount, consumerCount, producerTime, consumerTime, storageSize);
    }
}
