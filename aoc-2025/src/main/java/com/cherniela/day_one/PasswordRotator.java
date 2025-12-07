package com.cherniela.day_one;

import com.cherniela.utils.AdventOfCode;

import java.io.IOException;
import java.util.List;
import java.util.stream.IntStream;

public class PasswordRotator {
  static void main() throws IOException {
    var lines = AdventOfCode.getAllLines("day1-1.txt");
    System.out.println(calculatePassword(lines));
    System.out.println(calculateStep2Password(lines));
  }

  static class Rotator {
    int currentPosition = 50;
    int zeros = 0;

    void rotateRight() {
      currentPosition++;
      if (currentPosition == 100) {
        currentPosition = 0;
      }
    }

    void rotateLeft() {
      currentPosition--;
      if (currentPosition == -1) {
        currentPosition = 99;
      }
    }

    private void check() {
      if (currentPosition == 0) {
        zeros++;
      }
    }

    void rotate(Direction direction) {
      switch (direction) {
        case LEFT -> rotateLeft();
        case RIGHT -> rotateRight();
      }
    }
  }
  record Rotation(Direction direction, int steps) {}

  static int calculatePassword(List<String> testInput) {
    var steps = getRotations(testInput);

    var rotator = new Rotator();

    for (var step : steps) {
      IntStream.range(0, step.steps).forEach(_ -> rotator.rotate(step.direction));
      rotator.check();
    }
    return rotator.zeros;
  }

  private static List<Rotation> getRotations(List<String> testInput) {
    return testInput.stream()
                    .map(line -> new Rotation(Direction.getFromChar(line.charAt(0)), Integer.parseInt(line.substring(1))))
                    .toList();
  }

  static int calculateStep2Password(List<String> input) {
    var steps = getRotations(input);

    var rotator = new Rotator();

    for (var step : steps) {
      IntStream.range(0, step.steps).forEach(_ -> {rotator.rotate(step.direction); rotator.check();});
    }
    return rotator.zeros;
  }

  enum Direction {
    LEFT,
    RIGHT;

    static Direction getFromChar(char c) {
      return switch (c) {
        case 'L' -> LEFT;
        case 'R' -> RIGHT;
        default -> throw new IllegalArgumentException();
      };
    }
  }
}
