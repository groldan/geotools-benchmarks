package org.geotools.benchmarks.filter;

import static java.util.concurrent.TimeUnit.SECONDS;

import org.geotools.filter.AttributeExpressionImpl;
import org.openjdk.jmh.annotations.Benchmark;
import org.openjdk.jmh.annotations.BenchmarkMode;
import org.openjdk.jmh.annotations.Fork;
import org.openjdk.jmh.annotations.Measurement;
import org.openjdk.jmh.annotations.Mode;
import org.openjdk.jmh.annotations.Scope;
import org.openjdk.jmh.annotations.State;
import org.openjdk.jmh.annotations.Threads;
import org.openjdk.jmh.annotations.Warmup;

@Fork(0)
@BenchmarkMode(Mode.Throughput)
@Warmup(iterations = 2, time = 5, timeUnit = SECONDS)
@Measurement(iterations = 3, time = 5, timeUnit = SECONDS)
public class AttributeExpressionImplBenchmark {

    @State(Scope.Benchmark)
    public static class TestState {
        private AttributeExpressionImpl att = new AttributeExpressionImpl("property");
        private SampleBean obj = new SampleBean("0.1");
    }

    public static class SampleBean {
        private String prop;

        public SampleBean(String val) {
            this.prop = val;
        }

        public String getProperty() {
            return prop;
        }
    }

    private double getLastPropertyAccessor(TestState state) {
        SampleBean obj = state.obj;
        AttributeExpressionImpl att = state.att;
        Double val = att.evaluate(obj, Double.class);
        return val == null ? 0.0 : val.doubleValue();
    }

    @Threads(value = 1)
    public @Benchmark double getLastPropertyAccessor_001(TestState state) {
        return getLastPropertyAccessor(state);
    }

    @Threads(value = 4)
    public @Benchmark double getLastPropertyAccessor_004(TestState state) {
        return getLastPropertyAccessor(state);
    }

    @Threads(value = 16)
    public @Benchmark double getLastPropertyAccessor_016(TestState state) {
        return getLastPropertyAccessor(state);
    }

    @Threads(value = 64)
    public @Benchmark double getLastPropertyAccessor_064(TestState state) {
        return getLastPropertyAccessor(state);
    }

    @Threads(value = 256)
    public @Benchmark double getLastPropertyAccessor_256(TestState state) {
        return getLastPropertyAccessor(state);
    }
}
