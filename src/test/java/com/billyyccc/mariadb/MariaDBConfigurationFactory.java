package com.billyyccc.mariadb;

import io.vertx.mysqlclient.MySQLConnectOptions;
import org.testcontainers.containers.GenericContainer;

public class MariaDBConfigurationFactory {
  public static MySQLConnectOptions configuration(GenericContainer server) {
    return new MySQLConnectOptions()
      .setPort(server.getMappedPort(3306))
      .setHost(server.getContainerIpAddress())
      .setDatabase("test")
      .setUser("mysql")
      .setPassword("password");
  }
}
