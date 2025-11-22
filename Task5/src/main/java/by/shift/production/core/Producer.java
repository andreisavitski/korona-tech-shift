package by.shift.production.core;

import lombok.AllArgsConstructor;
import org.slf4j.Logger;

import java.time.LocalDateTime;

import static org.slf4j.LoggerFactory.getLogger;

@AllArgsConstructor
public class Producer extends Thread {

    private static final Logger logger = getLogger(Producer.class);

    private final int id;

    private final Storage storage;

    private final long produceTime;

    @Override
    public void run() {
        boolean shouldExit = false;
        while (!Thread.currentThread().isInterrupted() && !shouldExit) {
            try {
                delay();
                Resource resource = new Resource();
                storage.produce(resource, id);
            } catch (InterruptedException e) {
                logger.debug("{}: Producer {} stopped", LocalDateTime.now(), id);
                Thread.currentThread().interrupt();
                shouldExit = true;
            } catch (Exception e) {
                logger.error("{}: Producer {}. Error: {}", LocalDateTime.now(), id, e.getMessage());
            }
        }
    }

    private void delay() throws InterruptedException {
        Thread.sleep(produceTime);
    }
}
