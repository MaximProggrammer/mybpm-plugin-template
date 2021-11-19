///MODIFY replace template {PLUGIN_NAME.under}
package kz.greetgo.mybpm.plugin.template;

import kz.greetgo.mybpm.plugin.share.main.PluginMain;
import kz.greetgo.mybpm.plugin.share.main.UmbilicalCord;
import kz.greetgo.mybpm.plugin.share.model.PluginMainMarker;
import kz.greetgo.mybpm.plugin.share.umbilical_service.BoiConsumerUmbilicalService;
import kz.greetgo.mybpm.plugin.share.umbilical_service.ControllerRegistrarUmbilicalService;
import kz.greetgo.mybpm.plugin.share.umbilical_service.SchedulerUmbilicalService;
import kz.greetgo.mybpm.plugin.share.umbilical_service.boi.BoiModification;

@PluginMainMarker
///MODIFY replace Template {PLUGIN_NAME.Camel}
public class TemplatePluginMain implements PluginMain {
  @Override
  public void initPlugin(UmbilicalCord umbilicalCord) {
    umbilicalCord.get(BoiConsumerUmbilicalService.class)
                 .addBoiModificationHandler(
                   BoiModification.add()
                                  .groupId("test")
                                  .handler(
                                    modification ->
                                      System.out.println("ur1uL1MHvx :: TEST UsBoiModification :: "
                                                           + modification)));

    var testSchedulerController = new TestSchedulerController();

    umbilicalCord.get(SchedulerUmbilicalService.class).register(testSchedulerController);

    var testPluginController = new TestPluginController();

    umbilicalCord.get(ControllerRegistrarUmbilicalService.class).register(testPluginController);

  }
}
