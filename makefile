compile: bin
	javac -d bin -cp biuoop-1.4.jar src/*.java
run:
	java -jar ass6game.jar
jar:
	jar cfm ass6game.jar Manifest.mf -C bin . -C resources .
bin:
	mkdir bin