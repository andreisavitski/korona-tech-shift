package by.shift.production.util;

import lombok.experimental.UtilityClass;
import org.slf4j.Logger;

import java.util.List;

import static org.slf4j.LoggerFactory.getLogger;

@UtilityClass
public class ShutdownManager {

    private static final Logger logger = getLogger(ShutdownManager.class);

    public void registerShutdownHook(List<Thread> threads) {
        Runtime.getRuntime().addShutdownHook(new Thread(() -> {
            logger.debug("Request to interrupt all threads");
            for (Thread thread : threads) {
                thread.interrupt();
            }
            for (Thread thread : threads) {
                try {
                    thread.join(2000);
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                    logger.warn("Shutdown hook interrupted while waiting for thread: {}", thread.getName());
                }
            }
            logger.debug("All threads are stopped");
        }));
    }
}
