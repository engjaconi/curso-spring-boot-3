# curso-spring-boot-3
Curso de Spring Boot 3 da Udemy.
## Resumo:
 * Adicionado o flyway para gerenciar as alterações no banco de dados.

Para rodar via terminal o flyway, pulando os testes para ser mais rápido o comando. Não é recomendado pular os testes.
> mvn clean package spring-boot:run -DskipTests

Caso utilize um plugin do flyway, e não uma dependência no pom, execute o comando na raiz do projeto:
> mvn flyway:migrate

Plugin:
```
<plugin>
    <groupId>org.flywaydb</groupId>
    <artifactId>flyway-maven-plugin</artifactId>
    <configuration>
        <url>jdbc:mysql://localhost:3306/curso-spring-boot-3?useTimezone=true&serverTimezone=UTC&useSSL=false</url>
        <user>root</user>
        <password>123456789</password>
    </configuration>
    <dependencies>
        <dependency>
            <groupId>mysql</groupId>
            <artifactId>mysql-connector-java</artifactId>
            <version>${mysql.version}</version>
            <exclusions>
                <exclusion>
                    <artifactId>slf4j-api</artifactId>
                    <groupId>org.slf4j</groupId>
                </exclusion>
            </exclusions>
        </dependency>
    </dependencies>
</plugin> 
```
