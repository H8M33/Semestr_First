package model.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import model.repository.SqlGenerator.ColumnName;
import model.repository.SqlGenerator.TableName;


@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
@TableName(nameOfTable = "users")
public class User {
    @ColumnName(name = "id",identity = true,primary = true)
    Long id;
    @ColumnName(name = "username",uniq = true,notNull = true)
    String username;
    @ColumnName(name = "email",uniq = true, notNull = true)
    String email;
    @ColumnName(name = "password",notNull = true)
    Long password;
    @ColumnName(name = "status", notNull = true)
    Integer status;
    @ColumnName(name = "wallet", notNull = true)
    Long wallet;
}
