package by.shift.production;

import by.shift.production.config.Configuration;
import by.shift.production.util.ExitUtil;
import by.shift.production.util.ShutdownManager;
import by.shift.production.util.WorkerStarter;

import java.util.List;

public class ProductionRunner {

    private static final String PROPERTIES_PATH = "Task5/src/main/resources/application.properties";

    public static void main(String[] args) {
        Configuration configuration = Configuration.load(PROPERTIES_PATH);
        List<Thread> threads = WorkerStarter.startWorkers(configuration);
        ShutdownManager.registerShutdownHook(threads);
        ExitUtil.exit();
    }
}