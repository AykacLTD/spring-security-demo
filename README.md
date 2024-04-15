Sure, here's the markdown code for the `README.md` file for your project:

```markdown
# Spring Security Test Project

This is a Spring Boot project that demonstrates the use of Spring Security for authentication and authorization.

This demo application uses custom login and new user creation pages. The application also demonstrates role-based authorization for different endpoints.

 Database schema file is available in the resources folder, resources/schema.sql.
 
 You need to update properties file and set; spring.sql.init.mode as always to create the schema and insert data into the tables. Then you can change it to never. 
## Technologies Used

- Java 17
- MySQL
- Spring Boot
- Spring Security
- Gradle

## Features

- User authentication using JDBC and in-memory user details manager.
- Role-based authorization for different endpoints.
- Custom login and logout pages.

## Setup

### Prerequisites (tested versions )

- JDK 17 or later
- MySQL 8.2.0 or later
- Spring Boot 3.2.4 or later
- Spring Security 6.1.5 or later  
- Gradle 4.0 or later
- Thymeleaf 3.2.4 or later

### Docker run command for MySQL
docker command to run mysql container with locally mounted volume and environment variables
```docker run -d \        
  --name mysql \
  -e MYSQL_ROOT_PASSWORD=admin \
  -e MYSQL_DATABASE=mydb \
  -e MYSQL_USER=us \
  -e MYSQL_PASSWORD=pw \
  -v /Users/user1/Workspace/dockerVolumes/mysql:/var/lib/mysql \
  -p 3306:3306 \
  mysql:8.2.0

### Configuration

The database connection details are configured in the `SecurityAppConfig.java` file. Update the following properties with your database details:

```java
@Value("${spring.datasource.username}")
private String dbUser;

@Value("${spring.datasource.password}")
private String dbUserPassword;

@Value("${spring.datasource.url}")
private String dbUrl;

@Value("${spring.datasource.driver-class-name}")
private String driverName;
```

### Running the Application

To run the application, navigate to the project directory and run the following command:

```bash
./gradlew bootRun
```

## Usage

After starting the application, navigate to `http://localhost:8080/login` in your web browser to access the login page.

## References

- [Official Gradle documentation](https://docs.gradle.org)
- [Spring Boot Gradle Plugin Reference Guide](https://docs.spring.io/spring-boot/docs/3.2.4/gradle-plugin/reference/html/)
- [Create an OCI image](https://docs.spring.io/spring-boot/docs/3.2.4/gradle-plugin/reference/html/#build-image)
- [Spring Security](https://docs.spring.io/spring-boot/docs/3.2.4/reference/htmlsingle/index.html#web.security)
- [Spring Web](https://docs.spring.io/spring-boot/docs/3.2.4/reference/htmlsingle/index.html#web)

## License

This project is open source and available under the [MIT License](LICENSE).
```

Please replace `[MIT License](LICENSE)` with the actual link to your license file if you have one. If you don't have a license file, you can create one or remove the License section.