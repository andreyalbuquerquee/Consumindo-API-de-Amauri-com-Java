import java.net.URI;
import java.net.http.HttpRequest;


public class apiCommands {
    public static HttpRequest request(String url){
        HttpRequest request = HttpRequest.newBuilder().uri
        (URI.create(url)).build();
        return request;
    }

    public static HttpRequest postRequest(String url, String responseBody){
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(url))
                .header("Content-Type", "application/json")
                .POST(HttpRequest.BodyPublishers.ofString(responseBody))
                .build();
        return request;   
    }

    public static HttpRequest putRequest(String url, String requestBody) {
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(url))
                .header("Content-Type", "application/json")
                .PUT(HttpRequest.BodyPublishers.ofString(requestBody))
                .build();
        return request;
    }

    public static HttpRequest deleteRequest(String url) {
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(url))
                .header("Content-Type", "application/json")
                .DELETE()
                .build();
        return request;
    }
    
}
