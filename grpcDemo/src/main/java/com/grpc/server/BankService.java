package com.grpc.server;

import java.util.Iterator;

import com.gen.models.bankservice.Balance;
import com.gen.models.bankservice.BalanceCheckRequest;
import com.gen.models.bankservice.BankServiceGrpc.BankServiceImplBase;
import com.gen.models.bankservice.DepositeRequest;
import com.gen.models.bankservice.Money;
import com.google.rpc.Status;

import io.grpc.stub.StreamObserver;

public class BankService extends BankServiceImplBase {

	@Override
	public void getBalance(BalanceCheckRequest request, StreamObserver<Balance> responseObserver) {
		int accno = request.getAccNo();
//		Balance balance = Balance.newBuilder()
//		.setAmount(accno *10)
//		.build();
		
		Balance balance = Balance.newBuilder()
				.setAmount(AccountDatabase.getBalance(accno))
				.build();
		
		responseObserver.onNext(balance);
		responseObserver.onCompleted();
		System.out.println("Bankservice call done..");
	}
	
	public void withdrawAmout(BalanceCheckRequest request, StreamObserver<Money> responseObserver) {
		int accno = request.getAccNo();
		int amount = request.getAmount();
		int no = amount/10;
		int balance = AccountDatabase.getBalance(accno);
		
		for (int i = 0; i < no; i++) {
			Money money = Money.newBuilder().setValue(10).build();
			responseObserver.onNext(money);
			AccountDatabase.deductBalance(accno, 10);
			
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}

		}
		responseObserver.onCompleted();
	}

	@Override
	public StreamObserver<DepositeRequest> cashDeposite(StreamObserver<Balance> responseObserver) {
		
		return new CashStreamingRequest(responseObserver);
	}

}
