rm -rf out
mkdir -p out/server
mkdir -p out/client

javac -d out/server src/server/*.java
javac -d out/client src/client/*.java