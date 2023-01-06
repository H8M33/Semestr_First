package model.repository.blackjack;

import model.entity.BlackJack.BlackJackGame;
import model.entity.BlackJack.BlackJackPlayer;
import model.repository.database.PostgresConnectionProvider;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.TreeMap;

public class BlackJackRepository {

    public static void createGame(long id){
        try (Connection connection = PostgresConnectionProvider.getConnection()) {
            PreparedStatement statement = connection.prepareStatement("insert into black_jack_game (id, bank, rounds) values (?, 0, 0)");
            statement.setLong(1, id);
            statement.execute();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static boolean isGameStarted(long id){
        try (Connection connection = PostgresConnectionProvider.getConnection()) {
            PreparedStatement statement = connection.prepareStatement("select * from black_jack_game where id =? and bank =0");
            statement.setLong(1,id);
            return !statement.executeQuery().next();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static boolean isPlayer(long user_id, long game_id){
        try (Connection connection = PostgresConnectionProvider.getConnection()) {
            PreparedStatement statement = connection.prepareStatement("select * from black_jack_player where player_id =? and game_id =?");
            statement.setLong(1, user_id);
            statement.setLong(2, game_id);
            return statement.executeQuery().next();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static boolean isEnded(long id){
        try (Connection connection = PostgresConnectionProvider.getConnection()) {
            PreparedStatement statement = connection.prepareStatement("select is_ended from black_jack_game where id = ?");
            statement.setLong(1, id);
            ResultSet set = statement.executeQuery();
            if (set.next()){
                return set.getBoolean(1);
            }
            return true;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static long getLastID(){
        try (Connection connection = PostgresConnectionProvider.getConnection()) {
            PreparedStatement statement = connection.prepareStatement("select max(id) from black_jack_game");
            ResultSet set = statement.executeQuery();
            if (set.next()){
                return set.getLong(1);
            }
            return 0;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static void update(BlackJackGame game){
        try (Connection connection = PostgresConnectionProvider.getConnection()) {
            PreparedStatement statement = connection.prepareStatement("update black_jack_game set is_ended=?, rounds =?, bank =? where id=?");
            statement.setBoolean(1,game.is_ended());
            statement.setInt(2,game.getRounds());
            statement.setInt(3, game.getBank());
            statement.setLong(4,game.getId());
            statement.execute();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static BlackJackGame getGame(long id){
        try (Connection connection = PostgresConnectionProvider.getConnection()) {
            PreparedStatement statement = connection.prepareStatement("select is_ended, rounds, bank from black_jack_game" +
                    " where id=?");
            statement.setLong(1,id);
            ResultSet set = statement.executeQuery();
            if (set.next()){
                statement = connection.prepareStatement("select player_id, value, ace_counts, cards, is_stopped, username from black_jack_player left join users on " +
                        "users.id = player_id where game_id = ?");
                statement.setLong(1, id);
                ResultSet resultSet = statement.executeQuery();
                TreeMap<Long, BlackJackPlayer> playerTreeMap = new TreeMap<>();
                while (resultSet.next()){
                    playerTreeMap.put(resultSet.getLong(1),
                            BlackJackPlayer.builder()
                                    .player_id(resultSet.getLong(1))
                                    .game_id(id)
                                    .value(resultSet.getInt(2))
                                    .ace_counts(resultSet.getInt(3))
                                    .cards(resultSet.getString(4))
                                    .is_stopped(resultSet.getBoolean(5))
                                    .name(resultSet.getString(6))
                                    .build());
                }
                return BlackJackGame.builder()
                        .id(id)
                        .is_ended(set.getBoolean(1))
                        .rounds(set.getInt(2))
                        .bank(set.getInt(3))
                        .players(playerTreeMap)
                        .build();
            }

            return null;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static void addPlayer(long game_id, long user_id) {
        try (Connection connection = PostgresConnectionProvider.getConnection()) {
            PreparedStatement statement = connection.prepareStatement("insert into black_jack_player " +
                    "(game_id, player_id, value, cards, ace_counts) values (?,?,0,'',0)");
            statement.setLong(1, game_id);
            statement.setLong(2, user_id);
            statement.execute();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static void updatePlayer(BlackJackPlayer player){
        try(Connection connection = PostgresConnectionProvider.getConnection()) {
            PreparedStatement statement = connection.prepareStatement("update black_jack_player set cards=?, value=?" +
                    ", ace_counts=?, is_stopped =? where player_id=? and game_id=? ");
            statement.setString(1, player.getCards());
            statement.setInt(2, player.getValue());
            statement.setInt(3, player.getAce_counts());
            statement.setBoolean(4, player.is_stopped());
            statement.setLong(5,player.getPlayer_id());
            statement.setLong(6, player.getGame_id());
            statement.execute();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static void stopPlayer(long game_id, long user_id){
        try (Connection connection = PostgresConnectionProvider.getConnection()) {
            PreparedStatement statement = connection.prepareStatement("update black_jack_player set is_stopped = true where game_id = ? and player_id = ?");
            statement.setLong(1,game_id);
            statement.setLong(2,user_id);
            statement.execute();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static void readyPlayers(long game_id){
        try (Connection connection = PostgresConnectionProvider.getConnection()) {
            PreparedStatement statement = connection.prepareStatement("update black_jack_player set is_stopped = false where game_id = ? and value<21");
            statement.setLong(1,game_id);
            statement.execute();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static void removePlayer(long game_id, long user_id){
        try (Connection connection = PostgresConnectionProvider.getConnection()) {
            PreparedStatement statement = connection.prepareStatement("delete from black_jack_player where game_id =? and player_id = ?");
            statement.setLong(1, game_id);
            statement.setLong(2, user_id);
            statement.execute();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static void getWinner(long id) {
        try (Connection connection = PostgresConnectionProvider.getConnection()) {
            PreparedStatement statement = connection.prepareStatement("update black_jack_player set cards = concat(cards,' WINNER!') where value =\n" +
                    "(select max(value) from (select * from black_jack_player where game_id = 13 and( value<22 or (ace_counts = 2 and value = 22))) as \"bjp*\");");
            statement.execute();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
