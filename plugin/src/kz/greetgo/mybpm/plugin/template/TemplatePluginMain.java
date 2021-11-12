//MODIFY rename template {PROJECT_NAME}
package kz.greetgo.mybpm.plugin.template;

import kz.greetgo.mybpm.plugin.share.main.PluginMain;
import kz.greetgo.mybpm.plugin.share.main.UmbilicalCord;
import kz.greetgo.mybpm.plugin.share.model.PluginMainMarker;
import kz.greetgo.mybpm.plugin.share.umbilical_service.BoiConsumerUmbilicalService;
import kz.greetgo.mybpm.plugin.share.umbilical_service.ControllerRegistrarUmbilicalService;
import kz.greetgo.mybpm.plugin.share.umbilical_service.SchedulerUmbilicalService;
import kz.greetgo.mybpm.plugin.share.umbilical_service.boi.BoiModification;

@PluginMainMarker
//MODIFY rename Template {PROJECT_NAME}
public class TemplatePluginMain implements PluginMain {
  @Override
  public void initPlugin(UmbilicalCord umbilicalCord) {
    umbilicalCord.get(BoiConsumerUmbilicalService.class)
                 .addBoiModificationHandler(
                   BoiModification.add()
                                  .groupId("test")
                                  .handler(
                                    modification ->
                                      System.out.println("KFoy29p8ZM :: TEST UsBoiModification :: "
                                                           + modification)));

    var testSchedulerController = new TestSchedulerController();

    umbilicalCord.get(SchedulerUmbilicalService.class).register(testSchedulerController);

    var testPluginController = new TestPluginController();

    umbilicalCord.get(ControllerRegistrarUmbilicalService.class).register(testPluginController);

  }
}
