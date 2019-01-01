# 301494670
# aharonay

compile: bin
	find src | grep .java > sources.txt
	javac -d bin -cp biuoop-1.4.jar @sources.txt
	
jar:
	jar cfm ass5game.jar manifest.mf -C bin .
	
run:
	java -cp biuoop-1.4.jar:bin Ass5Game 1 2 3 4

check:
	java -jar checkstyle-5.7-all.jar -c biuoop.xml src/*.java

bin:
	mkdir bin