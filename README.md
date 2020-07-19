# Practice Topics

## Enviroment Setup
#### Maven Project Setup
1. Create Maven project with webapp archertype in Esclipse
2. Setup POM file 
[[pom.xml](https://github.com/cpulover-practice/spring-security/blob/master/pom.xml)]
   - Change JDK version (Servlet 3.0 require JDK 6+)
   - ```springframework.version``` property with ```spring-webmvc``` dependency
   - ```springsecurity.version``` property with ```spring-security-web``` and ```spring-security-config``` dependency (must compatible with ```springframework``` version, may be different)
   - ```spring-security-taglabs``` dependency: access user id and roles in JSP
   - ```javax.servlet-api, javax.servlet.jsp-api, jstl``` dependency: support Servlet, JSP and JSTL
   - ```jaxb-api``` dependency: compensate for Java 9+ not including jaxb
   - ```maven-war-plugin``` plugin (GAV)
   - ```mysql-connector-java``` dependency: JDBC driver to connect to database
   - ```com.mchange.c3p0``` dependency: setup database connection pool

#### Spring MVC Configuration
1. Create Spring MVC Configuration class 
[[AppConfig](https://github.com/cpulover-practice/spring-security/blob/master/src/main/java/com/cpulover/springsecurity/config/AppConfig.java)]
   - *__@Configuration__*
   - *__@EnableWebMvc__* (<=> ```<mvc:annotation-driven>```)
   - *__@ComponentScan__* with *```basePackages```*
   - Define a bean for View Resolver
2. Create Spring Dispatcher Servlet Initializer extends AbstractAnnotationConfigDispatcherServletInitializer
[[SpringMvcInitializer](https://github.com/cpulover-practice/spring-security/blob/master/src/main/java/com/cpulover/springsecurity/config/SpringMvcInitializer.java)]
   - Configure ```getServletConfigClasses()``` returns to Spring MVC Configuration class.
   - Configure ```getServletMappings()``` for character separating different parts of a URL, normally ```/```
3. Create Controller class 
[[DemoController](https://github.com/cpulover-practice/spring-security/blob/master/src/main/java/com/cpulover/springsecurity/controller/DemoController.java)]
   - *__@Controller__*
   - *__@RequestMapping__*, *__@GetMapping__*, *__@PostMapping__*
4. Create 'view' folder in ```src/main/webapp/WEB-INF``` to contain all JSP pages
5. Create JSP page 
[[home.jsp](https://github.com/cpulover-practice/spring-security/blob/master/src/main/webapp/WEB-INF/view/home.jsp)]
6. Run project on server to test Spring MVC

#### Spring Security Configuration
1. Create Spring Security Initializer extends ```AbstractSecurityWebApplicationInitializer```
[[SecurityInitializer](https://github.com/cpulover-practice/spring-security/blob/master/src/main/java/com/cpulover/springsecurity/config/SecurityInitializer.java)]
2. Create Spring Security Configuration class ```extends WebSecurityConfigurerAdapter```
[[SecurityConfig](https://github.com/cpulover-practice/spring-security/blob/master/src/main/java/com/cpulover/springsecurity/config/SecurityConfig.java)]
    - @*__Configuration__*
    - *__@EnableWebSecurity__*
    - Override ```configure(AuthenticationManagerBuilder)``` to add users for authentication
3. Run project on server to test Spring Security

#### Database Setup
1. Create database schema and tables (preferred schema for Spring Security) 
[[create-database.sql](https://github.com/cpulover-practice/spring-security/blob/master/sql-scripts/create-database.sql)]
   1. ```users``` with usename (PK, varchar), password (varchar), enabled (tinyint)
   2. ```authorities``` with username (FK, UNI, varchar), authority (UNI, varchar) with ```ROLE_``` prefix
2. Create JDBC properties file in ```src/main/resources``` (to inject the properties in Configuration files later, not hard-coding) 
[[persistence-mysql.properties](https://github.com/cpulover-practice/spring-security/blob/master/src/main/resources/persistence-mysql.properties)]
3. Define DataSource in Spring MVC Configuration with *__@PropertySource__*
[[AppConfig](https://github.com/cpulover-practice/spring-security/blob/master/src/main/java/com/cpulover/springsecurity/config/AppConfig.java)]
   1. Inject Enviroment to hold data properties with *__@AutoWired__*
   2. Define DataSource object bean
      - Create connection pool
      - Set the JDBC driver
      - Set database connection properties
      - Set connection pool properties
4. Update Spring Security Configuration to use JDBC 
[[SecurityConfig](https://github.com/cpulover-practice/spring-security/blob/master/src/main/java/com/cpulover/springsecurity/config/SecurityConfig.java)]
   1. Inject DataSource with *__@AutoWired__*
   2. ```auth.jdbcAuthentication().dataSource(<data source>)```

---

## Custom Login Form
1. Modify Spring Security Configuration to reference custom login form by overriding ```configure(HttpSecurity)```.
[[SecurityConfig](https://github.com/cpulover-practice/spring-security/blob/master/src/main/java/com/cpulover/springsecurity/config/SecurityConfig.java)]
2. Create a controller request returning to the custom login form 
[[LoginController](https://github.com/cpulover-practice/spring-security/blob/master/src/main/java/com/cpulover/springsecurity/controller/LoginController.java)]
3. Create customer login form
[[login-page.jsp](https://github.com/cpulover-practice/spring-security/blob/master/src/main/webapp/WEB-INF/view/login-page.jsp)]
   - Spring MVC form tag: ```<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>``` (to post username and password to the Authentication) 
   - JSTL: ```<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>``` (to get the error message)
4. Add logout function
   1. Add logout support to Spring Security Configuration 
[[SecurityConfig](https://github.com/cpulover-practice/spring-security/blob/master/src/main/java/com/cpulover/springsecurity/config/SecurityConfig.java)]
   2. Add logout button to JSP page
[[home.jsp](https://github.com/cpulover-practice/spring-security/blob/master/src/main/webapp/WEB-INF/view/home.jsp)]
   3. Update login form to display logout message 
[[styled-login-page.jsp](https://github.com/cpulover-practice/spring-security/blob/master/src/main/webapp/WEB-INF/view/styled-login-page.jsp)]

---

## User Security
1. Display user info 
[[home.jsp](https://github.com/cpulover-practice/spring-security/blob/master/src/main/webapp/WEB-INF/view/home.jsp)]
   - Spring Security JSP Tag Library: ```<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags"%>```
   - Display user name: ```<security:authentication property="principal.username"/>```
   - Display user roles: ```<security:authentication property="principal.authorities"/>```
2. Restrict access based on roles: ```antMatchers(<path>).hasRole(<role>)``` 
[[SecurityConfig](https://github.com/cpulover-practice/spring-security/blob/master/src/main/java/com/cpulover/springsecurity/config/SecurityConfig.java)]
3. Custom Access Denied Page
   1. Configure page path in Security Configuration file with ```exceptionHandling().accessDeniedPage(<path>)``` 
[[SecurityConfig](https://github.com/cpulover-practice/spring-security/blob/master/src/main/java/com/cpulover/springsecurity/config/SecurityConfig.java)]
   2. Create supporting controller code and JSP page 
[[access-denied.jsp](https://github.com/cpulover-practice/spring-security/blob/master/src/main/webapp/WEB-INF/view/access-denied.jsp)] 
[[LoginController](https://github.com/cpulover-practice/spring-security/blob/master/src/main/java/com/cpulover/springsecurity/controller/LoginController.java)]
   - [!] Internal browser of Eclipse does not display Custom Access Denied Page
4. Display content based on Roles: ```<security:authorize access="hasRole('<role>')">``` 
[[home.jsp](https://github.com/cpulover-practice/spring-security/blob/master/src/main/webapp/WEB-INF/view/home.jsp)]


---

## Notes - Tips
- 📌 [Maven] If ```src/main/java``` and ```src/test/java``` are not availalbe, go to Build Path ->  Order and Export -> Choose JRE and Maven Dependencies.
- 📌 [Esclipse] Select override method: Right click -> Source (Alt+Shift+S) -> Override methods
- 📌 [Server] Change Context Root (Context Path) to resolve duplicate name app on the server: Properties -> Web Project Settings.
- ℹ️ [JSP] ```<form:from>``` automatically adds CSRF tokens
- ℹ️ [Spring Security] Password formats in Spring Security 5: 
  - *noop:* plain text 
  - *bcrypt:* BCrypt hashing, 60 characters (prefer)
- ℹ️ [Maven] Resources in ```src/main/resources``` will be automatically copied to classpath during Maven build
- 📌 [Server] Project properties (Alt+Enter) -> Project facets -> Runtimes: Tomcat
- 📌 [Maven] Update Maven project (Alt+F5)
- 📌 [Maven] Change Servlet 2.3 (generated by archertype webapp) to Servlet 3.0, to use ```${pageContext.request.contextPath}```
  - Modify [web.xml](https://github.com/cpulover-practice/spring-security/blob/master/src/main/webapp/WEB-INF/web.xml)
  - Close project and delete it from the workspace (don't delete files on the disk)
  - Delete .project and .classpath files and .settings directory from the project folder
  - Re-import project: Import -> Existing Maven Project
  - Clean the server

#### Extra References
- Customize AuthenticationFailureHandler by Java 
[[URL](https://www.baeldung.com/spring-security-custom-authentication-failure-handler)]
- Implement "Remember me" function 
[[URL](https://www.baeldung.com/spring-security-remember-me)]

---

[**Go to top**](#practice-topics)





































