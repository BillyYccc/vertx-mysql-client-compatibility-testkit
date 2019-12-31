package com.billyyccc.vitess;

import com.billyyccc.base.MySQLClientTestKit;
import io.vertx.core.Vertx;
import io.vertx.mysqlclient.MySQLConnectOptions;
import io.vertx.mysqlclient.MySQLPool;
import io.vertx.sqlclient.PoolOptions;
import org.junit.jupiter.api.*;

@DisplayName("Vitess TestKit")
public class Vitess_TestKit extends MySQLClientTestKit {
  private boolean isTestingEnabled = false;

  @BeforeAll
  static void setUp(Vertx vertx) {
    MySQLConnectOptions connectOptions = new MySQLConnectOptions()
      .setHost("localhost")
      .setPort(15306)
      .setUser("root")
      .setPassword("");
    pool = MySQLPool.pool(vertx, connectOptions, new PoolOptions().setMaxSize(1));
  }

  @AfterAll
  static void tearDown(Vertx vertx) {
    pool.close();
    vertx.close();
  }

  @BeforeEach
  void before() {
    Assumptions.assumeTrue(isTestingEnabled);
  }
}
