package org.foodhub.database.connection;

import java.util.Properties;

import java.io.IOException;
import java.io.InputStream;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;

import org.foodhub.database.exception.DatabaseConnectionFailureException;

/**
 * <p>
 * Connects to the database to store information.
 * </p>
 *
 * @author Muthu kumar V
 * @version 1.0
 */
public final class DataBaseConnection {

    private static final Logger LOGGER = LogManager.getLogger(DataBaseConnection.class);
    private static Connection connection;

    private DataBaseConnection() {
    }

    /**
     * <p>
     * Gets the database connection.
     * </p>
     *
     * @return The database connection
     */
    public static Connection get() {
        if (null == connection) {
            final ClassLoader classLoader = DataBaseConnection.class.getClassLoader();

            try (final InputStream inputStream = classLoader.getResourceAsStream("database.properties")) {
                final Properties properties = new Properties();

                properties.load(inputStream);
                final String url = properties.get("database.url").toString();
                final String user = properties.get("database.user").toString();
                final String password = properties.get("database.password").toString();

                Class.forName("org.postgresql.Driver");
                connection = DriverManager.getConnection(url, user, password);
            } catch (SQLException | IOException | ClassNotFoundException message) {
                LOGGER.error(message.getMessage());
                throw new DatabaseConnectionFailureException(message.getMessage());
            }
        }

        return connection;
    }
}