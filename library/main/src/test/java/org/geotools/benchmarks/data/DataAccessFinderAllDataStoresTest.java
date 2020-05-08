package org.geotools.benchmarks.data;

import org.geotools.benchmarks.BenchmarkTest;

public class DataAccessFinderAllDataStoresTest extends BenchmarkTest {

    public @Override Class<?> getBenchmark() {
        return DataAccessFinderAllDataStores.class;
    }

    public static void main(String... args) throws Exception {
        new DataAccessFinderAllDataStoresTest().runJmhBenchmarkDebugMode();
    }
}
