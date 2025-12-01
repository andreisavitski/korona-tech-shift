package by.shift.production.core;

import org.slf4j.Logger;

import java.util.ArrayDeque;
import java.util.Deque;

import static org.slf4j.LoggerFactory.getLogger;

public class Storage {

    private static final Logger LOG = getLogger(Storage.class);

    private final Deque<Resource> queue = new ArrayDeque<>();

    private final int storageSize;

    public Storage(int storageSize) {
        if (storageSize <= 0) {
            throw new IllegalArgumentException("The storage capacity must be greater than zero");
        }
        this.storageSize = storageSize;
    }

    public void produce(Resource resource) {
        synchronized (this) {
            while (queue.size() >= storageSize) {
                LOG.debug("Waiting (storage is full: {},{})", queue.size(), storageSize);
                try {
                    this.wait();
                } catch (InterruptedException e) {
                    LOG.warn("Producer terminated while waiting", e);
                    Thread.currentThread().interrupt();
                    return;
                }
                LOG.debug("Producer resumed");
            }
            queue.addLast(resource);
            LOG.debug("Produced resource={}; storage={}/{}", resource.getUuid(), queue.size(), storageSize);
            this.notifyAll();
        }
    }

    public Resource consume() {
        synchronized (this) {
            while (queue.isEmpty()) {
                LOG.debug("Waiting (storage is empty)");
                try {
                    this.wait();
                } catch (InterruptedException e) {
                    LOG.warn("Consumer terminated while waiting", e);
                    Thread.currentThread().interrupt();
                    return null;
                }
                LOG.debug("Consumer resumed");
            }
            Resource resource = queue.removeFirst();
            LOG.debug("Consume resource={}; storage={}/{}", resource.getUuid(), queue.size(), storageSize);
            this.notifyAll();
            return resource;
        }
    }
}
