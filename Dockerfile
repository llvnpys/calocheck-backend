# Azul Zulu JRE 21 사용
FROM azul/zulu-openjdk:21-jre
WORKDIR /app

# 빌드된 jar 복사
COPY build/libs/*.jar app.jar

# 도커 프로필 사용
ENV SPRING_PROFILES_ACTIVE=docker

EXPOSE 8080
ENTRYPOINT ["java","-jar","/app/app.jar"]