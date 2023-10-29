package ru.otus.javapro.homeworks.hw10homemadeorm.jdbc.mapper;

import java.lang.reflect.Field;
import java.util.stream.Collectors;

public class EntitySQLMetaDataImpl <T> implements EntitySQLMetaData {

    EntityClassMetaData<T> entityClassMetaData;

    public EntitySQLMetaDataImpl(EntityClassMetaData<T> entityClassMetaData) {
        this.entityClassMetaData = entityClassMetaData;
    }

    @Override
    public String getSelectAllSql() {
        return String.format("select %s from %s",
                entityClassMetaData.getAllFields().stream().map(Field::getName).collect(Collectors.joining(", ")),
                entityClassMetaData.getName());
    }

    @Override
    public String getSelectByIdSql() {
        return String.format("select %s from %s where %s = ?",
                entityClassMetaData.getAllFields().stream().map(Field::getName).collect(Collectors.joining(", ")),
                entityClassMetaData.getName(),
                entityClassMetaData.getIdField().getName());

    }

    @Override
    public String getInsertSql() {
        return String.format("insert into %s(%s) values (%s)", entityClassMetaData.getName(),
                entityClassMetaData.getFieldsWithoutId().stream().map(Field::getName).collect(Collectors.joining(", ")),
                entityClassMetaData.getFieldsWithoutId().stream().map(o -> "?").collect(Collectors.joining(", ")));
    }

    @Override
    public String getUpdateSql() {
        return String.format("update %s set %s where %s = ?",
                entityClassMetaData.getName(),
                entityClassMetaData.getAllFields().stream().map(field -> String.format("%s = ?", field)).collect(Collectors.joining(", ")),
                entityClassMetaData.getIdField().getName());
    }

}
