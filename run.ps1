javac -d classes .\srcs\Main.java .\srcs\ui\*
cd classes; jar -cfe Minesweeper.jar Main .\Main.class .\ui\*.class; java -jar Minesweeper.jar; cd ..
