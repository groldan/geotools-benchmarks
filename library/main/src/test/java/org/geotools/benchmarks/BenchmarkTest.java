package org.geotools.benchmarks;

import static org.junit.Assert.assertFalse;

import java.io.File;
import java.util.Collection;
import org.geotools.util.Version;
import org.geotools.util.factory.GeoTools;
import org.junit.Test;
import org.openjdk.jmh.annotations.Mode;
import org.openjdk.jmh.results.RunResult;
import org.openjdk.jmh.results.format.ResultFormatType;
import org.openjdk.jmh.runner.Runner;
import org.openjdk.jmh.runner.RunnerException;
import org.openjdk.jmh.runner.options.Options;
import org.openjdk.jmh.runner.options.OptionsBuilder;
import org.openjdk.jmh.runner.options.VerboseMode;

public abstract class BenchmarkTest {

    public abstract Class<?> getBenchmark();

    @Test
    public void runJmhBenchmark() throws RunnerException {
        Version version = GeoTools.getVersion();
        String buildRevision = GeoTools.getBuildRevision();
        // String outputFileName = String.format("benchmark-output-%s-%s.csv", version,
        // buildRevision);
        new File("target/benchmarks").mkdir();
        final Class<?> benchmark = getBenchmark();
        String resultFileName =
                String.format(
                        "target/benchmarks/%s-results-%s-%s.csv",
                        benchmark.getSimpleName(), version, buildRevision);

        Options opt =
                new OptionsBuilder()
                        .include(benchmark.getSimpleName())
                        // .output(outputFileName)
                        .resultFormat(ResultFormatType.CSV)
                        .result(resultFileName)
                        // .addProfiler(HotspotMemoryProfiler.class)
                        // .addProfiler(HotspotRuntimeProfiler.class)
                        .build();
        Collection<RunResult> runResults = new Runner(opt).run();
        assertFalse(runResults.isEmpty());
    }

    public void runJmhBenchmarkDebugMode() throws RunnerException {
        runJmhBenchmarkDebugMode(null);
    }
    
    public void runJmhBenchmarkDebugMode(String include) throws RunnerException {
        Version version = GeoTools.getVersion();
        String buildRevision = GeoTools.getBuildRevision();
        // String outputFileName = String.format("benchmark-output-%s-%s.csv", version,
        // buildRevision);
        new File("target/benchmarks").mkdir();
        final Class<?> benchmark = getBenchmark();
        String resultFileName =
                String.format(
                        "target/benchmarks/%s-results-%s-%s.csv",
                        benchmark.getSimpleName(), version, buildRevision);

        String includePattern = include == null ? benchmark.getSimpleName() : include;
        Options opt =
                new OptionsBuilder()
                        .include(includePattern)
                        // .output(outputFileName)
                        .resultFormat(ResultFormatType.CSV)
                        .result(resultFileName)
                        // .addProfiler(HotspotMemoryProfiler.class)
                        // .addProfiler(HotspotRuntimeProfiler.class)

                        .forks(0)
                        .verbosity(VerboseMode.EXTRA)
                        .mode(Mode.SampleTime)
                        .build();
        Collection<RunResult> runResults = new Runner(opt).run();
        assertFalse(runResults.isEmpty());
    }
}
