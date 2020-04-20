package org.geotools.data.shapefile.shp;

import java.io.IOException;
import java.util.concurrent.TimeUnit;
import org.geotools.data.shapefile.files.ShpFiles;
import org.geotools.data.shapefile.shp.ShapefileReader.Record;
import org.geotools.geometry.jts.LiteCoordinateSequenceFactory;
import org.locationtech.jts.geom.Geometry;
import org.locationtech.jts.geom.GeometryFactory;
import org.openjdk.jmh.annotations.Benchmark;
import org.openjdk.jmh.annotations.BenchmarkMode;
import org.openjdk.jmh.annotations.Fork;
import org.openjdk.jmh.annotations.Measurement;
import org.openjdk.jmh.annotations.Mode;
import org.openjdk.jmh.annotations.Warmup;

@Fork(1)
@BenchmarkMode({Mode.AverageTime})
@Warmup(iterations = 1, time = 5, timeUnit = TimeUnit.SECONDS)
@Measurement(iterations = 3, time = 5, timeUnit = TimeUnit.SECONDS)
public class ShapefileReaderBenchmark {

    // @Benchmark
    public int largePolygon_hasNext() throws ShapefileException, IOException {
        String shp = "file:/data/geodata/large_poly/large_poly.shp";
        ShpFiles shapefileFiles = new ShpFiles(shp);
        boolean strict = true;
        boolean useMemoryMapped = false;
        GeometryFactory gf = new GeometryFactory();
        ShapefileReader reader = new ShapefileReader(shapefileFiles, strict, useMemoryMapped, gf);
        boolean hasNext = reader.hasNext();
        reader.close();
        return hasNext ? 1 : 0;
    }


    // @Benchmark
    public int largePolygon_simplifiedShape() throws ShapefileException, IOException {
        String shp = "file:/data/geodata/large_poly/large_poly.shp";
        ShpFiles shapefileFiles = new ShpFiles(shp);
        boolean strict = true;
        boolean useMemoryMapped = false;
        GeometryFactory gf = new GeometryFactory();
        ShapefileReader reader = new ShapefileReader(shapefileFiles, strict, useMemoryMapped, gf);
        Record record = reader.nextRecord();
        Geometry g = (Geometry) record.getSimplifiedShape();
        reader.close();
        return g.getNumPoints();
    }

    @Benchmark
    public int largePolygon_shape() throws ShapefileException, IOException {
        String shp = "file:/data/geodata/large_poly/large_poly.shp";
        ShpFiles shapefileFiles = new ShpFiles(shp);
        boolean strict = true;
        boolean useMemoryMapped = false;
        GeometryFactory gf = new GeometryFactory(new LiteCoordinateSequenceFactory());
        ShapefileReader reader = new ShapefileReader(shapefileFiles, strict, useMemoryMapped, gf);
        Record record = reader.nextRecord();
        Geometry shape = (Geometry) record.shape();
        reader.close();
        return shape.getNumPoints();
    }
}
