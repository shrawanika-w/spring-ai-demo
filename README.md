# Getting Started

## Author

**Shrawanika**  
[[LinkedIn Profile](https://www.linkedin.com/in/shrawanika)] | [[GitHub Profile](https://github.com/shrawanika-w)] | [[Personal Website](https://sites.google.com/view/shrawanika)]

Demo created for **Spring AI Session**  
July 2025  
by **Shrawanika**

### Steps

1. This project is created using Spring Initializer 
2. Get the Open AI KEY from https://platform.openai.com/api-keys to add to .env file
3. Create .env file in root folder and add variable OPENAI_API_KEY with the value
4. Run SpringAiDemoApplication.java
5. Send the queries via Postman or curl, refer controller class comments for the request parameters
6. For Postman, you can import sample collection from - resources\Spring AI session.postman_collection.json

### Reference Documentation
For further reference, please consider the following sections:

* [Official Apache Maven documentation](https://maven.apache.org/guides/index.html)
* [Spring Boot Maven Plugin Reference Guide](https://docs.spring.io/spring-boot/3.5.5-SNAPSHOT/maven-plugin)
* [Create an OCI image](https://docs.spring.io/spring-boot/3.5.5-SNAPSHOT/maven-plugin/build-image.html)
* [Spring Web](https://docs.spring.io/spring-boot/3.5.5-SNAPSHOT/reference/web/servlet.html)
* [OpenAI](https://docs.spring.io/spring-ai/reference/api/chat/openai-chat.html)
* [PDF Document Reader](https://docs.spring.io/spring-ai/reference/api/etl-pipeline.html#_pdf_page)

### Guides
The following guides illustrate how to use some features concretely:

* [Building a RESTful Web Service](https://spring.io/guides/gs/rest-service/)
* [Serving Web Content with Spring MVC](https://spring.io/guides/gs/serving-web-content/)
* [Building REST services with Spring](https://spring.io/guides/tutorials/rest/)

### Maven Parent overrides

Due to Maven's design, elements are inherited from the parent POM to the project POM.
While most of the inheritance is fine, it also inherits unwanted elements like `<license>` and `<developers>` from the parent.
To prevent this, the project POM contains empty overrides for these elements.
If you manually switch to a different parent and actually want the inheritance, you need to remove those overrides.

