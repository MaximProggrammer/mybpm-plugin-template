///MODIFY replace template {PLUGIN_NAME.under}
package kz.greetgo.mybpm.plugin.template.for_tests;

import kz.greetgo.mybpm.test.register.util.MybpmSimulator;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

///MODIFY replace template {PLUGIN_NAME.under}
///MODIFY replace Template {PLUGIN_NAME.Camel}
import static kz.greetgo.mybpm.plugin.template.launcher.TemplatePluginMain.PLUGIN_ID;

public abstract class ParentPluginTestNg {

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

}
