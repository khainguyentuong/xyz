package khainguyentuong.flywaydb;

import org.flywaydb.core.Flyway;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.Properties;

public class flywayMigrate  {
    static final Logger logger = LoggerFactory.getLogger(flywayMigrate.class);

    public static void main(final String... args) throws IOException {
        final String isClean = System.getProperty("isClean");
        final Boolean clean = isClean==null ? false : Boolean.valueOf(isClean);
        final String configLocation = System.getProperty("configFile");
        final File confFile = configLocation==null ?
                new File("./conf/config.properties") :
                new File(configLocation);

        final Properties properties = new Properties();

        properties.load(Files.newInputStream(confFile.toPath()));

        logger.info(String.format("Url is %s\n", properties.getProperty("flyway.url")));
		logger.info(String.format("Schemas is %s\n", properties.getProperty("flyway.schemas")));
        logger.info(String.format("Locations is %s\n", properties.getProperty("flyway.locations")));

        // Create the Flyway instance
        Flyway flyway = new Flyway();

        // Load flyway configurations
        flyway.configure(properties);

        if (clean) {
            // Cleanup database before the migration
            flyway.clean();
        }

        // Start the migration
        flyway.migrate();
    }
}