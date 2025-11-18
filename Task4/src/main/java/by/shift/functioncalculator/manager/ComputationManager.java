package by.shift.functioncalculator.manager;

import by.shift.functioncalculator.worker.Calculator;
import by.shift.functioncalculator.worker.Task;
import org.slf4j.Logger;

import java.math.BigDecimal;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CompletionException;
import java.util.concurrent.ExecutorService;
import java.util.stream.IntStream;

import static org.slf4j.LoggerFactory.getLogger;

public record ComputationManager(ExecutorService executor) {

    private static final Logger logger = getLogger(ComputationManager.class);

    private static final int MIN_CHUNK_SIZE = 1;

    private static final int START_INDEX_OFFSET = 1;

    public BigDecimal computeParallel(int n, int processors) {
        int chunk = Math.max(MIN_CHUNK_SIZE, n / processors);
        List<CompletableFuture<BigDecimal>> futures = createTasks(n, chunk, processors);
        CompletableFuture.allOf(futures.toArray(new CompletableFuture[0])).join();

        return futures.stream()
                .map(future -> {
                    try {
                        return future.join();
                    } catch (CompletionException e) {
                        Throwable cause = e.getCause();
                        logger.error("Ошибка при выполнении задачи: {}", cause.getMessage());
                        return BigDecimal.ZERO;
                    }
                })
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }

    private List<CompletableFuture<BigDecimal>> createTasks(int n, int chunk, int processors) {
        return IntStream.range(0, processors)
                .mapToObj(i -> {
                    int start = i * chunk + START_INDEX_OFFSET;
                    int end = (i == processors - 1) ? n : (i + 1) * chunk;

                    return CompletableFuture.supplyAsync(
                                    new Calculator(new Task(start, end))::compute, executor)
                            .exceptionally(ex -> {
                                logger.error("Ошибка в диапазоне [{}...{}]: {}", start, end, ex.getMessage());
                                return BigDecimal.ZERO;
                            });
                })
                .toList();
    }
}
