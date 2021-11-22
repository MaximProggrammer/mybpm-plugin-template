///PIN m1 MODIFY replace template {PLUGIN_NAME.under}
///PIN m2 MODIFY replace Template {PLUGIN_NAME.Camel}
package kz.greetgo.mybpm.plugin.template.register;

import kz.greetgo.mybpm.plugin.template.model.TestClient;

public interface TestPluginRegister {

  String hello(String world);

  TestClient loadTestClient(String id);

}
