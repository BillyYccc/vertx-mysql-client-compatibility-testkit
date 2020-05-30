package com.billyyccc.mariadb;

import com.billyyccc.base.MySQLClientTestKit;
import io.vertx.core.Vertx;
import io.vertx.mysqlclient.MySQLConnectOptions;
import io.vertx.mysqlclient.MySQLPool;
import io.vertx.sqlclient.PoolOptions;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.testcontainers.containers.GenericContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

@Testcontainers
@DisplayName("MariaDB 10.5 TestKit")
public class MariaDB_10_5_TestKit extends MySQLClientTestKit {
  @Container
  private static GenericContainer MARIA_10_5_CONTAINER = MariaDBContainerFactory.configureContainer("10.5");

  @BeforeAll
  static void setUp(Vertx vertx) {
    MySQLConnectOptions connectOptions = MariaDBConfigurationFactory.configuration(MARIA_10_5_CONTAINER);
    pool = MySQLPool.pool(vertx, connectOptions, new PoolOptions().setMaxSize(1));
  }

  @AfterAll
  static void tearDown(Vertx vertx) {
    pool.close();
    vertx.close();
  }
}
