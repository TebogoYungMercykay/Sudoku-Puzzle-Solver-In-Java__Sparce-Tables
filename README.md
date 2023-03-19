________________________________________________________________________________________
#                    Sudoku Puzzle in Java                               
________________________________________________________________________________________
 ## Description
 
 - Sudoku is a puzzle game that first appeared in the 18th Century. The puzzle consists of a 3x3 grid of 3x3 cells.
A cell is a space where a single number can be filled in. In traditional Sudoku, a cell can be any number between 1 and 9 inclusive.
A 3x3 grid of cells is called a block; For this implementation, a 3x3 grid of blocks is called a board.
The goal is to fill every cell on the board with a number without having any duplicate numbers in any of the rows, columns and blocks.
This is a more general version of Sudoku where an m × n grid is used. For clarity reasons, I use the variables:numRows×numCols.
In this format, every cell can contain the values [1,numRows ∗ numCols], and the normal no duplicate rule applies.
For example, in a 2x3 grid, the values [1,2*3] = [1,6] are allowed. Thus each row, column and grid must contain the values 1 through 6.

- ### Data Structures
	 - Linked Lists
	 - 3D Sparse Table

- ### Iterative methods
	 - Sole Candidate
	 - Unique Candidate
	 - Duplicate Cells
________________________________________________________________________________________
## UML Diagrams And Functions

#### Classes: Linked List and Node
<img alt="UML diagram and functions" src="">

#### Classes: Cell, Board and SudokuSolver
<img alt="UML diagram and functions" src="">

________________________________________________________________________________________

### Requirements before running codes:
- Install an IDE that compiles and runs Java codes. Recommendation VS Code
- How to setup WSL Ubuntu terminal shell and run it from Visual Studio Code: 
 visit: https://www.youtube.com/watch?v=fp45HpZuhS8&t=112s
- How to Install Java JDK 17 on Windows 11: https://www.youtube.com/watch?v=ykAhL1IoQUM&t=136s
- ## Installing Oracle JDK on Windows subsystem for Linux.
	- Run WSL as Administrator
	- set -ex
	- NB: Update links for other JDK Versions 
	- export JDK_URL=http://download.oracle.com/otn-pub/java/jdk/8u131-b11/d54c1d3a095b4ff2b6607d096fa80163/jdk-8u131-linux-x64.tar.gz
	- export UNLIMITED_STRENGTH_URL=http://download.oracle.com/otn-pub/java/jce/8/jce_policy-8.zip
	- wget --no-cookies --header "Cookie: oraclelicense=accept-securebackup-cookie" ${JDK_URL}
	- Extract the archive: tar -xzvf jdk-*.tar.gz
	- Clean up the tar: rm -fr jdk-*.tar.gz
	- Make the jvm dir: sudo mkdir -p /usr/lib/jvm
	- Move the server jre: sudo mv jdk1.8* /usr/lib/jvm/oracle_jdk8
	- Install unlimited strength policy: wget --no-cookies --header "Cookie: oraclelicense=accept-securebackup-cookie" ${UNLIMITED_STRENGTH_URL}
	- unzip jce_policy-8.zip
	- mv UnlimitedJCEPolicyJDK8/local_policy.jar /usr/lib/jvm/oracle_jdk8/jre/lib/security/
	- mv UnlimitedJCEPolicyJDK8/US_export_policy.jar /usr/lib/jvm/oracle_jdk8/jre/lib/security/
	- sudo update-alternatives --install /usr/bin/java java /usr/lib/jvm/oracle_jdk8/jre/bin/java 2000
	- sudo update-alternatives --install /usr/bin/javac javac /usr/lib/jvm/oracle_jdk8/bin/javac 2000
	- sudo echo "export J2SDKDIR=/usr/lib/jvm/oracle_jdk8 export J2REDIR=/usr/lib/jvm/oracle_jdk8/jre export PATH=$PATH:/usr/lib/jvm/oracle_jdk8/bin:/usr/lib/jvm/oracle_jdk8/db/bin:/usr/lib/jvm/oracle_jdk8/jre/bin export JAVA_HOME=/usr/lib/jvm/oracle_jdk8 export DERBY_HOME=/usr/lib/jvm/oracle_jdk8/db" | sudo tee -a /etc/profile.d/oraclejdk.sh
_______________________________________________________________________________________

 ## Makefile
 ##### NB: A makefile Is Included to compile and run the codes on the terminal with the following commands:=
- make clean
- make
- make run

```Java
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
```
_______________________________________________________________________________________
