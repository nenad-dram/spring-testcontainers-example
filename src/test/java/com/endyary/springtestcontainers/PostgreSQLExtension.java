package com.endyary.springtestcontainers;

import org.junit.jupiter.api.extension.AfterAllCallback;
import org.junit.jupiter.api.extension.BeforeAllCallback;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.testcontainers.containers.PostgreSQLContainer;

public class PostgreSQLExtension implements BeforeAllCallback, AfterAllCallback {

  @Override
  public void beforeAll(ExtensionContext extensionContext) {
    PostgreSQLContainer<?> postgres = new PostgreSQLContainer<>("postgres:alpine");
      postgres.start();
      System.setProperty("spring.datasource.url", postgres.getJdbcUrl());
      System.setProperty("spring.datasource.username", postgres.getUsername());
      System.setProperty("spring.datasource.password", postgres.getPassword());
  }

  @Override
  public void afterAll(ExtensionContext extensionContext) throws Exception {
  }
}
