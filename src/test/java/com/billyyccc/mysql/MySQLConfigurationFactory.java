package com.billyyccc.mysql;

import io.vertx.mysqlclient.MySQLConnectOptions;
import org.testcontainers.containers.GenericContainer;

public class MySQLConfigurationFactory {
  public static MySQLConnectOptions configuration(GenericContainer server) {
    return new MySQLConnectOptions()
      .setPort(server.getMappedPort(3306))
      .setHost(server.getContainerIpAddress())
      .setDatabase("test")
      .setUser("mysql")
      .setPassword("password");
  }
}
