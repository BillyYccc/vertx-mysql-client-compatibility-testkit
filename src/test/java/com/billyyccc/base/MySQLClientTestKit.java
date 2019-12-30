package com.billyyccc.base;

import io.vertx.junit5.Checkpoint;
import io.vertx.junit5.VertxExtension;
import io.vertx.junit5.VertxTestContext;
import io.vertx.mysqlclient.MySQLPool;
import io.vertx.sqlclient.Row;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

@ExtendWith(VertxExtension.class)
public abstract class MySQLClientTestKit {
  protected static MySQLPool pool;

  @Test
  @DisplayName("Testing simple query")
  public void testTextBasedQuery(VertxTestContext testContext) {
    Checkpoint checkpoint = testContext.checkpoint(1000);
    for (int i = 0; i < 1000; i++) {
      pool.getConnection(testContext.succeeding(conn -> {
        conn.query("SELECT 123", testContext.succeeding(result1 -> {
          int result1Size = result1.size();
          Row result1Row = result1.iterator().next();
          testContext.verify(() -> {
            Assertions.assertEquals(1, result1Size);
            Assertions.assertEquals(123, result1Row.getInteger(0));
          });
          conn.query("SELECT 'hello, world!'", testContext.succeeding(result2 -> {
            int result2Size = result2.size();
            Row result2Row = result2.iterator().next();
            testContext.verify(() -> {
              Assertions.assertEquals(1, result2Size);
              Assertions.assertEquals("hello, world!", result2Row.getString(0));
            });
            conn.close();
            checkpoint.flag();
          }));
        }));
      }));
    }
  }

  @Test
  @DisplayName("testing prepared statement")
  public void testPreparedStatement(VertxTestContext testContext) {
    Checkpoint checkpoint = testContext.checkpoint(1000);
    for (int i = 0; i < 1000; i++) {
      pool.getConnection(testContext.succeeding(conn -> {
        conn.preparedQuery("SELECT 123", testContext.succeeding(result1 -> {
          int result1Size = result1.size();
          Row result1Row = result1.iterator().next();
          testContext.verify(() -> {
            Assertions.assertEquals(1, result1Size);
            Assertions.assertEquals(123, result1Row.getInteger(0));
          });
          conn.preparedQuery("SELECT 'hello, world!'", testContext.succeeding(result2 -> {
            int result2Size = result2.size();
            Row result2Row = result2.iterator().next();
            testContext.verify(() -> {
              Assertions.assertEquals(1, result2Size);
              Assertions.assertEquals("hello, world!", result2Row.getString(0));
            });
            conn.close();
            checkpoint.flag();
          }));
        }));
      }));
    }
  }

  @Test
  @DisplayName("testing version")
  public void testVersion(VertxTestContext testContext) {
    pool.query("SELECT VERSION()", testContext.succeeding(result -> {
      Row row = result.iterator().next();
      String version = row.getString(0);
      System.out.println("SELECT VERSION(): " + version);
      testContext.completeNow();
    }));
  }
}
