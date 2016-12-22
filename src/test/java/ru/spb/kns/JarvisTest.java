package ru.spb.kns;

import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.List;

public class JarvisTest {

    @Test
    public void testJarvis() {
        List<Point> points = new ArrayList<>();
        points.add(new Point(0.1, 1));
        points.add(new Point(1, 0.5));
        points.add(new Point(-0.1, -1));
        points.add(new Point(2, 0));
        points.add(new Point(-1, 1));
        points.add(new Point(0, 0));

        List<Point> shell = Jarvis.findShell(points);

        System.out.println(shell);

        Assert.assertEquals(shell.size(),  4);
        Assert.assertEquals(shell.get(0).x, -1.0);
        Assert.assertEquals(shell.get(1).x, -0.1);
        Assert.assertEquals(shell.get(2).x, 2.0);
        Assert.assertEquals(shell.get(3).x, 0.1);
    }
}
