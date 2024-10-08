package dev.j3fftw.litexpansion.utils;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;

public final class Reflections {

  private Reflections() {}

  public static void setStaticField(
      @Nonnull Class<?> clazz, @Nonnull String fieldName, @Nonnull Object newValue) {
    try {
      final Field f = clazz.getDeclaredField(fieldName);
      f.setAccessible(true); // NOSONAR

      f.set(null, newValue); // NOSONAR
    } catch (ReflectiveOperationException e) {
      Log.warn("Failed to change field {} to {} in {}", fieldName, newValue, clazz.getSimpleName());
    }
  }

  public static void setField(
      @Nullable Object instance, @Nonnull String fieldName, @Nonnull Object newValue) {
    if (instance == null) return;
    try {
      final Field f = instance.getClass().getDeclaredField(fieldName);
      f.setAccessible(true); // NOSONAR

      f.set(instance, newValue); // NOSONAR
    } catch (ReflectiveOperationException e) {
      Log.warn(
          "Failed to change field {} to {} in {}",
          fieldName,
          newValue,
          instance.getClass().getSimpleName());
    }
  }

  public static @org.jetbrains.annotations.Nullable Object getField(
      @Nonnull Class<?> clazz, @Nullable Object instance, @Nonnull String fieldName) {
    try {
      final Field f = clazz.getDeclaredField(fieldName);
      f.setAccessible(true); // NOSONAR

      return f.get(instance);
    } catch (ReflectiveOperationException e) {
      Log.warn("Failed to get field {} in {}", fieldName, clazz.getSimpleName());
      return null;
    }
  }

  public static <T> @org.jetbrains.annotations.Nullable T getField(
      @Nonnull Object instance, @Nonnull Field field) {
    try {
      return (T) field.get(instance);
    } catch (ReflectiveOperationException e) {
      Log.warn("Failed to get field value {} from {}", field, instance.getClass().getSimpleName());
      return null;
    }
  }

  public static @org.jetbrains.annotations.Nullable Field getRawField(
      @Nonnull Class<?> clazz, @Nonnull String fieldName) {
    try {
      final Field f = clazz.getDeclaredField(fieldName);
      f.setAccessible(true); // NOSONAR

      return f;
    } catch (ReflectiveOperationException e) {
      Log.warn("Failed to get field {} in {}", fieldName, clazz.getSimpleName());
      return null;
    }
  }

  public static void invoke(
      @Nonnull Class<?> clazz, @Nullable Object instance, @Nonnull String methodName) {
    try {
      final Method m = clazz.getDeclaredMethod(methodName);
      m.setAccessible(true); // NOSONAR

      m.invoke(instance);
    } catch (ReflectiveOperationException e) {
      Log.warn("Failed to invoke method {} in {}", methodName, clazz.getSimpleName());
    }
  }
}
