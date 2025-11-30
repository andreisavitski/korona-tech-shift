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

    private static final Object LOCK = new Object();

    private static final String PRODUCER_NAME = "producer";

    private static final String CONSUMER_NAME = "consumer";

    private static final String MONITOR = "monitor";

    public List<Thread> startWorkersWithRestart(Configuration configuration) {
        Storage storage = new Storage(configuration.storageSize);
        List<Thread> workers = new ArrayList<>();

        createProducers(workers, configuration, storage);
        createConsumers(workers, configuration, storage);
        logger.debug("Started all workers");

        Thread monitor = new Thread(() -> monitorAndRestart(workers, storage, configuration));
        monitor.setName(MONITOR);
        monitor.setDaemon(true);
        monitor.start();

        workers.add(monitor);
        return workers;
    }

    private void createProducers(List<Thread> workers, Configuration configuration, Storage storage) {
        for (int i = 1; i <= configuration.producerCount; i++) {
            Producer producer = new Producer(storage, configuration.producerTime);
            producer.setName(PRODUCER_NAME + i);
            producer.start();
            workers.add(producer);
        }
    }

    private void createConsumers(List<Thread> workers, Configuration configuration, Storage storage) {
        for (int i = 1; i <= configuration.consumerCount; i++) {
            Consumer consumer = new Consumer(storage, configuration.consumerTime);
            consumer.setName(CONSUMER_NAME + i);
            consumer.start();
            workers.add(consumer);
        }
    }

    private void monitorAndRestart(List<Thread> workers,
                                   Storage storage,
                                   Configuration configuration) {
        while (!Thread.currentThread().isInterrupted()) {
            delay();
            synchronized (LOCK) {
                for (int i = 0; i < workers.size(); i++) {
                    Thread thread = workers.get(i);
                    if (!thread.isAlive() && !MONITOR.equals(thread.getName())) {
                        logger.warn("Thread {} is dead, restarting", thread.getName());

                        Thread newWorker = recreateWorker(thread.getName(), storage, configuration);
                        if (newWorker != null) {
                            newWorker.start();
                            workers.set(i, newWorker);
                        }
                    }
                }
            }
        }
    }

    private Thread recreateWorker(String nameDeadThread, Storage storage, Configuration configuration) {
        String prefix = nameDeadThread.startsWith(PRODUCER_NAME) ? PRODUCER_NAME :
                nameDeadThread.startsWith(CONSUMER_NAME) ? CONSUMER_NAME :
                        "";
        Thread newWorker = null;
        if (prefix.equals(PRODUCER_NAME)) {
            int index = parseIndex(nameDeadThread, PRODUCER_NAME);
            newWorker = new Producer(storage, configuration.producerTime);
            newWorker.setName(PRODUCER_NAME + index);
        } else if (prefix.equals(CONSUMER_NAME)) {
            int index = parseIndex(nameDeadThread, CONSUMER_NAME);
            newWorker = new Consumer(storage, configuration.consumerTime);
            newWorker.setName(CONSUMER_NAME + index);
        }
        return newWorker;
    }

    private int parseIndex(String name, String prefix) {
        try {
            return Integer.parseInt(name.substring(prefix.length()));
        } catch (Exception e) {
            return 0;
        }
    }

    private void delay() {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}
