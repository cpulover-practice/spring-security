# List of practiced topics

## Configuaration
1. Create Maven project with webapp archertype in Esclipse.
2. Setup pom file 
[[pom.xml]()]
   - ```springframework``` property with ```spring-webmvc``` dependency
   - ```springsecurity``` property with ```spring-security-web``` and ``spring-security-config``` (must compatible with springframework version, may be different)
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


## Notes/Tips
- If ```src/main/java``` and ```src/test/java``` are not availabe, go to Build Path -> Export folders
- Select override method: right click -> source (Alt+Shift+S) -> override methods

















































