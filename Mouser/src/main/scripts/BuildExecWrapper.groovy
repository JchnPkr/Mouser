//println "reading file wrapperCmdTemplate"
//String bashWrapCmdFile = new File("${project.basedir}/src/main/scripts/wrapperCmdTemplate".toString()).getText()
//
//println "replacing project name"
//def bashWrapCmdFileText = new groovy.text.SimpleTemplateEngine().createTemplate(bashWrapCmdFile).make([fileName: "${project.build.finalName}"])
//
//println "creating file createBashWrapper.sh"
//File createBashWrapperFile = new File("${project.basedir}/src/main/scripts/createBashWrapper.sh".toString())
//
//println "writing to file"
//createBashWrapperFile.withWriter('UTF-8') {writer -> writer.write(bashWrapCmdFileText)}
//
//println "execute createBashWrapper.sh"
//builder = new AntBuilder()
//builder.chmod(file:"${project.basedir}/src/main/scripts/createBashWrapper.sh", perm:"+x")
//createBashWrapperFile.getText().execute() //problem with cat command execution

File bashCmdFile = new File("${project.basedir}/src/main/scripts/stub.sh".toString())
File jarFile = new File("${project.basedir}/target/${project.build.finalName}-jar-with-dependencies.jar".toString())
File runnerFile = new File("${project.basedir}/output/mouser.run".toString())
runnerFile.append(bashCmdFile.getBytes())
runnerFile.append(jarFile.getBytes())
builder = new AntBuilder()
builder.chmod(file:runnerFile.absolutePath, perm:"+x")

