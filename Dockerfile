# 1. ESTÁGIO DE CONSTRUÇÃO (BUILD STAGE)
# Usa uma imagem base com o JDK e as ferramentas necessárias para compilar o projeto.
# Recomendamos uma versão LTS do JDK, como a 17 ou 21.
FROM eclipse-temurin:21-jdk-alpine AS build

# Define o diretório de trabalho dentro do contêiner
WORKDIR /app

# Copia os arquivos de configuração do Gradle para o contêiner
# Isso permite que o Gradle baixe as dependências no cache da imagem,
# o que acelera builds subsequentes se os arquivos de configuração não mudarem.
#COPY gradlew .
#COPY gradle gradle
#COPY build.gradle.kts .
#COPY settings.gradle.kts .

# Copia o código-fonte
COPY src src

# Dá permissão de execução ao gradlew
RUN chmod +x gradlew

# Executa o build do projeto usando o wrapper do Gradle
# 'clean build' compila, testa e gera o JAR
# '-x test' é uma otimização comum para ignorar os testes no build do Docker
# se você já os executou localmente. Remova se quiser rodar os testes no contêiner.
RUN ./gradlew clean build -x test

# Define uma variável de ambiente com o caminho para o JAR compilado
# Substitua 'seu-projeto' e '0.0.1-SNAPSHOT' pelos nomes reais do seu projeto
ARG JAR_FILE=build/libs/CoffeeIN-0.0.1-SNAPSHOT.jar

# 2. ESTÁGIO DE EXECUÇÃO (RUNTIME STAGE)
# Usa uma imagem base JRE (Java Runtime Environment) menor para a aplicação final,
# garantindo que ela contenha apenas o necessário para rodar, e não as ferramentas de build.
FROM eclipse-temurin:21-jre-alpine AS final

# Cria um usuário não-root por segurança
RUN addgroup -S springboot && adduser -S springboot -G springboot

# Define o usuário que irá rodar a aplicação
USER springboot

# Define o diretório de trabalho
WORKDIR /app

# Copia o JAR do estágio de construção para o estágio de execução
# O comando '--from=build' é o que torna este um build multi-stage
COPY --from=build /app/${JAR_FILE} app.jar

# O Spring Boot por padrão usa a porta 8080
EXPOSE 8080

# Comando para rodar a aplicação.
# O argumento '-Djava.security.egd=file:/dev/./urandom' é uma prática recomendada
# para acelerar a inicialização do Spring Boot em ambientes virtuais/cloud.
CMD ["java", "-Djava.security.egd=file:/dev/./urandom", "-jar", "app.jar"]