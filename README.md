# List of practiced topics

## Configuaration
1. Create Maven project with webapp archertype in Esclipse.
2. Setup pom file 
[[pom.xml]()]
   - ```springframework``` property and ```spring-webmvc``` dependency
   - ```javax.servlet-api, javax.servlet.jsp-api, jstl``` dependency (support Servlet, JSP and JSTL)
   - ```jaxb-api``` dependency (compensate for Java 9+ not including jaxb)
   - ```maven-war-plugin``` plugin (add GAV)
3. Project properties (Alt+Enter) -> Project facets -> Runtimes: Tomcat
4. Update Maven project (Alt+F5)
5. Create Configuration class 
[[AppConfig]()]
   - @Configuration
   - @EnableWebMvc (<=> ```<mvc:annotation-driven>```)
   - @ComponentScan with *```basePackages```*
   - Define a bean for View Resolver
6. Create Spring Dispatcher Servlet Initializer extends AbstractAnnotationConfigDispatcherServletInitializer
[[SpringMvcInitializer]()]
   - Configure ```getServletConfigClasses()``` returns to the Configuration class.
   - Configure ```getServletMappings()```

## Notes/Tips
- If ```src/main/java``` and ```src/test/java``` are not availabe, go to Build Path -> Export folders


















































