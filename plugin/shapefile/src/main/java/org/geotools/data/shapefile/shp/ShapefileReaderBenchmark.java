package org.geotools.data.shapefile.shp;

import static java.util.concurrent.TimeUnit.SECONDS;
import org.openjdk.jmh.annotations.Benchmark;
import org.openjdk.jmh.annotations.BenchmarkMode;
import org.openjdk.jmh.annotations.Fork;
import org.openjdk.jmh.annotations.Measurement;
import org.openjdk.jmh.annotations.Mode;
import org.openjdk.jmh.annotations.Warmup;

@Fork(1)
@BenchmarkMode({Mode.AverageTime, Mode.SampleTime})
@Warmup(iterations = 2, time = 3, timeUnit = SECONDS)
@Measurement(iterations = 3, time = 5, timeUnit = SECONDS)
public class ShapefileReaderBenchmark {

    public @Benchmark int largePolygons() {
        return 1;//count(DataAccessFinder.getAllDataStores());
    }
}
