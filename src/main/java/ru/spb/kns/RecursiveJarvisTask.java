package ru.spb.kns;

import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
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
        findBottomLine(shellLeft, shellRight);
        return null;
    }

    /**
     * @param shellLeft
     * @param shellRight
     * @return points to be excluded from the merged shell
     */
    static Set<Point> findBottomLine(List<Point> shellLeft, List<Point> shellRight) {
        int a = shellLeft.indexOf(Collections.max(shellLeft));
        int b = shellRight.indexOf(Collections.min(shellRight));

        System.out.printf("Left (max = %d): %s\n", a, shellLeft);
        System.out.printf("Right (min = %d): %s\n", b, shellRight);

        Set<Point> excludePoints = new HashSet<>();

        boolean aRotate;
        boolean bRotate;
        do {
            aRotate = false;
            bRotate = false;

            while (Jarvis.rotate(shellLeft.get(a - 1 == -1 ? shellLeft.size() - 1 : a - 1), shellRight.get(b), shellLeft.get(a)) > 0) {
                excludePoints.add(shellLeft.get(a));
                a--;
                if (a == -1) {
                    a = shellLeft.size() - 1;
                }
                System.out.printf("Rotate a (now a = %d)\n", a);
                aRotate = true;
            }

            while (Jarvis.rotate(shellLeft.get(a), shellRight.get(b), shellRight.get(b + 1 == shellRight.size() ? 0 : b + 1)) < 0) {
                excludePoints.add(shellRight.get(b));
                b = (b + 1) % shellRight.size();
                System.out.printf("Rotate b (now b = %d)\n", b);
                bRotate = true;
            }
        } while (aRotate || bRotate);

        excludePoints.remove(shellLeft.get(a));

        return excludePoints;
    }

    /**
     * @param shellLeft
     * @param shellRight
     * @return points to be excluded from the merged shell
     */
    static Set<Point> findTopLine(List<Point> shellLeft, List<Point> shellRight) {
        int a = shellLeft.indexOf(Collections.max(shellLeft));
        int b = shellRight.indexOf(Collections.min(shellRight));

        System.out.printf("Left (max = %d): %s\n", a, shellLeft);
        System.out.printf("Right (min = %d): %s\n", b, shellRight);

        Set<Point> excludePoints = new HashSet<>();

        boolean aRotate;
        boolean bRotate;
        do {
            aRotate = false;
            bRotate = false;

            while (Jarvis.rotate(shellLeft.get(a + 1 == shellLeft.size() ? 0 : a + 1), shellRight.get(b), shellLeft.get(a)) < 0) {
                excludePoints.add(shellLeft.get(a));
                a = (a + 1) % shellLeft.size();

                System.out.printf("Rotate a (now a = %d)\n", a);
                aRotate = true;
            }

            while (Jarvis.rotate(shellLeft.get(a), shellRight.get(b), shellRight.get(b - 1 == -1 ? shellRight.size() - 1 : b - 1)) > 0) {
                excludePoints.add(shellRight.get(b));
                b--;
                if (b == -1) {
                    b = shellRight.size() - 1;
                }
                System.out.printf("Rotate b (now b = %d)\n", b);
                bRotate = true;
            }
        } while (aRotate || bRotate);

        excludePoints.remove(shellLeft.get(a));

        return excludePoints;
    }
}
