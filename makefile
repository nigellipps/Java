JFLAGS = -g
JC = javac
JVM = java
.SUFFIXES: .java .class
.java.class:
	$(JC) $(JFLAGS) $*.java

CLASSES = \
        Account.java \
        COptionPanel.java \
        Check.java \
	CheckingAccount.java \
	Deposit.java \
	Game.java \
	JFrameL.java \
	PlaySound.java \
	Transaction.java \
        Main.java 

MAIN = Main
default: classes

classes: $(CLASSES:.java=.class)

run: $(MAIN).class
	$(JVM) $(MAIN)

clean:
	$(RM) *.class
