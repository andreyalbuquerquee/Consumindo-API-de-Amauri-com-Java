import java.io.IOException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.List;
import java.lang.reflect.Type;
import com.google.gson.reflect.TypeToken;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class Categorie {
    private String id;
    private String name;
    
    String url = "https://my-contacts-amaurilima.vercel.app/categories";
    HttpClient client = HttpClient.newHttpClient();
    
    public String listCategories() {
        try {
            HttpResponse<String> response;
            response = client.send(apiCommands.request(url), HttpResponse.BodyHandlers.ofString());
            String responseBody = response.body();
            Gson gson = new Gson();
            List<Categorie> categorieList = new ArrayList<>();
            Type categorieListType = new TypeToken<ArrayList<Categorie>>() {}.getType();
            categorieList = gson.fromJson(responseBody, categorieListType);
            for (Categorie categorie : categorieList) {
                System.out.println(categorie);
            }
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
        return "";
    }

    public Categorie findCategorie(String id) {
        Categorie categorie = null;
        try {
            String idUrl = url + "/" + id;
            HttpResponse<String> response;
            response = client.send(apiCommands.request(idUrl), HttpResponse.BodyHandlers.ofString());
            String responseBody = response.body();
            Gson gson = new Gson();
            categorie = gson.fromJson(responseBody, Categorie.class);
            if (categorie.getId() == null) {
                System.out.println("Id de categoria não existe!\n");
                categorie = null;} 
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
        return categorie;
    }

    public void createCategorie(String name) {
        Categorie categorie = new Categorie();
        categorie.setName(name);

        Gson gson = new GsonBuilder()
        .registerTypeAdapter(Categorie.class, new CategoryAdapter())
        .create();
        
        String jsonCategory= gson.toJson(categorie);

        try {
            HttpRequest request = apiCommands.postRequest(url, jsonCategory);
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
            String responseBody = response.body();
            int statusCode = response.statusCode();

            if (statusCode == 201) {
                Categorie createdCategorie = gson.fromJson(responseBody, Categorie.class);
                System.out.println("Categoria criada com sucesso:");
                System.out.println(createdCategorie);
            } else {
                System.out.println("Falha ao criar a categoria. Código de resposta: " + statusCode);
                System.out.println("Mensagem de erro: " + responseBody);
            }

        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void updateCategorie(String id, Categorie updateCategoryName) {
        String idUrl = url + "/" + id;
        
        Gson gson = new GsonBuilder()
        .registerTypeAdapter(Categorie.class, new CategoryAdapter())
        .create();

        String updateInfosJson = gson.toJson(updateCategoryName);
        System.out.println(updateInfosJson);

        try {
            HttpResponse<String> response = client.send(apiCommands.putRequest(idUrl, updateInfosJson),
            HttpResponse.BodyHandlers.ofString());
            String responseBody = response.body();
            int statusCode = response.statusCode();
            
            if (statusCode == 200) {
                Categorie updatedCategory = gson.fromJson(responseBody, Categorie.class);
                System.out.println("Categoria atualizada com sucesso:");
                System.out.println(updatedCategory);
            } else {
                System.out.println("Falha ao atualizar a categoria. Código de resposta: " + statusCode);
                System.out.println("Mensagem de erro: " + responseBody);
            }
            
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void deleteCategorie(String id) {
        String idUrl = url + "/" + id;
        
        try {
            HttpResponse<String> response = client.send(apiCommands.deleteRequest(idUrl),
            HttpResponse.BodyHandlers.ofString());
            int statusCode = response.statusCode();
            
            if (statusCode == 204) {
                System.out.println("Categoria excluída com sucesso.");
            } else {
                System.out.println("Falha ao excluir a categoria. Código de resposta: " + statusCode);
            }
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }

    public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    @Override
    public String toString() {
        return String.format("id: %s\nname: %s\n", id, name);
    }


}
