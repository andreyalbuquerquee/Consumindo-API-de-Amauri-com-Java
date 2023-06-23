import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.DoubleSummaryStatistics;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import java.time.format.DateTimeFormatter;
import org.w3c.dom.Text;
import java. text.Format;
import java.lang.reflect.Type;
import com.google.gson.reflect.TypeToken;
import com.google.gson.Gson;

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
