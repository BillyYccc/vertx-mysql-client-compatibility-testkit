package com.billyyccc.tidb;

import com.billyyccc.base.MySQLClientTestKit;
import io.vertx.core.Vertx;
import io.vertx.mysqlclient.MySQLConnectOptions;
import io.vertx.mysqlclient.MySQLPool;
import io.vertx.sqlclient.PoolOptions;
import org.junit.jupiter.api.*;

@DisplayName("TiDB 3.0 TestKit")
public class TiDB_3_0_TestKit extends MySQLClientTestKit {
  private boolean isTestingEnabled = false;

  @BeforeAll
  static void setUp(Vertx vertx) {
    MySQLConnectOptions connectOptions = new MySQLConnectOptions()
      .setHost("localhost")
      .setPort(4000)
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
