package processor;

import java.io.IOException;
import java.util.List;

import org.joda.time.DateTimeComparator;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectReader;
import static protocol.Constants.DESC;
import data.Customer;

public class CustomersProcessor {

	ObjectMapper mapper;
	private static final DateTimeComparator dtComp = DateTimeComparator.getDateOnlyInstance();
	
	public CustomersProcessor() {
		mapper = new ObjectMapper();
	}
	public String sortCustomers(String direction, String body) throws JsonProcessingException, IOException {
		JsonNode json = mapper.readTree(body);
		ObjectReader reader = mapper.readerFor(new TypeReference<List<Customer>>() {});
		List<Customer> customers = reader.readValue(json);
		if(direction.toLowerCase().equals(DESC)) {
			customers.sort((a,b) -> dtComp.compare(b.getDuetime(), a.getDuetime()));
		} else {
			customers.sort((a,b) -> dtComp.compare(a.getDuetime(), b.getDuetime()));
		}
		JsonNode result = mapper.valueToTree(customers);
		return result.toString();
	}
}
