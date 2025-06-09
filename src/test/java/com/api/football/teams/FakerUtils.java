package com.api.football.teams;

import net.datafaker.Faker;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Locale;

public class FakerUtils {

    private final static Faker faker = new Faker(new Locale("pt", "BR"));

    public static <T> T entity(Class<?> clazz) {
        T target = null;
        try {
            target = (T) clazz.getDeclaredConstructor().newInstance();
            Field[] fields = clazz.getDeclaredFields();

            for (Field field : fields) {
                Object value = null;
                field.setAccessible(true);
                if (field.getType().isAssignableFrom(String.class)) {
                    value = faker.lorem().word();
                } else if (field.getType().isAssignableFrom(Integer.class)) {
                    value = faker.number().randomDigitNotZero();
                } else if (field.getType().isAssignableFrom(Boolean.class)) {
                    value = faker.bool().bool();
                } else if (field.getType().isAssignableFrom(Long.class)) {
                    value = faker.number().randomNumber();
                } else if (field.getType().isAssignableFrom(Double.class)) {
                    value = faker.number().randomDouble(2, 1, 100);
                } else if (field.getType().isAssignableFrom(LocalDate.class)) {
                    value = faker.timeAndDate().birthday();
                } else if (field.getType().isAssignableFrom(BigDecimal.class)){
                    value = BigDecimal.valueOf(faker.number().randomDigitNotZero());
                }

                if(field.getName().equals("id"))
                    value = null;

                field.set(target, value);
            }

            return target;
        } catch (IllegalAccessException | InvocationTargetException |
                 InstantiationException | NoSuchMethodException e) {
            throw new RuntimeException(e);
        }
    }

}
