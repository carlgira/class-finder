# class-finder

Utilities for class finder. Using SQLITE and Spring-boot.


git clone https://github.com/carlgira/class-finder

# Compile

cd class-finder-db
mvn clean install

cd class-finder-ws
mvn clean package

cd class-finder-util
mvn clean package

cd jarscan-repo-creator
mvn clean package

# Scan jars of product

- Copy the jarscan-cargira.jar to the product installation folder and execute

java -jar jarscan-carlgira.jar -writeFiles Main

- This will generate a file or files with name (jarscan-respo.txt)

- Bulk all the files in only one and modify the name of the file with the name <productid>-<version>.txt

# Create SQLITE file

java -jar -Dsqlite.store.file=class-finder.db class-finder-util-1.0.0.jar import <productid>-<version>.txt

# Run App

- Add the property sqlite.store.file with the path of the sqlite file.

java -jar -Dsqlite.store.file=class-finder.db class-finder-ws-1.0.0.war










