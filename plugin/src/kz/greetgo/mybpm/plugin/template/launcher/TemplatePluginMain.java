///PIN m1 MODIFY replace template {PLUGIN_NAME.under}
///PIN m2 MODIFY replace Template {PLUGIN_NAME.Camel}
package kz.greetgo.mybpm.plugin.template.launcher;

import kz.greetgo.mybpm.plugin.share.main.PluginMain;
import kz.greetgo.mybpm.plugin.share.main.UmbilicalCord;
import kz.greetgo.mybpm.plugin.share.model.PluginMainMarker;
import kz.greetgo.mybpm.plugin.share.umbilical_service.BoiConsumerUmbilicalService;
import kz.greetgo.mybpm.plugin.share.umbilical_service.ControllerRegistrarUmbilicalService;
import kz.greetgo.mybpm.plugin.share.umbilical_service.PostgresUmbilicalService;
import kz.greetgo.mybpm.plugin.share.umbilical_service.SchedulerUmbilicalService;
import kz.greetgo.mybpm.plugin.share.umbilical_service.VersionUmbilicalService;
import kz.greetgo.mybpm.plugin.share.umbilical_service.boi.BoiModification;
import kz.greetgo.mybpm.plugin.template.controller.TestPluginController;
import kz.greetgo.mybpm.plugin.template.etc.App;
import kz.greetgo.mybpm.plugin.template.etc.liquibase.LiquibaseManager;
import kz.greetgo.mybpm.plugin.template.register.TestPluginRegisterImpl;
import kz.greetgo.mybpm.plugin.template.scheduler.TestSchedulerController;

@PluginMainMarker
public class TemplatePluginMain implements PluginMain {

  public static final String PLUGIN_ID = "template";

  private LiquibaseManager liquibaseManager;

  @Override
  public void initPlugin(UmbilicalCord umbilicalCord) {

    setVersionInfo(umbilicalCord);

    umbilicalCord.get(BoiConsumerUmbilicalService.class)
                 .addBoiModificationHandler(
                   BoiModification.add()
                                  .groupId("test")
                                  .handler(
                                    modification ->
                                      System.out.println("ur1uL1MHvx :: TEST UsBoiModification :: " + modification)));

    var postgresUs = umbilicalCord.get(PostgresUmbilicalService.class);

    liquibaseManager = new LiquibaseManager(postgresUs);

    var testSchedulerController = new TestSchedulerController();

    var schedulerUs = umbilicalCord.get(SchedulerUmbilicalService.class);
    schedulerUs.register(testSchedulerController);

    var testPluginRegister   = new TestPluginRegisterImpl(postgresUs);
    var testPluginController = new TestPluginController(testPluginRegister);

    var controllerRegistrarUs = umbilicalCord.get(ControllerRegistrarUmbilicalService.class);
    controllerRegistrarUs.register(testPluginController);

  }

  private void setVersionInfo(UmbilicalCord umbilicalCord) {
    var service = umbilicalCord.get(VersionUmbilicalService.class);
    service.setPluginGitId(App.gitId());
    service.setPluginVersion(App.version());
    service.setPluginVendor(App.vendor());
  }

  @Override
  public void start() {
    liquibaseManager.update();
  }
}
