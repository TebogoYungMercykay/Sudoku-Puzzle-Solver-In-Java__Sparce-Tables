default:
	javac *.java

run:
	java Main

clean:
	rm -f *.class
	reset
	clear

tar:
	tar -cvz *.java makefile -f Sudoku.tar.gz

unzip:
	tar -zxvf *.tar