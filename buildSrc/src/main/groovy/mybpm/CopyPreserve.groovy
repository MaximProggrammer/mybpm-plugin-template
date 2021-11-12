package mybpm

import org.gradle.api.DefaultTask
import org.gradle.api.tasks.TaskAction

import java.nio.file.Files
import java.nio.file.Path
import java.nio.file.StandardCopyOption
import java.util.stream.Collectors

abstract class CopyPreserve extends DefaultTask {

  private final List<Path> fromPathList = new ArrayList<>()
  private Path _intoPath
  private boolean _clearIntoPath = false
  private boolean _preserve = false

  def fromPath(Path path) {
    fromPathList.add(path)
  }

  def intoPath(Path path) {
    _intoPath = path
  }

  def clearIntoPath(boolean clearIntoPath) {
    _clearIntoPath = clearIntoPath
  }

  def preserve(boolean preserve) {
    _preserve = preserve
  }

  @TaskAction
  def execute() {
    assert _intoPath != null
    if (!_intoPath.toFile().exists()) {
      _intoPath.toFile().mkdirs()
    } else if (_clearIntoPath) {
      def files = _intoPath.toFile().listFiles()
      if (files != null) {
        for (File file : files) {
          if (file.isDirectory()) {
            file.deleteDir()
          } else {
            file.delete()
          }
        }
      }
    }

    for (Path fromPath : fromPathList) {

      if (!fromPath.toFile().exists()) continue

      def jars = Files.list(fromPath)
        .filter { it.toFile().getName().toLowerCase().endsWith(".jar") }
        .collect(Collectors.toList())

      for (Path jar : jars) {

        def destPath = _intoPath.resolve(jar.toFile().getName())
        if (destPath.toFile().exists()) {
          if (destPath.toFile().isDirectory()) {
            destPath.toFile().deleteDir()
          } else {
            destPath.toFile().delete()
          }
        }

        def target = jar

        if (_preserve) {
          Files.copy(target, destPath, StandardCopyOption.COPY_ATTRIBUTES)
        } else {
          Files.copy(target, destPath)
        }

      }

    }

  }

}
