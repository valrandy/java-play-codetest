package controllers;

import com.google.inject.Inject;

import akka.actor.ActorRef;
import akka.actor.ActorSystem;
import akka.routing.FromConfig;
import play.mvc.*;
import play.api.mvc.MultipartFormData;
import play.mvc.Http.MultipartFormData.FilePart;
import protocol.RequestParams;
import scala.compat.java8.FutureConverters;
import workers.CustomersWorker;
import protocol.Message.SortCustomersRequest;
import protocol.Message.SortCustomersResponse;
import static akka.pattern.Patterns.ask;
import static play.mvc.Http.MimeTypes.JSON;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CompletionStage;
import java.io.File;
import java.io.IOException;
import org.apache.commons.io.FileUtils;
import static protocol.Constants.ASC;
/**
 * This controller contains an action to handle HTTP requests
 * to the application's home page.
 */
public class HomeController extends Controller {
	final ActorRef actorRef;
	
	@Inject
	public HomeController(ActorSystem system) {
		actorRef = system.actorOf(FromConfig.getInstance().props(CustomersWorker.props), "customersRouter");
	}
    /**
     * An action that renders an HTML page with a welcome message.
     * The configuration in the <code>routes</code> file means that
     * this method will be called when the application receives a
     * <code>GET</code> request with a path of <code>/</code>.
     */
    public Result index() {
        return ok(views.html.index.render());
    }

    public CompletionStage<Result> fileUpload() throws IOException {
    	final Http.MultipartFormData<File> formData = request().body().asMultipartFormData();
        final Http.MultipartFormData.FilePart<File> filePart = formData.getFile("file");
        final File file = filePart.getFile();
        String contents = FileUtils.readFileToString(file);
		RequestParams requestParams = new RequestParams();
		requestParams.setDirection("");
		requestParams.setRequestBody(contents);
		return FutureConverters.toJava(ask(actorRef, new SortCustomersRequest(requestParams), 5000))
                .thenApply(response -> {
            		if(response instanceof SortCustomersResponse) {
            			SortCustomersResponse result = (SortCustomersResponse) response;
            			if(result.status == OK) {
            				return ok(result.responseBody).as(JSON);
            			}
            		}
            		return ok();
                });
    }
    
    public CompletionStage<Result> sortCustomers() {
		String body = request().body().asJson().toString();
		String direction = request().getQueryString("direction");
		if(direction == null || direction == "") {
			direction = ASC;
		}
		RequestParams requestParams = new RequestParams();
		requestParams.setDirection(direction);
		requestParams.setRequestBody(body);
		return FutureConverters.toJava(ask(actorRef, new SortCustomersRequest(requestParams), 5000))
                .thenApply(response -> {
            		if(response instanceof SortCustomersResponse) {
            			SortCustomersResponse result = (SortCustomersResponse) response;
            			if(result.status == OK) {
            				return ok(result.responseBody).as(JSON);
            			}
            		}
            		return ok();
                });
	}
}
