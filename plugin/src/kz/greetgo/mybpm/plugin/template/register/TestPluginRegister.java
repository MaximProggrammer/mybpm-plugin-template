package kz.greetgo.mybpm.plugin.template.register;

import kz.greetgo.mybpm.plugin.template.model.TestClient;

public interface TestPluginRegister {

  String hello(String world);

  TestClient loadTestClient(String id);

}
