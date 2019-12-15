package com.billyyccc.mariadb;

import org.testcontainers.containers.GenericContainer;

public class MariaDBContainerFactory {
  public static GenericContainer configureContainer(String version) {
    return new GenericContainer("mariadb:" + version)
      .withEnv("MYSQL_USER", "mysql")
      .withEnv("MYSQL_PASSWORD", "password")
      .withEnv("MYSQL_ROOT_PASSWORD", "password")
      .withEnv("MYSQL_DATABASE", "test")
      .withExposedPorts(3306);
  }
}
