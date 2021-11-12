package mybpm

import java.nio.file.Path
import java.util.regex.Pattern

class Version {
  private static def VERSION_PATTERN = Pattern.compile("(.*)\\.(\\d+)(.*)")

  static def incrementVersionFile(Path versionFile) {
    versionFile.text = incrementVersionValue(versionFile.text) + "\n"
  }

  static String incrementVersionValue(String version) {
    def m = VERSION_PATTERN.matcher(version.trim())
    if (!m.matches()) {
      throw new RuntimeException("X6eBTrek0U :: Unknown version format: " + version)
    }

    def before = m.group(1)
    def v = m.group(2)
    def after = m.group(3)

    return before + "." + (Integer.parseInt(v) + 1) + after
  }

}
