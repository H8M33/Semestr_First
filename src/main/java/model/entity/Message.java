package model.entity;

import lombok.*;
import model.repository.SqlGenerator.ColumnName;
import model.repository.SqlGenerator.TableName;

@AllArgsConstructor
@Data
@Builder
@TableName(nameOfTable = "messages")
public class Message {
    @ColumnName(name = "id", primary = true, identity = true)
    Long id;
    @ColumnName(name = "name_of_author")
    String author;
    @ColumnName(name = "name_of_receiver")
    String receiver;
    @ColumnName(name = "message_text")
    String text;
}
