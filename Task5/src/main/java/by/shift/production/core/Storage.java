package by.shift.production.core;

import org.slf4j.Logger;

import java.util.ArrayDeque;
import java.util.Deque;

import static java.time.LocalDateTime.now;
import static org.slf4j.LoggerFactory.getLogger;

public class Storage {

    private static final Logger logger = getLogger(Storage.class);

    private final Deque<Resource> queue = new ArrayDeque<>();

    private final int storageSize;

    public Storage(int storageSize) {
        if (storageSize <= 0) {
            throw new IllegalArgumentException("The storage capacity must be greater than zero");
        }
        this.storageSize = storageSize;
    }

    public void produce(Resource resource, int producerId) {
        synchronized (this) {
            while (queue.size() >= storageSize) {
                logger.debug("{}: Producer {} is waiting (storage is full)={})", now(), producerId, queue.size());
                try {
                    this.wait();
                } catch (InterruptedException e) {
                    logger.warn("{}: Producer {} terminated while waiting", now(), producerId, e);
                    Thread.currentThread().interrupt();
                    return;
                }
                logger.debug("{}: Producer {} resumed", now(), producerId);
            }
            queue.addLast(resource);
            logger.debug("{}: Producer {} produced resource={}; storage={}/{}", now(), producerId, resource.getUuid(), queue.size(), storageSize);
            this.notifyAll();
        }
    }

    public Resource consume(int consumerId) {
        synchronized (this) {
            while (queue.isEmpty()) {
                logger.debug("{}: Consumer {} is waiting (warehouse is full)", now(), consumerId);
                try {
                    this.wait();
                } catch (InterruptedException e) {
                    logger.warn("{}: Consumer {} terminated while waiting", now(), consumerId, e);
                    Thread.currentThread().interrupt();
                    return null;
                }
                logger.debug("{}: Consumer {} resumed", now(), consumerId);
            }
            Resource resource = queue.removeFirst();
            logger.debug("{}: Consumer {} produced resource={}; storage={}/{}", now(), consumerId, resource.getUuid(), queue.size(), storageSize);
            this.notifyAll();
            return resource;
        }
    }
}
