//MODIFY rename template {PROJECT_NAME}
package kz.greetgo.mybpm.plugin.template;


import java.util.Date;
import kz.greetgo.mybpm.plugin.share.util.PublicAccess;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/test")
public class TestPluginController {

  @PublicAccess
  @GetMapping("/hello")
  public String hello(@RequestParam(value = "world", required = false) String world) {
    for (int i = 0; i < 10; i++) {
      System.out.println("h4w77aQMiO LL i = " + i);
    }
    return "Test PLUGIN: HELLO " + world + " - 00001 - NOW(" + new Date() + ")";
  }

}
