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
@DisplayName("Percona 5.6 TestKit")
class Percona_5_6_TestKit extends MySQLClientTestKit {
  @Container
  private static GenericContainer PERCONA_5_6_CONTAINER = PerconaContainerFactory.configureContainer("ps-5.6");

  @BeforeAll
  static void setUp(Vertx vertx) {
    MySQLConnectOptions connectOptions = PerconaConfigurationFactory.configuration(PERCONA_5_6_CONTAINER);
    pool = MySQLPool.pool(vertx, connectOptions, new PoolOptions().setMaxSize(1));
  }

  @AfterAll
  static void tearDown(Vertx vertx) {
    pool.close();
    vertx.close();
  }
}
