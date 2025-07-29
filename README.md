# Getting Started

## Author

**Shrawanika**  
[[LinkedIn Profile](https://www.linkedin.com/in/shrawanika)] | [[GitHub Profile](https://github.com/shrawanika-w)] | [[Personal Website](https://sites.google.com/view/shrawanika)]

Demo created for **Spring AI Session**  
July 2025  
by **Shrawanika**

### Steps

1. _Versions_ - Java 21 | Spring Boot 3.5.5 | Apache Maven 3.9.7 | Chat model: gpt-4o-mini | Embedding model: text-embedding-ada-002
2. This project is created using Spring Initializer (https://start.spring.io/)
3. Get the OpenAI API KEY from https://platform.openai.com/api-keys to add to .env file
4. Create secrets.properties file in root folder and add variable OPENAI_API_KEY with the value _( OPENAI_API_KEY=your_openai_api_key )_
5. Run SpringAiDemoApplication.java. Edit the configuration, add paramters to CLI arguments via external file _( --spring.config.import=file:secrets.properties )_
6. Send the queries via Postman or curl, refer controller class comments for the request parameters
7. For Postman, you can import sample collection from - resources\Spring AI session.postman_collection.json

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

