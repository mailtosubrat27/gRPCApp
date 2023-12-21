package com.grpc;

import com.gen.models.Person;

public class PersonDemo {

	public static void main(String[] args) {
		Person p = Person.newBuilder().setName("John").build();
		System.out.println(p.getName());
	
	}

}
