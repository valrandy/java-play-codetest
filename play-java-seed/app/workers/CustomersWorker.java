package workers;

import akka.actor.AbstractActor;
import akka.actor.Props;
import processor.CustomersProcessor;
import protocol.Message.SortCustomersRequest;
import protocol.Message.SortCustomersResponse;

public class CustomersWorker extends AbstractActor {

	public static final Props props = Props.create(CustomersWorker.class);
	@Override
	public Receive createReceive() {
	  return receiveBuilder()
	    .match(SortCustomersRequest.class, request -> {
	    	CustomersProcessor processor = new CustomersProcessor();
	    	
			String body = processor.sortCustomers(request.requestParams.getDirection(), 
					request.requestParams.getRequestBody());
			SortCustomersResponse response = new SortCustomersResponse(200, body);
			sender().tell(response, self());
	    })
	    .build();
	}
}
