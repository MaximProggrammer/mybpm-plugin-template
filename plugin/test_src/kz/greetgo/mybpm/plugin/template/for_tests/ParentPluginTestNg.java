///PIN m1 MODIFY replace template {PLUGIN_NAME.under}
///PIN m2 MODIFY replace Template {PLUGIN_NAME.Camel}
package kz.greetgo.mybpm.plugin.template.for_tests;

import kz.greetgo.mybpm.test.register.util.MybpmSimulator;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

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
