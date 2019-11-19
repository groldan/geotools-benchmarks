package org.geotools.benchmarks.filter;

import static org.junit.Assert.assertFalse;
import java.util.Collection;
import org.geotools.util.Version;
import org.geotools.util.factory.GeoTools;
import org.junit.Test;
import org.openjdk.jmh.results.RunResult;
import org.openjdk.jmh.results.format.ResultFormatType;
import org.openjdk.jmh.runner.Runner;
import org.openjdk.jmh.runner.RunnerException;
import org.openjdk.jmh.runner.options.Options;
import org.openjdk.jmh.runner.options.OptionsBuilder;

public class AttributeExpressionImplTest {

    @Test
    public void runJmhBenchmark() throws RunnerException {
        Version version = GeoTools.getVersion();
        String buildRevision = GeoTools.getBuildRevision();
        String outputFileName = String.format("benchmark-output-%s-%s.csv", version, buildRevision);
        String resultFileName =
                String.format("benchmark-results-%s-%s.csv", version, buildRevision);
        Options opt =
                new OptionsBuilder().include(AttributeExpressionImplBenchmark.class.getSimpleName())
                        .output(outputFileName).resultFormat(ResultFormatType.CSV)
                        .result(resultFileName).build();
        Collection<RunResult> runResults = new Runner(opt).run();
        assertFalse(runResults.isEmpty());
    }
}
