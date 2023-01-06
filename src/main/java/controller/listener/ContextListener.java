package controller.listener;

import jakarta.servlet.ServletContextEvent;
import jakarta.servlet.ServletContextListener;
import jakarta.servlet.annotation.WebListener;
import model.entity.*;
import model.entity.BlackJack.BlackJackGame;
import model.entity.BlackJack.BlackJackPlayer;
import model.entity.BlackJack.BlackJackRound;
import model.repository.SqlGenerator.SqlGenerator;
import model.repository.database.PostgresConnectionProvider;

import java.sql.*;
import java.util.Enumeration;

@WebListener
public class ContextListener implements ServletContextListener {

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        try {
            Connection connection = PostgresConnectionProvider.getConnection();
            Statement statement = connection.createStatement();
            statement.execute(SqlGenerator.createTable(User.class));
            statement.execute(SqlGenerator.createTable(BlackJackGame.class));
            statement.execute(SqlGenerator.createTable(BlackJackRound.class));
            statement.execute(SqlGenerator.createTable(BlackJackPlayer.class));
            statement.execute(SqlGenerator.createTable(Message.class));
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        Enumeration<Driver> drivers = DriverManager.getDrivers();
        while (drivers.hasMoreElements()) {
            Driver driver = drivers.nextElement();
            try {
                DriverManager.deregisterDriver(driver);
            } catch (SQLException ignored) {
            }
        }
        ServletContextListener.super.contextDestroyed(sce);
    }
}
