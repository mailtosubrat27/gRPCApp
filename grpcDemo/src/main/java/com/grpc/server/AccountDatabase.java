package com.grpc.server;

import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class AccountDatabase {
	public static final Map<Integer, Integer> MAP = IntStream.rangeClosed(1, 20)
			.boxed()
			.collect(Collectors.toMap(Function.identity(), v -> v*10));
			
	public static Integer getBalance(Integer accId) {
		return MAP.get(accId);
	}
	
	public static void addbalance(Integer accId, Integer amount) {
//		MAP.put(accId, amount);
		MAP.computeIfPresent(accId, (k, v) -> v + amount);
		
	}
	
	public static void deductBalance(Integer accId, Integer amount) {
		MAP.computeIfPresent(accId, (k, v) -> v - amount);
	}
	
	
	
}