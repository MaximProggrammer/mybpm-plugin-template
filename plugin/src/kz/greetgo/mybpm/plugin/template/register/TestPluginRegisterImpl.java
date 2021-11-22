package kz.greetgo.mybpm.plugin.template.register;

import java.util.Date;
import kz.greetgo.mybpm.plugin.share.umbilical_service.PostgresUmbilicalService;

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

}
