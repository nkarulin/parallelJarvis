package ru.spb.kns;


import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.Arrays;
import java.util.HashSet;

public class MergeShellsTest {

    @DataProvider(name = "shellsBottom")
    public Object[][] shells() {
        return new Object[][] {
            {
                    new Point[] {
                            new Point(3, 1),
                            new Point(6, 3),
                            new Point(5, 4),
                            new Point(2, 7),
                            new Point(1, 5),
                            new Point(2, 2),
                    },
                    new Point[] {
                            new Point(8, 1),
                            new Point(11, 3),
                            new Point(10, 6),
                            new Point(9, 5),
                            new Point(7, 4),
                    },
                    new Point[] {
                            new Point(6, 3),
                            new Point(7, 4)
                    }
            },
            {
                    new Point[] {
                            new Point(2, 2),
                            new Point(5, 4),
                            new Point(2, 6),
                    },
                    new Point[] {
                            new Point(9, 2),
                            new Point(9, 6),
                            new Point(6, 4),
                    },
                    new Point[] {
                            new Point(5, 4),
                            new Point(6, 4)
                    }
            },
            {
                    new Point[] {
                            new Point(6, 2),
                            new Point(2, 5),
                            new Point(2, 2),
                    },
                    new Point[] {
                            new Point(7, 2),
                            new Point(9, 2),
                            new Point(9, 5),
                    },
                    new Point[] {}
            }
        };
    }

    @Test(dataProvider = "shellsBottom")
    public void testFindBottomLine(Point[] left, Point[] right, Point[] expected) throws Exception {
        Assert.assertEquals(
                RecursiveJarvisTask.findBottomLine(Arrays.asList(left), Arrays.asList(right)),
                new HashSet<>(Arrays.asList(expected))
        );
    }

    @DataProvider(name = "shellsTop")
    public Object[][] shellsTop() {
        return new Object[][] {
                {
                        new Point[] {
                                new Point(3, 1),
                                new Point(6, 3),
                                new Point(5, 4),
                                new Point(2, 7),
                                new Point(1, 5),
                                new Point(2, 2),
                        },
                        new Point[] {
                                new Point(8, 1),
                                new Point(11, 3),
                                new Point(10, 6),
                                new Point(9, 5),
                                new Point(7, 4),
                        },
                        new Point[] {
                                new Point(6, 3),
                                new Point(7, 4),
                                new Point(9, 5),
                                new Point(5, 4)
                        }
                },
                {
                        new Point[] {
                                new Point(2, 2),
                                new Point(5, 4),
                                new Point(2, 6),
                        },
                        new Point[] {
                                new Point(9, 2),
                                new Point(9, 6),
                                new Point(6, 4),
                        },
                        new Point[] {
                                new Point(5, 4),
                                new Point(6, 4)
                        }
                },
                {
                        new Point[] {
                                new Point(6, 2),
                                new Point(2, 5),
                                new Point(2, 2),
                        },
                        new Point[] {
                                new Point(7, 2),
                                new Point(9, 2),
                                new Point(9, 5),
                        },
                        new Point[] {
                                new Point(6, 2),
                                new Point(7, 2)
                        }
                }
        };
    }

    @Test(dataProvider = "shellsTop")
    public void testFindTopLine(Point[] left, Point[] right, Point[] expected) throws Exception {
        Assert.assertEquals(
                RecursiveJarvisTask.findTopLine(Arrays.asList(left), Arrays.asList(right)),
                new HashSet<>(Arrays.asList(expected))
        );
    }

}