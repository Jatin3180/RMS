package com.management.rms.model;


public class RspnsVO {
	
	  private String response;
	    public String getResponse() {
		return response;
	}

	public void setResponse(String response) {
		this.response = response;
	}

	public int getResponseCode() {
		return responseCode;
	}

	public void setResponseCode(int responseCode) {
		this.responseCode = responseCode;
	}

	public String getResponseMsg() {
		return responseMsg;
	}

	public void setResponseMsg(String responseMsg) {
		this.responseMsg = responseMsg;
	}

		private int responseCode;
	    private String responseMsg;

	    public RspnsVO() {
	    	
	    }


	    // Constructor for success response
	    public RspnsVO(String response,int responseCode, String responseMsg) {
	        this.response = response;
	        this.responseCode = responseCode;
	        this.responseMsg = responseMsg;
	      
	    }

	    // Constructor for error response
	    public RspnsVO(int responseCode, String responseMsg) {
	        this.response =null;
	        this.responseCode = responseCode;
	        this.responseMsg = responseMsg;
	       
	    }


}


