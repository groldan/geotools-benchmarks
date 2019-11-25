package org.geotools.benchmarks.data;

import static java.util.concurrent.TimeUnit.SECONDS;

import java.util.Iterator;
import org.geotools.data.DataAccessFactory;
import org.geotools.data.DataAccessFinder;
import org.openjdk.jmh.annotations.Benchmark;
import org.openjdk.jmh.annotations.BenchmarkMode;
import org.openjdk.jmh.annotations.Fork;
import org.openjdk.jmh.annotations.Measurement;
import org.openjdk.jmh.annotations.Mode;
import org.openjdk.jmh.annotations.Threads;
import org.openjdk.jmh.annotations.Warmup;

@Fork(1)
@BenchmarkMode({Mode.AverageTime, Mode.SampleTime})
@Warmup(iterations = 2, time = 3, timeUnit = SECONDS)
@Measurement(iterations = 3, time = 5, timeUnit = SECONDS)
public class DataAccessFinderBenchmark {

    @Threads(value = 1)
    public @Benchmark int getAllDataStores_001() {
        return count(DataAccessFinder.getAllDataStores());
    }

    @Threads(value = 4)
    public @Benchmark int getAllDataStores_004() {
        return count(DataAccessFinder.getAllDataStores());
    }

    @Threads(value = 16)
    public @Benchmark int getAllDataStores_016() {
        return count(DataAccessFinder.getAllDataStores());
    }

    @Threads(value = 64)
    public @Benchmark int getAllDataStores_064() {
        return count(DataAccessFinder.getAllDataStores());
    }

    @Threads(value = 256)
    public @Benchmark int getAllDataStores_256() {
        return count(DataAccessFinder.getAllDataStores());
    }

    @Threads(value = 1)
    public @Benchmark int getAvailableDataStores_001() {
        return count(DataAccessFinder.getAvailableDataStores());
    }

    @Threads(value = 4)
    public @Benchmark int getAvailableDataStores_004() {
        return count(DataAccessFinder.getAvailableDataStores());
    }

    @Threads(value = 16)
    public @Benchmark int getAvailableDataStores_016() {
        return count(DataAccessFinder.getAvailableDataStores());
    }

    @Threads(value = 64)
    public @Benchmark int getAvailableDataStores_064() {
        return count(DataAccessFinder.getAvailableDataStores());
    }

    @Threads(value = 256)
    public @Benchmark int getAvailableDataStores_256() {
        return count(DataAccessFinder.getAvailableDataStores());
    }

    private int count(Iterator<DataAccessFactory> it) {
        int c = 0;
        while (it.hasNext()) {
            it.next();
            c++;
        }
        return c;
    }
}
