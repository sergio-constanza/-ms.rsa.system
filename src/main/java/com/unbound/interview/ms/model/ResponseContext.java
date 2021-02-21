package com.unbound.interview.ms.model;

import java.util.List;

public class ResponseContext {
	public String keyId;
	public List<String> keyIds;
	public String signature;
	public Boolean isValid;
	
	private String status;
	private String description;
	
	public ResponseContext() {
		
	}
	
	public ResponseContext(List<String> keyIds) {
		this.status = "SUCCESS";
		this.keyIds = keyIds;
	}

	public ResponseContext(String keyId, String signature) {
		this.status = "SUCCESS";
		this.keyId = keyId;
		this.signature = signature;
	}
	
	public ResponseContext(Boolean isValid) {
		this.status = "SUCCESS";
		this.isValid = isValid;
	}

	@Override
	public String toString() {
		return "ResponseContext [keyId=" + keyId + ", keyIds=" + keyIds + ", signature=" + signature + ", isValid="
				+ isValid + ", status=" + status + ", description=" + description + "]";
	}
	
	public String getKeyId() {
		return keyId;
	}
	
	public void setKeyId(String keyId) {
		this.keyId = keyId;
	}
	
	public List<String> getKeyIds() {
		return keyIds;
	}
	
	public void setKeyIds(List<String> keyIds) {
		this.keyIds = keyIds;
	}
	
	public String getSignature() {
		return signature;
	}
	
	public void setSignature(String signature) {
		this.signature = signature;
	}
	
	public Boolean getIsValid() {
		return isValid;
	}
	
	public void setIsValid(Boolean isValid) {
		this.isValid = isValid;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
	
}
