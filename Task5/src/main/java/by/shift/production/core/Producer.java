package by.shift.production.core;

import lombok.AllArgsConstructor;
import org.slf4j.Logger;

import static org.slf4j.LoggerFactory.getLogger;

@AllArgsConstructor
public class Producer extends Thread {

    private static final Logger LOG = getLogger(Producer.class);

    private final Storage storage;

    private final long produceTime;

    @Override
    public void run() {
        boolean shouldExit = false;
        while (!Thread.currentThread().isInterrupted() && !shouldExit) {
            try {
                delay();
                Resource resource = new Resource();
                storage.produce(resource);
            } catch (InterruptedException e) {
                LOG.debug("Stopped");
                Thread.currentThread().interrupt();
                shouldExit = true;
            } catch (Exception e) {
                LOG.error(e.getMessage());
            }
        }
    }

    private void delay() throws InterruptedException {
        Thread.sleep(produceTime);
    }
}
