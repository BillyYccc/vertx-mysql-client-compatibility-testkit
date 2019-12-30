package com.billyyccc.percona;

import org.testcontainers.containers.GenericContainer;

public class PerconaContainerFactory {
  public static GenericContainer configureContainer(String version) {
    return new GenericContainer("percona:" + version)
      .withEnv("MYSQL_USER", "mysql")
      .withEnv("MYSQL_PASSWORD", "password")
      .withEnv("MYSQL_ROOT_PASSWORD", "password")
      .withEnv("MYSQL_DATABASE", "test")
      .withExposedPorts(3306);
  }
}
