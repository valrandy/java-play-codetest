package protocol;

import java.io.Serializable;

public class Message {

	public interface Request extends Serializable {
		
	}
	
	public interface Response extends Serializable {
		
	}
	
	public static class SortCustomersRequest implements Request {

		/**
		 * 
		 */
		private static final long serialVersionUID = 1L;
		public final RequestParams requestParams;
		
		public SortCustomersRequest(RequestParams params) {
			requestParams = params;
		}
	}
	
	public static class SortCustomersResponse implements Response {
		/**
		 * 
		 */
		private static final long serialVersionUID = 1L;
		public final int status;
		public final String responseBody;
		
		public SortCustomersResponse(int status, String responseBody) {
			this.status = status;
			this.responseBody = responseBody;
		}
	}
 }
