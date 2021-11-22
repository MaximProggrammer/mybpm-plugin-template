///MODIFY replace template {PLUGIN_NAME.under}
package kz.greetgo.mybpm.plugin.template.register;

///MODIFY replace template {PLUGIN_NAME.under}
import kz.greetgo.mybpm.plugin.template.model.TestClient;

public interface TestPluginRegister {

  String hello(String world);

  TestClient loadTestClient(String id);

}
