package by.shift.production.core;

import lombok.AllArgsConstructor;
import org.slf4j.Logger;

import java.time.LocalDateTime;

import static org.slf4j.LoggerFactory.getLogger;

@AllArgsConstructor
public class Consumer extends Thread {

    private static final Logger logger = getLogger(Consumer.class);

    private final int id;

    private final Storage storage;

    private final long consumeTime;

    @Override
    public void run() {
        boolean shouldExit = false;
        while (!Thread.currentThread().isInterrupted() && !shouldExit) {
            try {
                Resource resource = storage.consume(id);
                if (resource != null) {
                    delay();
                    logger.debug("{}: Consumer {} has finished processing the resource ={}",
                            LocalDateTime.now(), id, resource.getUuid());
                } else {
                    shouldExit = true;
                }
            } catch (InterruptedException e) {
                logger.debug("{}: Consumer {} stopped", LocalDateTime.now(), id);
                Thread.currentThread().interrupt();
                shouldExit = true;
            } catch (Exception e) {
                logger.error("{}: Consumer {}. Error: {}", LocalDateTime.now(), id, e.getMessage());
            }
        }
    }

    private void delay() throws InterruptedException {
        Thread.sleep(consumeTime);
    }
}
