JVM = java
JC = javac
PNAME = Matriks
JFLAGS = -g -d .
MAIN = Driver
SRC = src

.SUFFIXES = .java .class

all: clean run

Matriks:
	$(JC) $(JFLAGS) $(addprefix $(SRC)/, *)

run: Matriks
	$(JVM) $(addprefix $(PNAME)., $(MAIN))

.PHONY: clean all

clean:
	$(RM) -r Matriks
