package com.challenge.desafio;

import com.challenge.annotation.Somar;
import com.challenge.annotation.Subtrair;
import com.challenge.interfaces.Calculavel;

import java.lang.reflect.Field;
import java.math.BigDecimal;

public class CalculadorDeClasses implements Calculavel {
  @Override
  public BigDecimal somar(Object object) throws IllegalAccessException {
    BigDecimal subTotal = BigDecimal.ZERO;
    Field[] fields = object.getClass().getDeclaredFields();

    for (Field field: fields) {
      field.setAccessible(true);
      if (field.isAnnotationPresent(Somar.class)) {
        subTotal = subTotal.add((BigDecimal) field.get(object));
      }
    }

    return subTotal;
  }

  @Override
  public BigDecimal subtrair(Object object) throws IllegalAccessException {
    BigDecimal subTotal = BigDecimal.ZERO;
    Field[] fields = object.getClass().getDeclaredFields();

    for (Field field: fields) {
      field.setAccessible(true);

      if (field.isAnnotationPresent(Subtrair.class)) {
        subTotal = subTotal.add((BigDecimal) field.get(object));
      }
    }

    return subTotal;
  }

  @Override
  public BigDecimal totalizar(Object object) throws IllegalAccessException {
    return somar(object).subtract(subtrair(object));
  }

  
}
