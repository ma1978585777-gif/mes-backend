FROM eclipse-temurin:21-jdk-jammy

# 应用端口
EXPOSE 8080

WORKDIR /apps/mes/

RUN mkdir -p /export/logs/mes

ENTRYPOINT ["java", "-server", "-Xms4G", "-Xmx4G", "-Xss256K", "-XX:+UseG1GC", "-Xloggc:/export/logs/mes/mes-gc.log", "-XX:+PrintGCDetails", "-XX:+PrintGCDateStamps", "-XX:+UseGCLogFileRotation", "-XX:NumberOfGCLogFiles=10", "-XX:GCLogFileSize=20M", "-XX:+PrintGCApplicationStoppedTime", "-XX:+PrintGCApplicationConcurrentTime", "-XX:+HeapDumpOnOutOfMemoryError", "-XX:HeapDumpPath=/export/logs/mes/", "-Dfile.encoding=utf-8", "-Djava.awt.headless=true", "-jar", "/apps/fire/mes-backend.jar"]
