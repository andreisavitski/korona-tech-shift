package by.shift.production.util;

import by.shift.production.config.Configuration;
import by.shift.production.core.Consumer;
import by.shift.production.core.Producer;
import by.shift.production.core.Storage;
import lombok.experimental.UtilityClass;
import org.slf4j.Logger;

import java.util.ArrayList;
import java.util.List;

import static org.slf4j.LoggerFactory.getLogger;

@UtilityClass
public class WorkerStarter {

    private static final Logger logger = getLogger(WorkerStarter.class);

    public List<Thread> startWorkers(Configuration configuration) {
        Storage storage = new Storage(configuration.storageSize);
        List<Thread> threads = new ArrayList<>();
        for (int i = 1; i <= configuration.producerCount; i++) {
            Producer producer = new Producer(i, storage, configuration.producerTime);
            producer.start();
            threads.add(producer);
        }
        for (int i = 1; i <= configuration.consumerCount; i++) {
            Consumer consumer = new Consumer(i, storage, configuration.consumerTime);
            consumer.start();
            threads.add(consumer);
        }
        logger.debug("Start all threads");
        return threads;
    }
}
