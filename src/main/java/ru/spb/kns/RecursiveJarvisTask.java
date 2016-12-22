package ru.spb.kns;

import java.util.List;
import java.util.concurrent.ForkJoinTask;
import java.util.concurrent.RecursiveTask;

public class RecursiveJarvisTask extends RecursiveTask<List<Point>> {

    private static final int THRESHOLD = 200;
    private List<Point> points;

    /**
     * @param points - sorted by x!
     */
    public RecursiveJarvisTask(List<Point> points) {
        this.points = points;
    }

    protected List<Point> compute() {
        if (points.size() < THRESHOLD) {
            return Jarvis.findShell(points);
        }

        int median = points.size()/2;
        ForkJoinTask<List<Point>> t1 = new RecursiveJarvisTask(points.subList(0, median));
        ForkJoinTask<List<Point>> t2 = new RecursiveJarvisTask(points.subList(median, 0));

        t1.fork();
        t2.fork();

        return mergeShells(t2.join(), t2.join());
    }

    private static List<Point> mergeShells(List<Point> shellLeft, List<Point> shellRight) {
        return null;
    }
}
