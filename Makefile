JVM = java
JAR = jar
JC = javac
PNAME = Matriks
JFLAGS = -g -d .
JARFLG = cfe
MAIN = Driver
SRC = src

.SUFFIXES = .java .class

all: clean runJar

Matriks:
	$(JC) $(JFLAGS) $(addprefix $(SRC)/, *)

run: Matriks
	$(JVM) $(addprefix $(PNAME)., $(MAIN))

tubes.jar: Matriks
	$(JAR) $(JARFLG) tubes.jar $(addprefix $(PNAME)., $(MAIN)) $(PNAME)

runJar: tubes.jar
	$(JVM) -jar tubes.jar

.PHONY: clean all

clean:
	$(RM) -r Matriks *.jar
