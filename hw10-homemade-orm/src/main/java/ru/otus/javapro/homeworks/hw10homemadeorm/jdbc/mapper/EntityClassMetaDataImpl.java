package ru.otus.javapro.homeworks.hw10homemadeorm.jdbc.mapper;

import ru.otus.javapro.homeworks.hw10homemadeorm.annotations.IdField;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.List;

public class EntityClassMetaDataImpl <T> implements EntityClassMetaData<T> {

    private final Class<T> entityClass;

    public EntityClassMetaDataImpl(Class<T> entityClass) {
        this.entityClass = entityClass;
    }

    @Override
    public String getName() {
        return entityClass.getSimpleName();
    }

    @Override
    public Constructor<T> getConstructor() {
        try {
            return entityClass.getDeclaredConstructor(Arrays.stream(entityClass.getDeclaredFields()).map(Field::getType).toArray(Class[]::new));
        } catch (NoSuchMethodException e) {
            throw new RuntimeException("No default constructor found for " + entityClass.getName(), e);
        }
    }

    @Override
    public Field getIdField() {
        for (Field field : entityClass.getDeclaredFields()) {
            if (field.getAnnotation(IdField.class) != null) {
                return field;
            }
        }
        return null;
    }

    @Override
    public List<Field> getAllFields() {
        return Arrays.asList(entityClass.getDeclaredFields());
    }

    @Override
    public List<Field> getFieldsWithoutId() {
        return Arrays.stream(entityClass.getDeclaredFields()).filter(field -> field.getAnnotation(IdField.class) == null).toList();
    }

}
