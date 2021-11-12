//MODIFY rename template {PROJECT_NAME}
package kz.greetgo.mybpm.plugin.template;

import java.util.Date;
import kz.greetgo.scheduling.FromConfig;
import kz.greetgo.scheduling.Scheduled;

public class TestSchedulerController {

  @FromConfig("test wow")
  @Scheduled("repeat every 15 seconds")
  public void ping() {
    System.out.println("tO2NV50Qrm :: Ping from scheduler " + new Date());
  }

}
