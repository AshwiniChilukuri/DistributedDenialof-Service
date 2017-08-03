JCC = javac

JFLAGS = -cp ".:geoip-api-1.2.10.jar"

default:MasterBot.class	SlaveBot.class

MasterBot.class: MasterBot.java
	$(JCC) MasterBot.java
SlaveBot.class: SlaveBot.java
	$(JCC) $(JFLAGS) SlaveBot.java
clean: 
	$(RM) *.class
