package model.repository.user;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import model.entity.User;
import model.repository.database.PostgresConnectionProvider;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.sql.*;
import java.util.LinkedList;
import java.util.List;

public class UserRepository {

    public static long getIDByUsername(String username) {
        try (Connection connection = PostgresConnectionProvider.getConnection()) {
            PreparedStatement statement = connection.prepareStatement("select id from users where username=?");
            statement.setString(1, username);
            ResultSet set = statement.executeQuery();
            set.next();
            return set.getLong(1);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static long getIDByEmail(String email) {
        try (Connection connection = PostgresConnectionProvider.getConnection()) {
            PreparedStatement statement = connection.prepareStatement("select id from users where email=?");
            statement.setString(1, email);
            ResultSet set = statement.executeQuery();
            if (set.next()) {
                return set.getLong(1);
            } else return -1;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static String createUser(String username, String password, String email, InputStream fileContent) {
        try (Connection connection = PostgresConnectionProvider.getConnection()) {
            PreparedStatement statement = connection.prepareStatement("insert into users (username, password," +
                    " email, status, wallet) values (?, ?, ?, -1, 0)", Statement.RETURN_GENERATED_KEYS);
            statement.setString(1, username);
            statement.setLong(2, password.hashCode());
            statement.setString(3, email);
            statement.execute();
            ResultSet key = statement.getGeneratedKeys();
            key.next();
            updateImage(fileContent, key.getLong(1));
            return "Successfully";
        } catch (SQLException e) {
            return "Unexpected error during registration";
        } catch (IOException e) {
            return "Unsuspected error during image uploading";
        }
    }

    public static String getUser(String email, String password, HttpServletRequest req) {
        try (Connection connection = PostgresConnectionProvider.getConnection()) {
            HttpSession session = req.getSession(true);
            PreparedStatement statement = connection.prepareStatement("select id, username, password," +
                    " email,status, wallet from users where email = ? and password = ?;");
            statement.setString(1, email);
            statement.setLong(2, password.hashCode());
            ResultSet set = statement.executeQuery();
            if (set.next()) {
                session.setAttribute("User", User.builder()
                        .id(set.getLong(1))
                        .username(set.getString(2))
                        .password(set.getLong(3))
                        .email(set.getString(4))
                        .status(set.getInt(5))
                        .wallet(set.getLong(6))
                        .build());
                return "Successfully";
            } else return "Wrong email or password";
        } catch (SQLException e) {
            return "Unexpected error during authentication";
        }
    }

    public static String getUser(long id, HttpServletRequest req) {
        try (Connection connection = PostgresConnectionProvider.getConnection()) {
            HttpSession session = req.getSession(true);
            PreparedStatement statement = connection.prepareStatement("select id, username, password," +
                    " email from users where id =?;");
            statement.setLong(1, id);
            ResultSet set = statement.executeQuery();
            set.next();
            session.setAttribute("User", User.builder()
                    .id(set.getLong(1))
                    .username(set.getString(2))
                    .password(set.getLong(3))
                    .email(set.getString(4))
                    .build());
            return "Successfully";
        } catch (SQLException e) {
            return "Unexpected error during authentication";
        }
    }

    public static String updateUserPassword(long id, long new_password) {
        try (Connection connection = PostgresConnectionProvider.getConnection()) {
            PreparedStatement statement = connection.prepareStatement("update users set password =? " +
                    " where id = ?");
            statement.setLong(1, new_password);
            statement.setLong(2, id);
            statement.execute();
            return "Successfully";
        } catch (SQLException e) {
            return "Unexpected error during password updating";
        }
    }

    public static String updateUserEmail(long id, String new_email) {
        try (Connection connection = PostgresConnectionProvider.getConnection()) {
            PreparedStatement statement = connection.prepareStatement("update users set email =? " +
                    " where id = ?");
            statement.setString(1, new_email);
            statement.setLong(2, id);
            statement.execute();
            return "Successfully";
        } catch (SQLException e) {
            return "Unexpected error during email updating";
        }
    }

    public static String activateUser(long id) {
        try (Connection connection = PostgresConnectionProvider.getConnection()) {
            PreparedStatement statement = connection.prepareStatement("update users set status = 1 where id =?");
            statement.setLong(1, id);
            statement.execute();
            return "Successfully";
        } catch (SQLException e) {
            return "Unexpected error during profile activation";
        }
    }

    public static String updateUserStatus(long id, int status) {
        try (Connection connection = PostgresConnectionProvider.getConnection()) {
            PreparedStatement statement = connection.prepareStatement("update users set status = ? where id =?");
            statement.setLong(1, status);
            statement.setLong(2, id);
            statement.execute();
            return "Successfully";
        } catch (SQLException e) {
            return "Unexpected error during status updating";
        }
    }

    public static String getUsername(long user_id) {
        try (Connection connection = PostgresConnectionProvider.getConnection()) {
            PreparedStatement statement = connection.prepareStatement("select username from users where id=?");
            statement.setLong(1, user_id);
            ResultSet set = statement.executeQuery();
            set.next();
            return set.getString(1);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static int getUserStatus(long user_id) {
        try (Connection connection = PostgresConnectionProvider.getConnection()) {
            PreparedStatement statement = connection.prepareStatement("select status from users where id=?");
            statement.setLong(1, user_id);
            ResultSet set = statement.executeQuery();
            set.next();
            return set.getInt(1);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static boolean existsUser(long user_id) {
        try (Connection connection = PostgresConnectionProvider.getConnection()) {
            PreparedStatement statement = connection.prepareStatement("select * from users where id=?");
            statement.setLong(1, user_id);
            return statement.executeQuery().next();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static boolean existsUsername(String username) {
        try (Connection connection = PostgresConnectionProvider.getConnection()) {
            PreparedStatement statement = connection.prepareStatement("select * from users where username=?");
            statement.setString(1, username);
            return statement.executeQuery().next();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static User getUser(long id) {
        try (Connection connection = PostgresConnectionProvider.getConnection()) {
            PreparedStatement statement = connection.prepareStatement("select id, username, password," +
                    " email, wallet from users where id =?;");
            statement.setLong(1, id);
            ResultSet set = statement.executeQuery();
            set.next();
            return User.builder()
                    .id(set.getLong(1))
                    .username(set.getString(2))
                    .password(set.getLong(3))
                    .email(set.getString(4))
                    .wallet(set.getLong(5))
                    .build();
        } catch (SQLException e) {
            return null;
        }
    }

    public static void updateImage(InputStream fileContent, long pin_id) throws IOException {
        new File("..\\images").mkdirs();
        File file = new File("..\\images\\" + pin_id + ".png");
        Files.copy(fileContent, file.toPath(), StandardCopyOption.REPLACE_EXISTING);
    }

    public static List<User> getUserList() {
        try (Connection connection = PostgresConnectionProvider.getConnection()) {
            LinkedList<User> list = new LinkedList<>();
            PreparedStatement statement = connection.prepareStatement("SELECT id, username, email, status, wallet from users");
            ResultSet set = statement.executeQuery();
            while (set.next()) {
                list.add(User.builder()
                        .id(set.getLong(1))
                        .username(set.getString(2))
                        .email(set.getString(3))
                        .status(set.getInt(4))
                        .wallet(set.getLong(5))
                        .build());
            }
            return list;
        } catch (SQLException e) {
            return null;
        }
    }

    public static void deleteUsers(LinkedList<User> list) {
        try (Connection connection = PostgresConnectionProvider.getConnection()) {
            for (User user : list
            ) {
                PreparedStatement statement = connection.prepareStatement("delete from users where id=?");
                statement.setLong(1, user.getId());
                statement.execute();
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
