package com.example.stmlabs.exception;

/**
 * Исключение используется если элемент не найден.
 */
public class ElemNotFound extends RuntimeException {

  public ElemNotFound() {  }

  public ElemNotFound(String textException) {super(textException);
  }
}

