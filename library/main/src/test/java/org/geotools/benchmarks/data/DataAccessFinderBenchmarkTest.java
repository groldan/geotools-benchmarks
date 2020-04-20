package org.geotools.benchmarks.data;

import java.sql.DriverManager;
import java.util.Collections;
import org.geotools.benchmarks.BenchmarkTest;
import org.geotools.util.factory.GeoTools;
import org.openjdk.jmh.runner.RunnerException;

public class DataAccessFinderBenchmarkTest extends BenchmarkTest {

    public @Override Class<?> getBenchmark() {
        return DataAccessFinderBenchmark.class;
    }

    public static void main(String... args) throws Exception {
        GeoTools.getInitialContext(null);
        Collections.list(DriverManager.getDrivers())
                .stream()
                .map(Object::getClass)
                .map(Class::getName)
                .forEach(System.err::println);

        new DataAccessFinderBenchmarkTest().runJmhBenchmarkDebugMode("getAvailableDataStores");
    }
}
