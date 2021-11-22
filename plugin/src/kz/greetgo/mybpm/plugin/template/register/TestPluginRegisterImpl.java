///MODIFY replace template {PLUGIN_NAME.under}
package kz.greetgo.mybpm.plugin.template.register;

import java.sql.ResultSet;
import java.util.Date;
import kz.greetgo.mybpm.plugin.share.umbilical_service.PostgresUmbilicalService;
///MODIFY replace template {PLUGIN_NAME.under}
import kz.greetgo.mybpm.plugin.template.model.TestClient;
import lombok.SneakyThrows;

public class TestPluginRegisterImpl implements TestPluginRegister {

  private final PostgresUmbilicalService postgresUs;

  public TestPluginRegisterImpl(PostgresUmbilicalService postgresUs) {
    this.postgresUs = postgresUs;
  }

  @Override
  public String hello(String world) {
    for (int i = 0; i < 10; i++) {
      System.out.println("h4w77aQMiO LL i = " + i);
    }
    return "Test PLUGIN: HELLO " + world + " - 00001 - NOW(" + new Date() + ")";
  }

  @Override
  @SneakyThrows
  public TestClient loadTestClient(String id) {
    try (var connection = postgresUs.dataSource().getConnection()) {
      try (var ps = connection.prepareStatement("select * from test_client where id = ?")) {
        ps.setString(1, id);
        try (var rs = ps.executeQuery()) {
          if (!rs.next()) {
            return null;
          }
          return rsToTestClient(rs);
        }
      }
    }
  }

  @SneakyThrows
  private TestClient rsToTestClient(ResultSet rs) {
    var rete = new TestClient();
    rete.id         = rs.getString("id");
    rete.surname    = rs.getString("surname");
    rete.name       = rs.getString("name");
    rete.patronymic = rs.getString("patronymic");
    return rete;
  }

}
