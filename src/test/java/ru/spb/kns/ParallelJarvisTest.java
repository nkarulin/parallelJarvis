package ru.spb.kns;

import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ForkJoinPool;

public class ParallelJarvisTest {

    @Test
    public void test() {
        List<Point> points = new ArrayList<>();
        points.add(new Point(0, 2));
        points.add(new Point(3, 2.1));
        points.add(new Point(4, 2));
        points.add(new Point(7, 3));
        points.add(new Point(1, 0));
        points.add(new Point(2, 0));
        points.add(new Point(5, 0));
        points.add(new Point(6, 0));
        points.add(new Point(0, -2));
        points.add(new Point(3, -2));
        points.add(new Point(4, -2));
        points.add(new Point(7, -3));


        RecursiveJarvisTask.THRESHOLD = 3;
        RecursiveJarvisTask recursiveJarvisTask = new RecursiveJarvisTask(points);
        ForkJoinPool forkJoinPool = ForkJoinPool.commonPool();
        forkJoinPool.submit(recursiveJarvisTask);
        List<Point> shell = recursiveJarvisTask.join();

        System.out.println(shell);
    }
}
