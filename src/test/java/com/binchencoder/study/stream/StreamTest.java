package com.binchencoder.study.stream;

import java.util.List;
import java.util.stream.Collectors;
import org.junit.BeforeClass;
import org.junit.Test;
import org.testng.collections.Lists;

/**
 * Created by chenbin on 18-5-3.
 */
public class StreamTest {

  public static List<Stream> lst = Lists.newArrayList();

  @BeforeClass
  public static void init() {
    for (int i = 0; i < 100; i++) {
      lst.add(new Stream(i, "stream" + i));
    }

    lst.add(null);
  }

  @Test
  public void compareParallelStreamAndStream() {
    long start = System.currentTimeMillis();
    List<Long> ids = lst.parallelStream()
        .filter(stream -> null != stream && stream.getId() % 2 == 1)
        .map(Stream::getId)
        .collect(Collectors.toList());
    System.out.println("parallelStream cost time:" + (System.currentTimeMillis() - start));

    long start1 = System.currentTimeMillis();
    List<Long> ids1 = lst.stream()
        .filter(stream -> null != stream && stream.getId() % 2 == 1)
        .map(Stream::getId).collect(
            Collectors.toList());
    System.out.println("stream cost time:" + (System.currentTimeMillis() - start1));
  }
}
