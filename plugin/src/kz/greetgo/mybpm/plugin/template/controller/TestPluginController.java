///MODIFY replace template {PLUGIN_NAME.under}
package kz.greetgo.mybpm.plugin.template.controller;


import kz.greetgo.mybpm.plugin.share.util.PublicAccess;
///MODIFY replace template {PLUGIN_NAME.under}
import kz.greetgo.mybpm.plugin.template.register.TestPluginRegister;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/test")
public class TestPluginController {

  private final TestPluginRegister testPluginRegister;

  public TestPluginController(TestPluginRegister testPluginRegister) {
    this.testPluginRegister = testPluginRegister;
  }

  @PublicAccess
  @GetMapping("/hello")
  public String hello(@RequestParam(value = "world", required = false) String world) {
    return testPluginRegister.hello(world);
  }

}
