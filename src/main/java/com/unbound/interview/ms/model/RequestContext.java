package com.unbound.interview.ms.model;

public class RequestContext {
	public String data;
	public String signature;
	
	@Override
	public String toString() {
		return "RequestContext [data=" + data + ", signature=" + signature + "]";
	}
	
	public String getData() {
		return data;
	}
	
	public void setData(String data) {
		this.data = data;
	}
	
	public String getSignature() {
		return signature;
	}
	
	public void setSignature(String signature) {
		this.signature = signature;
	}
	
}
