package model.repository;

import model.entity.Message;
import model.repository.database.PostgresConnectionProvider;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

public class MessageRepository {

    public static void createMessage(String AuthorName, String ReceiverName, String text) {
        try (Connection connection = PostgresConnectionProvider.getConnection()) {
            PreparedStatement statement = connection.prepareStatement("insert into messages (name_of_author, name_of_receiver, message_text) VALUES (?,?,?)");
            statement.setString(1, AuthorName);
            statement.setString(2, ReceiverName);
            statement.setString(3, text);
            statement.execute();
        } catch (SQLException ignored) {
        }
    }

    public static List<Message> getMessages(String AuthorName, String ReceiverName) {
        try (Connection connection = PostgresConnectionProvider.getConnection()) {
            PreparedStatement statement = connection.prepareStatement("select name_of_author, message_text from messages where" +
                    "                     (name_of_author = ? AND name_of_receiver = ?) or (name_of_receiver = ? and name_of_author = ?) order by id;");
            statement.setString(1, AuthorName);
            statement.setString(2, ReceiverName);
            statement.setString(3, AuthorName);
            statement.setString(4, ReceiverName);
            ResultSet set = statement.executeQuery();
            LinkedList<Message> list = new LinkedList<>();
            while (set.next()) {
                list.add(
                        Message.builder()
                                .author(set.getString(1))
                                .text(set.getString(2))
                                .build()
                );
            }
            return list;
        } catch (SQLException e) {
            return null;
        }
    }
}
