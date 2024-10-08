
FROM openjdk:17-jdk-slim
WORKDIR /app

# Copiar o arquivo JAR da aplicação para o container
COPY Territory/target/Territory-0.0.1-SNAPSHOT.jar /app/Territory-0.0.1-SNAPSHOT.jar

# Expõe a porta que a aplicação usará
EXPOSE 8080

# Comando para rodar a aplicação
CMD ["java", "-jar", "/app/Territory-0.0.1-SNAPSHOT.jar"]
