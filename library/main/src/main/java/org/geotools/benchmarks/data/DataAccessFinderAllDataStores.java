package org.geotools.benchmarks.data;

import static java.util.concurrent.TimeUnit.SECONDS;
import java.util.Iterator;
import java.util.concurrent.TimeUnit;
import org.geotools.data.DataAccessFactory;
import org.geotools.data.DataAccessFinder;
import org.openjdk.jmh.annotations.Benchmark;
import org.openjdk.jmh.annotations.BenchmarkMode;
import org.openjdk.jmh.annotations.Fork;
import org.openjdk.jmh.annotations.Measurement;
import org.openjdk.jmh.annotations.Mode;
import org.openjdk.jmh.annotations.OutputTimeUnit;
import org.openjdk.jmh.annotations.Threads;
import org.openjdk.jmh.annotations.Warmup;

@Fork(1)
//gotta use avg time cause throughput reports false results under high contention (seems like dropping a lot of calls to stick to the test time)
@BenchmarkMode({Mode.Throughput, Mode.AverageTime})
@Warmup(iterations = 3, time = 5, timeUnit = SECONDS)
@OutputTimeUnit(TimeUnit.MILLISECONDS)
@Measurement(iterations = 3, time = 5, timeUnit = SECONDS)
public class DataAccessFinderAllDataStores {

    @Threads(value = 1)
    public @Benchmark int getAllDataStores_001() {
        return count(DataAccessFinder.getAllDataStores());
    }

    @Threads(value = 2)
    public @Benchmark int getAllDataStores_002() {
        return count(DataAccessFinder.getAllDataStores());
    }

    @Threads(value = 4)
    public @Benchmark int getAllDataStores_004() {
        return count(DataAccessFinder.getAllDataStores());
    }

    @Threads(value = 8)
    public @Benchmark int getAllDataStores_008() {
        return count(DataAccessFinder.getAllDataStores());
    }

    @Threads(value = 16)
    public @Benchmark int getAllDataStores_016() {
        return count(DataAccessFinder.getAllDataStores());
    }

    @Threads(value = 32)
    public @Benchmark int getAllDataStores_032() {
        return count(DataAccessFinder.getAllDataStores());
    }

    @Threads(value = 64)
    public @Benchmark int getAllDataStores_064() {
        return count(DataAccessFinder.getAllDataStores());
    }

    @Threads(value = 128)
    @Measurement(iterations = 3, time = 30, timeUnit = SECONDS)
    public @Benchmark int getAllDataStores_128() {
        return count(DataAccessFinder.getAllDataStores());
    }

    @Threads(value = 256)
    @Measurement(iterations = 3, time = 30, timeUnit = SECONDS)
    public @Benchmark int getAllDataStores_256() {
        return count(DataAccessFinder.getAllDataStores());
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
