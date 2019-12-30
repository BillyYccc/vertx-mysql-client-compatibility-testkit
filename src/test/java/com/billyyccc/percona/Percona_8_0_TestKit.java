package com.billyyccc.percona;

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
@DisplayName("Percona 8.0 TestKit")
class Percona_8_0_TestKit extends MySQLClientTestKit {
  @Container
  private static GenericContainer PERCONA_8_0_CONTAINER = PerconaContainerFactory.configureContainer("ps-8.0");

  @BeforeAll
  static void setUp(Vertx vertx) {
    MySQLConnectOptions connectOptions = PerconaConfigurationFactory.configuration(PERCONA_8_0_CONTAINER);
    pool = MySQLPool.pool(vertx, connectOptions, new PoolOptions().setMaxSize(1));
  }

  @AfterAll
  static void tearDown(Vertx vertx) {
    pool.close();
    vertx.close();
  }
}
