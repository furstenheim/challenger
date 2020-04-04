test:
	JAVA_HOME=${HOME}/.jdks/liberica-1.8.0_242/
	export JAVA_HOME
	gradle test
test-watch:
	./watch.sh
