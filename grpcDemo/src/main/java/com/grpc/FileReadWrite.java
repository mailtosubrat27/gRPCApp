package com.grpc;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import com.gen.models.Person;

public class FileReadWrite {

	public static void main(String[] args) throws IOException {
//		Person person = Person.newBuilder()
//				.setName("raj")
//				.setAge(10)
//				.build();
		Path path = Paths.get("Person-1");
//		Files.write(path, person.toByteArray());
		
		byte[] bytes= Files.readAllBytes(path);
		System.out.println(Person.parseFrom(bytes).getGender());

	}

}
