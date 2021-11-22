///MODIFY replace template {PLUGIN_NAME.under}
package kz.greetgo.mybpm.plugin.template.register;
///PIN m1 MODIFY replace template {PLUGIN_NAME.under}
///PIN m2 MODIFY replace Template {PLUGIN_NAME.Camel}

import kz.greetgo.mybpm.plugin.share.umbilical_service.PostgresUmbilicalService;
import kz.greetgo.mybpm.plugin.template.etc.liquibase.LiquibaseManager;
import kz.greetgo.mybpm.plugin.template.for_tests.ParentPluginTestNg;
import kz.greetgo.mybpm.plugin.template.for_tests.PgAccessFactory;
import kz.greetgo.mybpm.plugin.template.model.TestClient;
import kz.greetgo.mybpm.test.register.util.MybpmSimulator;
import kz.greetgo.util.RND;
import lombok.SneakyThrows;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static kz.greetgo.mybpm.plugin.template.launcher.TemplatePluginMain.PLUGIN_ID;
import static org.assertj.core.api.Assertions.assertThat;

///UNPIN m1
///UNPIN m2
public class TestPluginRegisterImplTest extends ParentPluginTestNg {

  protected MybpmSimulator mybpmSimulator;

  @BeforeMethod
  public void init_mybpmSimulator() {
    mybpmSimulator          = new MybpmSimulator(PLUGIN_ID);
    mybpmSimulator.pgAccess = PgAccessFactory.get();
  }

  @AfterMethod
  public void mybpmSimulator__close() {
    mybpmSimulator.close();
  }

  @Test
  @SneakyThrows
  public void loadTestClient() {

    mybpmSimulator.enable(PostgresUmbilicalService.class);

    var postgresUs = mybpmSimulator.cord().get(PostgresUmbilicalService.class);

    new LiquibaseManager(postgresUs).update();

    var testPluginRegister = new TestPluginRegisterImpl(postgresUs);

    var id         = RND.str(20);
    var surname    = RND.str(20);
    var name       = RND.str(20);
    var patronymic = RND.str(20);

    try (var connection = postgresUs.dataSource().getConnection()) {
      try (var ps = connection.prepareStatement(
        "insert into test_client (id, surname, name, patronymic) values (?,?,?,?)")) {
        ps.setString(1, id);
        ps.setString(2, surname);
        ps.setString(3, name);
        ps.setString(4, patronymic);
        ps.executeUpdate();
      }
    }

    //
    //
    TestClient testClient = testPluginRegister.loadTestClient(id);
    //
    //

    assertThat(testClient).isNotNull();
    assertThat(testClient.id).isEqualTo(id);
    assertThat(testClient.surname).isEqualTo(surname);
    assertThat(testClient.name).isEqualTo(name);
    assertThat(testClient.patronymic).isEqualTo(patronymic);
  }

}