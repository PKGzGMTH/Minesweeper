# **Minesweeper (Java project)**

This is Minesweeper Build with Java. It's might not fit with your screen. sorry :v

You can play this game with 3 difficulty level.
* easy (map size 14x10 and 10 bombs)
* medium (map size 20x16 and 24 bombs)
* hard (map size 30x16 and 52 bombs)

## Build project

* before you build project make sure you have no Minesweeper.jar file in root directory.

go to root directory of this project and enter this command

```bash
rm Minesweeper.jar; javac -d classes .\srcs\Main.java .\srcs\ui\*;
cd classes; jar -cfe Minesweeper.jar Main .\Main.class .\ui\*.class;
cd ..; mv classes\Minesweeper.jar .
```
the output file (Minesweeper.jar) will appear in root directory

## Run project
go to root directory of this project and enter this command
```bash
java -jar Minesweeper.jar
```
## GLFH!
