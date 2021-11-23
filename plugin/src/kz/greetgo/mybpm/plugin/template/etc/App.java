///PIN m1 MODIFY replace template {PLUGIN_NAME.under}
///PIN m2 MODIFY replace Template {PLUGIN_NAME.Camel}
package kz.greetgo.mybpm.plugin.template.etc;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.Objects;
import java.util.stream.Collectors;
import lombok.SneakyThrows;

public class App {
  @SneakyThrows
  public static String version() {
    {
      var version = App.class.getPackage().getSpecificationVersion();
      if (version != null) {
        return version;
      }
    }
    {
      var resourceAsStream = App.class.getResourceAsStream("/version.txt");
      return new String(Objects.requireNonNull(resourceAsStream).readAllBytes(), StandardCharsets.UTF_8).trim();
    }
  }

  @SneakyThrows
  public static String gitId() {
    {
      var gitId = App.class.getPackage().getImplementationVersion();
      if (gitId != null) {
        return gitId;
      }
    }
    {
      var gitId = readGitIdFromGit();
      if (gitId != null) {
        return gitId;
      }
    }

    return "unknown-git-id";
  }

  @SneakyThrows
  public static String vendor() {
    {
      var vendor = App.class.getPackage().getSpecificationVendor();
      if (vendor != null) {
        return vendor;
      }
    }
    return "greetgo!";
  }

  private static String readGitIdFromGit() {
    ProcessBuilder processBuilder = new ProcessBuilder();

    processBuilder.command("git", "rev-parse", "HEAD");

    try {
      Process process = processBuilder.start();

      try (BufferedReader reader = new BufferedReader(
        new InputStreamReader(process.getInputStream()))) {

        var result = reader.lines().collect(Collectors.joining(" ")).trim();

        var exitCode = process.waitFor();

        if (exitCode == 0) {
          return result.length() <= 17 ? result : result.substring(0, 17);
        }

        return null;

      }

    } catch (IOException | InterruptedException e) {
      return null;
    }
  }

}
