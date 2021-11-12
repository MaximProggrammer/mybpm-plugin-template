package mybpm


import java.util.stream.Collectors

class Git {

  private File rootDir

  Git(File rootDir) {
    this.rootDir = rootDir
  }

  def currentBranch() {
    def br = System.getenv("CI_COMMIT_BRANCH")
    if (br != null && br.length() > 0) {
      return br
    }
    try {
      def result = 'git rev-parse --abbrev-ref HEAD'.execute(null, rootDir)
      result.waitFor()
      if (result.exitValue() == 0) {
        return result.text.trim()
      }
    } catch (e) {
      e.printStackTrace()
    }
    return "no_branch_defined"
  }

  List<String> uncommittedList() {
    def result = "git update-index --refresh".execute(null, rootDir)
    result.waitFor()
    String text = result.text
    return Arrays.stream(text.split("\n"))
      .filter { it != null }
      .filter { it.length() > 0 }
      .collect(Collectors.toList())
  }

  String runCmd(String[] cmd, String errMessage) {
    def out = new StringBuilder(), err = new StringBuilder()
    def result = Runtime.getRuntime().exec(cmd, null, rootDir)
    result.consumeProcessOutput(out, err)
    result.waitFor()
    if (result.exitValue() == 0) {
      return out.toString()
    }
    def cmdLine = Arrays.asList(cmd).join(" ")
    throw new RuntimeException("X96cDwnWw3 :: Error in command: " + cmdLine + "\n"
      + "Exit code " + result.exitValue() + "\n"
      + errMessage + "\n"
      + err)
  }

  def pushTag(String remote, String tagName) {
    runCmd(["git", "push", remote, tagName] as String[], "9O9zK8XJvn :: Cannot push tag " + tagName + " to " + remote)
  }

  def currentRemote() {
    return runCmd(["git", "remote"] as String[], "C3zhVNF6DV :: git remote").trim()
  }

  def commitOneFile(String file, String commitMessage) {
    def out = new StringBuilder(), err = new StringBuilder()
    def result = Runtime.getRuntime().exec([
      "git", "commit", "-m", commitMessage, "--", file.toString()
    ] as String[], null, rootDir)
    result.consumeProcessOutput(out, err)
    result.waitFor()
    if (result.exitValue() == 0) {
      return
    }
    System.err.println(err)
    throw new RuntimeException("TGBs883UJ8 :: Cannot commit file " + file + " with commit message: " + commitMessage)
  }

  def tag(String tagName, String errMessage) {
    runCmd(["git", "tag", tagName] as String[],
      "Git cannot create tag: " + tagName + "\n" + errMessage)
  }
}
