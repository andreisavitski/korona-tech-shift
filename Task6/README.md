Клиент-серверное приложение с использованием Swing (клиент) и TCP сокетов (сервер).

Сборка выполняется из корня ./gradlew build

В результате будут созданы 2 jar:

build\libs\Task6-server-1.0.0.jar - сервер

build\libs\Task6-client-1.0.0.jar - клиент

Запуск сервера и клиента:

java -jar build\libs\Task6-server-1.0.0.jar

java -jar build\libs\Task6-client-1.0.0.jar

Порт можно задать в Task6\src\main\resources\server.properties. По умолчанию используется 8085.
