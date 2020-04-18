JAVA_HOME:=${HOME}/.jdks/liberica-1.8.0_242/
# export JAVA_HOME=${HOME}/.jdks/liberica-1.8.0_242/

test:
	gradle test -Dorg.gradle.java.home=${JAVA_HOME}
test-watch:
	./watch.sh
