package kw.kng.sms;

import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class KngNadMvcApplication 
{
	@Bean
	public ModelMapper modelMapper()
	{
		return new ModelMapper();
	}


	public static void main(String[] args) 
	{
		SpringApplication.run(KngNadMvcApplication.class, args);
	}

}

/*
1. What is ModelMapper???

Ans:

ModelMapper is a Java library that provides an easy and efficient way to map objects from one type to another.
It is often used in applications where there is a need to convert between different types of objects, such as mapping between data transfer objects (DTOs) 
and entity objects in a Spring application.

Key Features of ModelMapper:
Object Mapping: Automatically maps fields between source and destination objects based on their names and types.
Type Conversion: Handles conversion between different data types.
Flexible Configuration: Allows customization of mapping logic through configuration.
Nested Mapping: Supports mapping of nested properties and complex object structures.


Reduced Boilerplate: Simplifies the code required for object mapping.
Consistency: Ensures consistent mapping logic throughout the application.
Maintainability: Makes it easier to maintain mapping logic as the application evolves.

2. How to Implement ModelMapper into ypur project???

Ans:
2.1. First inject the depenedency in pom.xml file

			<!--  Model Mapper Dependency START -->
		<dependency>
			<groupId>org.modelmapper</groupId>
			<artifactId>modelmapper</artifactId>
			<version>3.2.0</version>
		</dependency>
		<!-- END -->

2.2 Annotate `@Bean` and inject this as an object in File that you use to run.

@Bean
	public ModelMapper modelMapper()
	{
		return new ModelMapper();
	}

2.3 Inject into the required class using CONSTRUCTOR INJECTION. Make sure that the Binding class for the corresponding class is injected.

*/