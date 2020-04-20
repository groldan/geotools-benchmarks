package org.geotools.data.shapefile.shp;

import org.geotools.benchmarks.BenchmarkTest;

public class ShapefileReaderBenchmarkTest extends BenchmarkTest {

    public @Override Class<?> getBenchmark() {
        return ShapefileReaderBenchmark.class;
    }

    public static void main(String... args) throws Exception {
        ShapefileReaderBenchmarkTest test = new ShapefileReaderBenchmarkTest();
        //test.runJmhBenchmark();
        test.runJmhBenchmarkDebugMode("largePolygon_shape");
    }
}
