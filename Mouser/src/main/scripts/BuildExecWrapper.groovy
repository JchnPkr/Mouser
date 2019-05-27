println "reading file wrap"
String bashWrapCmdFile = new File("${project.basedir}/src/main/scripts/wrap".toString()).getText()

println "replacing project name"
def bashWrapCmdFileText = new groovy.text.SimpleTemplateEngine().createTemplate(bashWrapCmdFile).make([fileName: "${project.build.finalName}"])

println "creating file createBashWrapper.sh"
File createBashWrapperFile = new File("${project.basedir}/src/main/scripts/createBashWrapper.sh".toString())

println "writing to file"
createBashWrapperFile.withWriter('UTF-8') {writer -> writer.write(bashWrapCmdFileText)}