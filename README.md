# List of practiced topics

## Configuaration
1. Create Maven project with webapp archertype in Esclipse.
2. Setup pom file 
[[pom.xml]()]
   - ```springframework.version``` property with ```spring-webmvc``` dependency
   - ```springsecurity.version``` property with ```spring-security-web``` and ``spring-security-config``` (must compatible with springframework version, may be different)
   - ```spring-security-taglabs``` dependency (for access user id/role in JSP)
   - ```javax.servlet-api, javax.servlet.jsp-api, jstl``` dependency (support Servlet, JSP and JSTL)
   - ```jaxb-api``` dependency (compensate for Java 9+ not including jaxb)
   - ```maven-war-plugin``` plugin (add GAV)
3. Project properties (Alt+Enter) -> Project facets -> Runtimes: Tomcat
4. Update Maven project (Alt+F5)
5. Create Spring Configuration class 
[[AppConfig]()]
   - @Configuration
   - @EnableWebMvc (<=> ```<mvc:annotation-driven>```)
   - @ComponentScan with *```basePackages```*
   - Define a bean for View Resolver
6. Create Spring Dispatcher Servlet Initializer extends AbstractAnnotationConfigDispatcherServletInitializer
[[SpringMvcInitializer]()]
   - Configure ```getServletConfigClasses()``` returns to the Configuration class.
   - Configure ```getServletMappings()```
7. Create Controller class 
[[DemoController]()]
   - @Controller
   - @RequestMapping, @GetMapping, @PostMapping
8. Create 'view' folder in ```src/main/webapp/WEB-INF``` to contain all JSP pages
9. Create JSP page 
[[home.jsp]()]
10. Run project on server to test Spring MVC
11. Create Spring Security Initializer extends AbstractSecurityWebApplicationInitializer 
[[SecurityInitializer]()]
12. Create Spring Security Configuration class extends WebSecurityConfigurerAdapter
[[SecurityConfig]()]
    - @Configuration
    - @EnableWebSecurity
    - Override ```configure(AuthenticationManagerBuilder)``` to add users for in-memory authentication
13. Run project on server to test Spring Security

## Custom Login Form
1. Modify Spring Security Configuration to reference custom login form by overriding ```configure(HttpSecurity)```.
[[SecurityConfig]()]
2. Develop a Controller to show the custom login form 
[[LoginController]()]
3. Create customer login form
[[login-page.jsp]()]
   - Spring MVC form tag: ```<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>``` (to post username and password to the Authentication) 
   - JSTL: ```<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>``` (to get the error message)
4. Add logout function
   - Add logout support to Spring Security Configuration 
[[SecurityConfig]()]
   - Add logout button to JSP page
[[home.jsp]()]
   - Update login form to display logout message 
[[styled-login-page.jsp]()]

## User Stuffs
1. Display user info 
[[home.jsp]()]
   - Spring Security JSP Tag Library: ```<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags"%>```
   - Display user name: ```<security:authentication property="principal.username"/>```
   - Display user roles: ```<security:authentication property="principal.authorities"/>```
2. Restrict access based on roles: ```antMatchers(<path>).hasRole(<role>)``` 
[[SecurityConfig]()]
3. Custom Access Denied Page
   - Configure page path in Security Configuration file with ```exceptionHandling().accessDeniedPage(<path>)``` 
[[SecurityConfig]()]
   - Create supporting controller code and JSP page 
[[access-denied.jsp]()][[LoginController]()]
   - [!] Internal browser of Eclipse does not display Custom Access Denied Page
3. Display content based on Roles: ```<security:authorize access="hasRole('<role>')"> 
[[home.jsp]()]

## Database
1. Create database schema and tables (preferred schema for Spring Security) 
[[create-database.sql]()]
   - ```users``` with usename (PK, varchar), password (varchar), enabled (tinyint)
   - ```authorities``` with username (FK, UNI, varchar), authority (UNI, varchar) with ```ROLE_``` prefix
2. Add database support to POM file 
   - JDBC driver: ```mysql-connector-java``` dependency 
   - Database Connection Pool: ``com.mchange.c3p0``` dependency
3. Create JDBC properties file in src/main/resources
[[persistence-mysql.properties]()]
4. Define DataSource in Spring Configuration with @PropertySource 
[[AppConfig]()]
   - Inject Enviroment to hold data properties with @AutoWired
   - Define DataSource object bean
     - Create connection pool
     - Set the JDBC driver
     - Set database connection properties
     - Set connection pool properties
5. Update Security Configuration to use JDBC 
[[SecurityConfig]()]
   - Inject DataSource with @AutoWired
   - ```auth.jdbcAuthentication().dataSource(<data source>)```

## Notes/Tips
- If ```src/main/java``` and ```src/test/java``` are not availalbe, go to Build Path -> Export folders
- Select override method: Right click -> Source (Alt+Shift+S) -> Override methods
- Change context root to resolve duplicate name app on the server: Properties -> Web Project Settings.
- Change Servlet 2.3 (generated by archertype webapp) to Servlet 3.0, to use ```${pageContext.request.contextPath}```
  - Modify [web.xml]()
  - Close project and delete it from the workspace (don't delete files on the disk)
  - Delete .project and .classpath files and .settings directory from the project folder
  - Re-import project: Import -> Existing Maven Project
  - Clean the server
- We can customize AuthenticationFailureHandler by Java code 
[[URL](https://www.baeldung.com/spring-security-custom-authentication-failure-handler)]
- ```<form:from>``` automatically adds CSRF tokens
- Password formats in Spring Security 5: noop (plain text), bcrypt (BCrypt hashing, 60 characters)
- Resources in src/main/resources will be automatically copied to classpath during Maven build
- Implement "Remember me" function 
[[URL](https://www.baeldung.com/spring-security-remember-me)]








































