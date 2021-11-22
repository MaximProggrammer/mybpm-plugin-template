///PIN m1 MODIFY replace template {PLUGIN_NAME.under}
///PIN m2 MODIFY replace Template {PLUGIN_NAME.Camel}
package kz.greetgo.mybpm.plugin.template.for_tests;

import kz.greetgo.mybpm.test.register.util.model.PgAccess;

public class PgAccessFactory {
  public static PgAccess get() {
    PgAccess rete = new PgAccess();
    rete.dbHost = "localhost";
    ///MODIFY port 23121
    rete.dbPort = 23121;
    rete.dbName = "test_register_util_db";
    rete.dbAdminUsername = "test_register_util";
    ///MODIFY   password    25UsbGa7G76X01F30K09D7v1a96vYZUybWVsZWqN
    rete.dbAdminPassword = "25UsbGa7G76X01F30K09D7v1a96vYZUybWVsZWqN";
    return rete;
  }
}
