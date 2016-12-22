package ru.spb.kns;

import java.util.ArrayList;
import java.util.List;

public class Jarvis {

    public static List<Point> findShell(List<Point> points) {
        setLeftmostPointFirst(points);

        List<Point> shell = new ArrayList<Point>();
        shell.add(points.get(0));
        points.remove(0);
        points.add(shell.get(0));

        while (true) {
            int right = 0;
            for (int i = 1; i < points.size(); i++) {
                if (rotate(shell.get(shell.size() - 1), points.get(right), points.get(i)) < 0) {
                    right = i;
                }
            }

            if (points.get(right) == shell.get(0)) {
                break;
            }

            shell.add(points.get(right));
            points.remove(right);
        }

        return shell;
    }

    private static void setLeftmostPointFirst(List<Point> points) {
        for (int i = 0; i < points.size(); i++) {
            if (points.get(i).x < points.get(0).x) {
                //если i-ая точка лежит левее 0-ой точки
                //меняем местами номера этих точек
                Point prevFirst = points.get(0);
                points.add(0, points.get(i));
                points.add(i, prevFirst);
            }
        }
    }

    private static double rotate(Point a, Point b, Point c) {
        return (b.x - a.x) * (c.y - b.y) - (b.y - a.y) * (c.x - b.x);
    }
}
