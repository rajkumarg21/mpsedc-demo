package com.mpsedc.exception;

class ErrorResponse {
    private int status;
    private String message;
    private boolean apiStatus;

    public ErrorResponse(int status, String message,Boolean apiStatus) {
        this.status = status;
        this.message = message;
        this.apiStatus= apiStatus;
    }

	public ErrorResponse() {
		super();
		
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public Boolean getApiStatus() {
		return apiStatus;
	}

	public void setApiStatus(Boolean apiStatus) {
		this.apiStatus = apiStatus;
	}

	

   
}
