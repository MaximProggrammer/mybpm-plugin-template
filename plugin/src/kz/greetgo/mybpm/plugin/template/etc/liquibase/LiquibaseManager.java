///PIN m1 MODIFY replace template {PLUGIN_NAME.under}
///PIN m2 MODIFY replace Template {PLUGIN_NAME.Camel}
package kz.greetgo.mybpm.plugin.template.etc.liquibase;

import kz.greetgo.mybpm.plugin.share.umbilical_service.PostgresUmbilicalService;
import liquibase.Liquibase;
import liquibase.database.core.PostgresDatabase;
import liquibase.database.jvm.JdbcConnection;
import liquibase.resource.ClassLoaderResourceAccessor;
import lombok.SneakyThrows;

public class LiquibaseManager {
  private final PostgresUmbilicalService postgresUs;

  public LiquibaseManager(PostgresUmbilicalService postgresUs) {
    this.postgresUs = postgresUs;
  }

  @SneakyThrows
  public void update() {
    Class.forName("org.postgresql.Driver");
    try (var connection = postgresUs.dataSource().getConnection()) {
      var database = new PostgresDatabase();
      database.setConnection(new JdbcConnection(connection));
      {
        new Liquibase(
          ///MODIFY replace template {PLUGIN_NAME.under}
          "liquibase/plugin-template/main-change-log.xml",
          new ClassLoaderResourceAccessor(), database
        ).update("");
      }
    }
  }
}
