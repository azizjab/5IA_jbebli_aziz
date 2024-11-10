# Utilisez une image de base avec Java 17
FROM openjdk:17-alpine

# Copiez le fichier JAR dans l'image Docker
COPY target/5IA_*.jar /aziz_jbebli_image.jar

# Définissez la commande d'entrée pour exécuter l'application
CMD ["java", "-jar", "/aziz_jbebli_image.jar"]
