package ru.otus.javapro.homeworks.hw10homemadeorm.jdbc.mapper;

import ru.otus.javapro.homeworks.hw10homemadeorm.core.repository.DataTemplate;
import ru.otus.javapro.homeworks.hw10homemadeorm.core.repository.DataTemplateException;
import ru.otus.javapro.homeworks.hw10homemadeorm.core.repository.executor.DbExecutor;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * Сохратяет объект в базу, читает объект из базы
 */
public class DataTemplateJdbc<T> implements DataTemplate<T> {

    private final DbExecutor dbExecutor;
    private final EntitySQLMetaData entitySQLMetaData;
    private final EntityClassMetaData<T> entityClassMetaData;

    public DataTemplateJdbc(DbExecutor dbExecutor, EntitySQLMetaData entitySQLMetaData, EntityClassMetaData<T> entityClassMetaData) {
        this.dbExecutor = dbExecutor;
        this.entitySQLMetaData = entitySQLMetaData;
        this.entityClassMetaData = entityClassMetaData;
    }

    @Override
    public Optional<T> findById(Connection connection, long id) {

        return dbExecutor.executeSelect(connection, entitySQLMetaData.getSelectByIdSql(), List.of(id), rs -> {
            try {
                if (rs.next()) {
                    List<Object> listOfParameters = new ArrayList<>();
                    for(Field field : entityClassMetaData.getAllFields()){
                        listOfParameters.add(rs.getObject(field.getName(), field.getType()));
                    }
                    return entityClassMetaData.getConstructor().newInstance(listOfParameters.toArray());
                }
                return null;
            } catch (SQLException e) {
                throw new DataTemplateException(e);
            } catch (InvocationTargetException | InstantiationException | IllegalAccessException e) {
                throw new RuntimeException(e);
            }
        });
    }

    @Override
    public List<T> findAll(Connection connection) {
        throw new UnsupportedOperationException();
    }

    @Override
    public long insert(Connection connection, T client) {

        List<Field> fields = entityClassMetaData.getFieldsWithoutId();
        List<Object> fieldValues = new ArrayList<>();

        for (Field field : fields) {
            try {
                field.setAccessible(true);
                Object value = field.get(client);
                fieldValues.add(value);
            } catch (IllegalAccessException e) {
                throw new DataTemplateException(e);
            }
        }

        return dbExecutor.executeStatement(connection, entitySQLMetaData.getInsertSql(), fieldValues);
    }

    @Override
    public void update(Connection connection, T client) {
        throw new UnsupportedOperationException();
    }
}
