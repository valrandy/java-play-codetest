package protocol;

import java.io.Serializable;

public class RequestParams implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String direction;
	private String requestBody;
	
	public String getDirection() {
		return direction;
	}
	public void setDirection(String direction) {
		this.direction = direction;
	}
	
	public String getRequestBody() {
		return requestBody;
	}
	
	public void setRequestBody(String requestBody) {
		this.requestBody = requestBody;
	}
}
