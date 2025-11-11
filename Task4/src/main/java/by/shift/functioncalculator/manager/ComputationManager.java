package by.shift.functioncalculator.manager;

import by.shift.functioncalculator.worker.Calculator;
import by.shift.functioncalculator.worker.Task;

import java.math.BigDecimal;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.stream.IntStream;

public record ComputationManager(ExecutorService executor) {

    private static final int MIN_CHUNK_SIZE = 1;

    private static final int START_INDEX_OFFSET = 1;

    public BigDecimal computeParallel(final int n,
                                      final int processors) {
        final int chunk = Math.max(MIN_CHUNK_SIZE, n / processors);
        final List<CompletableFuture<BigDecimal>> futures = createTasks(n, chunk, processors);
        CompletableFuture.allOf(futures.toArray(new CompletableFuture[0])).join();
        return futures.stream()
                .map(CompletableFuture::join)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }

    private List<CompletableFuture<BigDecimal>> createTasks(final int n,
                                                            final int chunk,
                                                            final int processors) {
        return IntStream.range(0, processors)
                .mapToObj(i -> {
                    final int start = i * chunk + START_INDEX_OFFSET;
                    final int end = (i == processors - 1) ? n : (i + 1) * chunk;
                    return CompletableFuture.supplyAsync(
                            new Calculator(new Task(start, end))::compute,
                            executor
                    );
                })
                .toList();
    }
}
