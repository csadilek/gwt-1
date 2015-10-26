/*
 * Copyright 2016 Google Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not
 * use this file except in compliance with the License. You may obtain a copy of
 * the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * License for the specific language governing permissions and limitations under
 * the License.
 */

package com.google.gwt.emultest.java8.util.stream;

import java.util.ArrayList;
import java.util.List;
import java.util.OptionalInt;
import java.util.OptionalLong;
import java.util.function.IntSupplier;
import java.util.function.LongSupplier;
import java.util.function.Supplier;
import java.util.stream.LongStream;

/**
 * Tests {@link LongStream}.
 */
public class LongStreamTest extends StreamTestBase {

  public void testEmptyStream() {
    LongStream empty = LongStream.empty();
    assertEquals(0, empty.count());
    try {
      empty.count();
      fail("second terminal operation should have thrown IllegalStateEx");
    } catch (IllegalStateException expected) {
      // expected
    }

    assertEquals(0, LongStream.empty().limit(2).toArray().length);
    assertEquals(0L, LongStream.empty().count());
    assertEquals(0L, LongStream.empty().limit(2).count());

    assertFalse(LongStream.empty().findFirst().isPresent());
    assertFalse(LongStream.empty().findAny().isPresent());
    assertFalse(LongStream.empty().max().isPresent());
    assertFalse(LongStream.empty().min().isPresent());
    assertTrue(LongStream.empty().noneMatch(item -> true));
    assertTrue(LongStream.empty().allMatch(item -> true));
    assertFalse(LongStream.empty().anyMatch(item -> true));
    assertEquals(new long[0], LongStream.empty().toArray());
  }

  public void testStreamOfOne() {
    Supplier<LongStream> one = () -> LongStream.of(1);
    assertEquals(new long[]{1}, one.get().toArray());
    assertEquals(1L, one.get().count());
    assertEquals(1, one.get().findFirst().getAsLong());
    assertEquals(1, one.get().findAny().getAsLong());
  }

  public void testBuilder() {
    LongStream s = LongStream.builder()
        .add(1)
        .add(3)
        .add(2)
        .build();

    assertEquals(
        new long[] {1, 3, 2},
        s.toArray()
    );

    LongStream.Builder builder = LongStream.builder();
    LongStream built = builder.build();
    assertEquals(0, built.count());
    try {
      builder.build();
      fail("build() after build() should fail");
    } catch (IllegalStateException expected) {
      // expected
    }
    try {
      builder.add(10);
      fail("add() after build() should fail");
    } catch (IllegalStateException expected) {
      // expected
    }
  }

  public void testGenerate() {
    // infinite, but if you limit it is already too short to skip much
    assertEquals(new long[0], LongStream.generate(makeGenerator()).limit(4).skip(5).toArray());

    assertEquals(new long[]{10, 11, 12, 13, 14}, LongStream.generate(makeGenerator()).skip(10).limit(5).toArray());
  }

  private LongSupplier makeGenerator() {
    return new LongSupplier() {
      long next = 0;

      @Override
      public long getAsLong() {
        return next++;
      }
    };
  }

  public void testRange() {
    assert false : "TODO";
  }

  public void testToArray() {
    assertEquals(new long[0], LongStream.of().toArray());
    assertEquals(new long[]{1}, LongStream.of(1).toArray());
    assertEquals(new long[]{3,2,0}, LongStream.of(3,2,0).toArray());
  }

  public void testReduce() {
    long reduced = LongStream.of(1, 2, 4).reduce(0, Long::sum);
    assertEquals(7, reduced);

    reduced = LongStream.of().reduce(0, Long::sum);
    assertEquals(0, reduced);

    OptionalLong maybe = LongStream.of(1, 4, 8).reduce(Long::sum);
    assertTrue(maybe.isPresent());
    assertEquals(13, maybe.getAsLong());
    maybe = LongStream.of().reduce(Long::sum);
    assertFalse(maybe.isPresent());
  }

  public void testFilter() {
    // unconsumed stream never runs filter
    boolean[] data = {false};
    LongStream.of(1, 2, 3).filter(i -> data[0] |= true);
    assertFalse(data[0]);

    // one result
    assertEquals(
        new long[]{1},
        LongStream.of(1, 2, 3, 4, 3).filter(a -> a == 1).toArray()
    );
    // zero results
    assertEquals(
        new long[0],
        LongStream.of(1, 2, 3, 4, 3).filter(a -> false).toArray()
    );
    // two results
    assertEquals(
        new long[] {2, 4},
        LongStream.of(1, 2, 3, 4, 3).filter(a -> a % 2 == 0).toArray()
    );
    // all
    assertEquals(
        new long[] {1, 2, 3, 4, 3},
        LongStream.of(1, 2, 3, 4, 3).filter(a -> true).toArray()
    );
  }

  public void testMap() {
    // unconsumed stream never runs map
    int[] data = {0};
    LongStream.of(1, 2, 3).map(i -> data[0]++);
    assertEquals(0, data[0]);

    assertEquals(
        new long[] {2, 4, 6},
        LongStream.of(1, 2, 3).map(i -> i * 2).toArray()
    );
  }

  public void testPeek() {
    // unconsumed stream never peeks
    boolean[] data = {false};
    LongStream.of(1, 2, 3).peek(i -> data[0] |= true);
    assertFalse(data[0]);

    // make sure we saw it all in order
    long[] items = new long[] {1, 2, 3};
    List<Long> peeked = new ArrayList<>();
    LongStream.of(items).peek(item -> peeked.add(item)).forEach(item -> {
      // do nothing, just run
    });
    assertEquals(items.length, peeked.size());
    for (int i = 0; i < items.length; i++) {
      assertEquals(items[i], (long) peeked.get(i));
    }
  }

  // same impl, no parallel in browser
  public void testFindFirstOrAny() {
    OptionalLong any = LongStream.of(1, 2).findAny();
    assertTrue(any.isPresent());
    assertEquals(1, any.getAsLong());
  }

  public void testAnyMatch() {
    // all
    assertTrue(LongStream.of(1, 2).anyMatch(s -> true));

    // some
    assertTrue(LongStream.of(1, 2).anyMatch(s -> s == 1));

    // none
    assertFalse(LongStream.of(1, 2).anyMatch(s -> false));
  }

  public void testAllMatch() {
    // all
    assertTrue(LongStream.of(1, 2).allMatch(s -> true));

    // some
    assertFalse(LongStream.of(1, 2).allMatch(s -> s == 1));

    // none
    assertFalse(LongStream.of(1, 2).allMatch(s -> false));
  }

  public void testNoneMatch() {
    // all
    assertFalse(LongStream.of(1, 2).noneMatch(s -> true));

    // some
    assertFalse(LongStream.of(1, 2).noneMatch(s -> s == 1));

    // none
    assertTrue(LongStream.of(1, 2).noneMatch(s -> false));
  }

  public void testFlatMap() {
    assertEquals(0, LongStream.empty().flatMap(value -> LongStream.of(1)).count());
    assertEquals(0, LongStream.of(1).flatMap(value -> LongStream.empty()).count());
    assertEquals(0, LongStream.of(1).flatMap(value -> LongStream.of()).count());
    assertEquals(0, LongStream.of().flatMap(value -> LongStream.of(1)).count());
    assertEquals(1, LongStream.of(1).flatMap(value -> LongStream.of(1)).count());

    LongStream values = LongStream.of(1, 2, 3);

    assertEquals(
        new long[] {1, 2, 2, 4, 3, 6},
        values.flatMap(i -> LongStream.of(i, i * 2)).toArray()
    );
  }

  public void testMapToOthers() {
    Supplier<LongStream> s = () -> LongStream.of(1, 2, 10);

    assertEquals(
        new String[]{"1", "2", "10"},
        s.get().mapToObj(String::valueOf).toArray(String[]::new)
    );

    assertEquals(
        new int[]{1, 2, 10},
        s.get().mapToInt(i -> (int) i).toArray()
    );

    assertEquals(
        new double[]{1, 2, 10},
        s.get().mapToDouble(i -> (double) i).toArray()
    );
  }

  public void testDistinct() {
    long[] distinct = LongStream.of(1, 2, 3, 2).distinct().toArray();
    assertEquals(3, distinct.length);
    assertEquals(1 + 2 + 3, distinct[0] + distinct[1] + distinct[2]);
  }

  public void testSorted() {
    long[] sorted = LongStream.of(3, 1, 2).sorted().toArray();

    assertEquals(new long[] {1, 2, 3}, sorted);
  }

  public void testMinMax() {
    Supplier<LongStream> stream = () -> LongStream.of(2, 3, 4, 1);

    assertEquals(1, stream.get().min().orElse(0));
    assertEquals(4, stream.get().max().orElse(0));

    assertFalse(stream.get().filter(a -> false).max().isPresent());
    assertFalse(stream.get().filter(a -> false).min().isPresent());
  }

  public void testCountLimitSkip() {
    Supplier<LongStream> stream = () -> LongStream.of(1, 2, 3, 4);

    assertEquals(4, stream.get().count());

    assertEquals(4, stream.get().limit(4).count());
    assertEquals(4, stream.get().limit(5).count());
    assertEquals(3, stream.get().limit(3).count());

    assertEquals(3, stream.get().skip(1).limit(3).count());

    assertEquals(2, stream.get().limit(3).skip(1).count());

    assertEquals(1, stream.get().skip(3).count());

    assertEquals(new long[]{3, 4}, stream.get().skip(2).limit(3).toArray());
    assertEquals(new long[]{3}, stream.get().skip(2).limit(1).toArray());

    assertEquals(new long[]{4}, stream.get().skip(3).toArray());
    assertEquals(new long[]{}, stream.get().skip(5).toArray());

    assertEquals(new long[]{1, 2}, stream.get().limit(2).toArray());

    assertEquals(new long[]{2}, stream.get().limit(2).skip(1).toArray());
  }

  // TODO
  public void testBoxed() {
    assert false : "TODO";
  }

  public void testAsOtherPrimitive() {
    assert false : "TODO";
  }

  public void testStats() {
    assert false : "TODO";
  }

  public void testAverage() {
    assert false : "TODO";
  }

  public void testSum() {
    assert false : "TODO";
  }

  public void testCollect() {
    assert false : "TODO";
  }

  public void testForEach() {
    assert false : "TODO";
  }

  public void testIterator() {
    assert false : "TODO";
  }

  public void testSpliterator() {
    assert false : "TODO";
  }
}
