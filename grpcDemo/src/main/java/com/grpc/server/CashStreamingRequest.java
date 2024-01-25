package com.grpc.server;

import com.gen.models.bankservice.Balance;
import com.gen.models.bankservice.DepositeRequest;

import io.grpc.stub.StreamObserver;

public class CashStreamingRequest implements StreamObserver<DepositeRequest>{
	private StreamObserver<Balance> balanceStreamObserver;
	private int acc_balance; 
	
	public CashStreamingRequest(StreamObserver<Balance> balanceStreamObserver) {
		this.balanceStreamObserver = balanceStreamObserver;
	}

	@Override
	public void onNext(DepositeRequest value) {
		int accno = value.getAccNo();
		int depositeAmt = value.getAmount();
		AccountDatabase.addbalance(accno, depositeAmt);
		this.acc_balance  = AccountDatabase.getBalance(accno);
		
	}

	@Override
	public void onError(Throwable t) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onCompleted() {
		Balance balance = Balance.newBuilder().setAmount(this.acc_balance).build();
		this.balanceStreamObserver.onNext(balance);
		this.balanceStreamObserver.onCompleted();
	}

}
