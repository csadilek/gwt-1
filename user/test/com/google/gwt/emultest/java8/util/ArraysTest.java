package com.google.gwt.emultest.java8.util;

import com.google.gwt.emultest.java.util.EmulTestBase;

import java.util.Arrays;

/**
 * Java8 tests for {@link Arrays}.
 */
public class ArraysTest extends EmulTestBase {
  public void testArrayStreamInt() {
    int[] six = {1, 2, 3, 4, 5, 6};

    // zero entries
    assertEquals(new int[]{}, Arrays.stream(six, 0, 0).toArray());
    assertEquals(new int[]{}, Arrays.stream(six, 1, 1).toArray());

    // single entry
    assertEquals(new int[]{1}, Arrays.stream(six, 0, 1).toArray());
    assertEquals(new int[]{2}, Arrays.stream(six, 1, 2).toArray());

    // multiple entries
    assertEquals(new int[]{1, 2, 3}, Arrays.stream(six, 0, 3).toArray());
    assertEquals(new int[]{4, 5, 6}, Arrays.stream(six, 3, 6).toArray());


    try {
      // start < 0
      Arrays.stream(six, -1, 1);
      fail("expected aioobe");
    } catch (ArrayIndexOutOfBoundsException e) {
      // pass
    }
    try {
      // end < start
      Arrays.stream(six, 2, 1);
      fail("expected aioobe");
    } catch (ArrayIndexOutOfBoundsException e) {
      // pass
    }
    try {
      // end > size
      Arrays.stream(six, 0, 7);
      fail("expected aioobe");
    } catch (ArrayIndexOutOfBoundsException e) {
      // pass
    }
  }

  protected void assertEquals(int[] expected, int[] actual) {
    assertTrue("expected: " + Arrays.toString(expected) + ", saw " + Arrays.toString(actual), Arrays.equals(expected, actual));
  }
  public void testArrayStreamLong() {
    long[] six = {1, 2, 3, 4, 5, 6};

    // zero entries
    assertEquals(new long[]{}, Arrays.stream(six, 0, 0).toArray());
    assertEquals(new long[]{}, Arrays.stream(six, 1, 1).toArray());

    // single entry
    assertEquals(new long[]{1}, Arrays.stream(six, 0, 1).toArray());
    assertEquals(new long[]{2}, Arrays.stream(six, 1, 2).toArray());

    // multiple entries
    assertEquals(new long[]{1, 2, 3}, Arrays.stream(six, 0, 3).toArray());
    assertEquals(new long[]{4, 5, 6}, Arrays.stream(six, 3, 6).toArray());


    try {
      // start < 0
      Arrays.stream(six, -1, 1);
      fail("expected aioobe");
    } catch (ArrayIndexOutOfBoundsException e) {
      // pass
    }
    try {
      // end < start
      Arrays.stream(six, 2, 1);
      fail("expected aioobe");
    } catch (ArrayIndexOutOfBoundsException e) {
      // pass
    }
    try {
      // end > size
      Arrays.stream(six, 0, 7);
      fail("expected aioobe");
    } catch (ArrayIndexOutOfBoundsException e) {
      // pass
    }
  }

  protected void assertEquals(long[] expected, long[] actual) {
    assertTrue("expected: " + Arrays.toString(expected) + ", saw " + Arrays.toString(actual), Arrays.equals(expected, actual));
  }
  public void testArrayStreamDouble() {
    double[] six = {1, 2, 3, 4, 5, 6};

    // zero entries
    assertEquals(new double[]{}, Arrays.stream(six, 0, 0).toArray());
    assertEquals(new double[]{}, Arrays.stream(six, 1, 1).toArray());

    // single entry
    assertEquals(new double[]{1}, Arrays.stream(six, 0, 1).toArray());
    assertEquals(new double[]{2}, Arrays.stream(six, 1, 2).toArray());

    // multiple entries
    assertEquals(new double[]{1, 2, 3}, Arrays.stream(six, 0, 3).toArray());
    assertEquals(new double[]{4, 5, 6}, Arrays.stream(six, 3, 6).toArray());


    try {
      // start < 0
      Arrays.stream(six, -1, 1);
      fail("expected aioobe");
    } catch (ArrayIndexOutOfBoundsException e) {
      // pass
    }
    try {
      // end < start
      Arrays.stream(six, 2, 1);
      fail("expected aioobe");
    } catch (ArrayIndexOutOfBoundsException e) {
      // pass
    }
    try {
      // end > size
      Arrays.stream(six, 0, 7);
      fail("expected aioobe");
    } catch (ArrayIndexOutOfBoundsException e) {
      // pass
    }
  }

  protected void assertEquals(double[] expected, double[] actual) {
    assertTrue("expected: " + Arrays.toString(expected) + ", saw " + Arrays.toString(actual), Arrays.equals(expected, actual));
  }

  public void testArrayStreamObject() {
    String[] six = {"1", "2", "3", "4", "5", "6"};

    // zero entries
    assertEquals(new String[]{}, Arrays.stream(six, 0, 0).toArray());
    assertEquals(new String[]{}, Arrays.stream(six, 1, 1).toArray());

    // single entry
    assertEquals(new String[]{"1"}, Arrays.stream(six, 0, 1).toArray());
    assertEquals(new String[]{"2"}, Arrays.stream(six, 1, 2).toArray());

    // multiple entries
    assertEquals(new String[]{"1", "2", "3"}, Arrays.stream(six, 0, 3).toArray());
    assertEquals(new String[]{"4", "5", "6"}, Arrays.stream(six, 3, 6).toArray());


    try {
      // start < 0
      Arrays.stream(six, -1, 1);
      fail("expected aioobe");
    } catch (ArrayIndexOutOfBoundsException e) {
      // pass
    }
    try {
      // end < start
      Arrays.stream(six, 2, 1);
      fail("expected aioobe");
    } catch (ArrayIndexOutOfBoundsException e) {
      // pass
    }
    try {
      // end > size
      Arrays.stream(six, 0, 7);
      fail("expected aioobe");
    } catch (ArrayIndexOutOfBoundsException e) {
      // pass
    }
  }

}
