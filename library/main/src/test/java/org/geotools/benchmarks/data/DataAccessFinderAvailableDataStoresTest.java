package org.geotools.benchmarks.data;

import java.sql.DriverManager;
import java.util.Collections;
import java.util.Iterator;
import org.geotools.benchmarks.BenchmarkTest;
import org.geotools.data.DataStoreFactorySpi;
import org.geotools.data.DataStoreFinder;
import org.geotools.util.factory.GeoTools;

public class DataAccessFinderAvailableDataStoresTest extends BenchmarkTest {

    public @Override Class<?> getBenchmark() {
        return DataAccessFinderAvailableDataStores.class;
    }

    public static void main(String... args) throws Exception {
        print("all: ", DataStoreFinder.getAllDataStores());
        print("available: ", DataStoreFinder.getAvailableDataStores());
        if(true)return;
        Collections.list(DriverManager.getDrivers())
                .stream()
                .map(Object::getClass)
                .map(Class::getName)
                .forEach(System.err::println);

        new DataAccessFinderAvailableDataStoresTest().runJmhBenchmarkDebugMode();
    }

    private static void print(String title, Iterator<DataStoreFactorySpi> it) {
        System.err.println(title);
        while(it.hasNext()) {
            DataStoreFactorySpi f = it.next();
            System.err.println(f.getDisplayName() + " " + f.getClass().getName());
        }
    }
}
