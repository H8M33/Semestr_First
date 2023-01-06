package model.repository.SqlGenerator;

import java.lang.reflect.Field;
import java.sql.Time;
import java.util.Date;

public class SqlGenerator {
    public static <T> String createTable(Class<T> entityClass) {
        StringBuilder str;
        str = new StringBuilder("CREATE TABLE IF NOT EXISTS " + entityClass.getDeclaredAnnotation(TableName.class).nameOfTable() + "" +
                "(");
        Field[] fields = entityClass.getDeclaredFields();
        for (int i = 0; i < fields.length; i++) {
            if (!fields[i].getDeclaredAnnotation(ColumnName.class).toIgnore()) {
                str.append(stringBuilder(fields[i]));
                if (fields[i].getDeclaredAnnotation(ColumnName.class).notNull()) {
                    str.append(" NOT NULL");
                }
                if (i != fields.length - 1) {
                    str.append(",");
                }
            }
        }
        str.append(");");
        return str.toString();
    }


    private static String stringBuilder(Field field) {
        String column;
        ColumnName columnName = field.getDeclaredAnnotation(ColumnName.class);
        column = "" + columnName.name() + "";

        if (columnName.identity()) {
            column = column + " bigserial";
            if (columnName.primary()) {
                column = column + " primary key";
            }
        } else if (field.getType().equals(Long.class)) {
            column = column + " bigint";
            if (columnName.primary()) {
                column = column + " primary key";
            }

        } else if (field.getType().equals(Integer.class)) {
            column = column + " INT";
            if (columnName.primary() && columnName.identity()) {
                column = column + " primary key";
            }
            if (columnName.uniq()) {
                column = column + " UNIQUE";
            }
        } else if (field.getType().equals(String.class)) {
            column = column + " varchar";
            if (columnName.uniq()) {
                column = column + " UNIQUE";
            }
        } else if (field.getType().equals(Boolean.TYPE)) {
            if (field.getDeclaredAnnotation(ColumnName.class).defaultBooleanFalse()) {
                column = column + " BOOLEAN DEFAULT false";
            } else if (field.getDeclaredAnnotation(ColumnName.class).defaultBooleanTrue()) {
                column = column + " BOOLEAN DEFAULT true";
            } else {
                column = column + " BOOLEAN";
            }
        } else if (field.getType().equals(Date.class)) {
            column = column + " date";
        } else if (field.getType().equals(Time.class)) {
            column = column + " time";
        }
        return column;

    }
}
