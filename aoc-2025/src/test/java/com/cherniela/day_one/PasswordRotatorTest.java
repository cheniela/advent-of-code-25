package com.cherniela.day_one;

import org.junit.jupiter.api.*;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class PasswordRotatorTest {
  @Test
  void should_calculateZeros() {
    assertEquals(3, PasswordRotator.calculatePassword(getTestInput()));
  }

  @Test
  void should_calculateMoreZeros() {
    assertEquals(6, PasswordRotator.calculateStep2Password(getTestInput()));
  }

  List<String> getTestInput() {
    return """
           L68
           L30
           R48
           L5
           R60
           L55
           L1
           L99
           R14
           L82
           """.lines().toList();
  }
}