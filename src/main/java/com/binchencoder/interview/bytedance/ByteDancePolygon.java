package com.binchencoder.interview.bytedance;

/**
 * Created by chenbin at 2019/11/04
 *
 * 题目内容来源: ByteDance/bytedance20191104.jpg
 */
public class ByteDancePolygon {

  /**
   * 计算边的长度
   */
  static float lineLength(Point a, Point b) {
    float length;
    if (a.x == b.x) { // 垂直线条
      length = Math.abs(a.y - b.y);
    } else {
      length = Math.abs(a.x - b.x);
    }

    return length;
  }

  /**
   * 计算周长
   */
  static float totalSideLength(Point[] points, Line[] lines) {
    float side = 0;

    for (int i = 1; i < points.length; i++) {
      Point point = points[i];
      Point prev = points[i - 1];

      float length = lineLength(prev, point);
      side += length;
      lines[i - 1] = new Line(prev, point, length);

      if (i == points.length - 1) {
        length = lineLength(point, points[0]);

        side += length;
        lines[i] = new Line(point, points[0], length);
      }
    }

    return side;
  }

  static Point[] division(Point[] points, int divisionNum) {
    Point[] divisionPoints = new Point[divisionNum];

    // 计算周长
    Line[] lines = new Line[points.length];
    float side = totalSideLength(points, lines);

    // 等分长度
    float divisionLength = side / divisionNum;

    int lineIndex = -1;
    float sumLength = 0;

    for (int i = 0; i < divisionNum; i++) {
      if (i == 0) {
        // 第一个等分点直接是起始点坐标
        divisionPoints[i] = new Point(points[0]);
        continue;
      }

      divisionPoints[i] = new Point();
      float lineLength = divisionLength * i;

      while (true) {
        Line line;
        if (sumLength < lineLength) {
          lineIndex++;
          line = lines[lineIndex];
          sumLength += line.length;
        } else {
          line = lines[lineIndex];
        }

        if (sumLength >= lineLength) {
          float temp = sumLength - lineLength;

          if (line.begin.x == line.end.x) {
            // begin和end的坐标点垂直
            divisionPoints[i].x = line.begin.x;

            if (line.end.y > line.begin.y) {
              divisionPoints[i].y = line.end.y - temp;
            } else {
              divisionPoints[i].y = line.end.y + temp;
            }
          } else {
            // begin和end的坐标点水平
            divisionPoints[i].y = line.end.y;

            if (line.end.x > line.begin.x) {
              divisionPoints[i].x = line.end.x - temp;
            } else {
              divisionPoints[i].x = line.end.x + temp;
            }
          }

          break;
        }
      }
    }

    return divisionPoints;
  }

  static void print(Point[] points) {
    for (int i = 0; i < points.length; i++) {
      System.out.println("第" + (i + 1) + "等分点, x：" + points[i].x + "，y：" + points[i].y);
    }
  }

  public static void main(String[] args) {
    Point[] points = new Point[]{new Point(0, 0), new Point(0, 1), new Point(1, 1),
        new Point(1, 0)};

    Point[] divPoints = division(points, 8);

    print(divPoints);
  }
}

/**
 * 定义坐标类
 */
class Point {

  float x;
  float y;

  public Point() {
  }

  public Point(float x, float y) {
    this.x = x;
    this.y = y;
  }

  public Point(Point point) {
    this(point.x, point.y);
  }

  @Override
  public String toString() {
    return "Point, x：" + x + " y：" + y;
  }
}

/**
 * N边形的边封装类
 */
class Line {

  Point begin;
  Point end;
  float length;

  public Line() {

  }

  public Line(Point begin, Point end, float length) {
    this.begin = begin;
    this.end = end;
    this.length = length;
  }
}
