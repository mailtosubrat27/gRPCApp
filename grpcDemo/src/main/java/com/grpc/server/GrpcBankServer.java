package com.grpc.server;

import java.io.IOException;

import io.grpc.Server;
import io.grpc.ServerBuilder;

public class GrpcBankServer {

	public static void main(String[] args) throws IOException, InterruptedException {
		System.out.println("Bank Server start.. ");
		Server server = ServerBuilder.forPort(6565)
		.addService(new BankService())
		.build();
		
		server.start();
		System.out.println("Server started, listening on " + 6565);
		 
	 

		server.awaitTermination();
	

	}

}
