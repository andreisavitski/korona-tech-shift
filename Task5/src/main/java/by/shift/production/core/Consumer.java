package by.shift.production.core;

import lombok.AllArgsConstructor;
import org.slf4j.Logger;

import static org.slf4j.LoggerFactory.getLogger;

@AllArgsConstructor
public class Consumer extends Thread {

    private static final Logger logger = getLogger(Consumer.class);

    private final Storage storage;

    private final long consumeTime;

    @Override
    public void run() {
        boolean shouldExit = false;
        while (!Thread.currentThread().isInterrupted() && !shouldExit) {
            try {
                Resource resource = storage.consume();
                if (resource != null) {
                    delay();
                    logger.debug("Has finished processing the resource = {}", resource.getUuid());
                } else {
                    shouldExit = true;
                }
            } catch (InterruptedException e) {
                logger.debug("Stopped");
                Thread.currentThread().interrupt();
                shouldExit = true;
            } catch (Exception e) {
                logger.error(e.getMessage());
            }
        }
    }

    private void delay() throws InterruptedException {
        Thread.sleep(consumeTime);
    }
}
