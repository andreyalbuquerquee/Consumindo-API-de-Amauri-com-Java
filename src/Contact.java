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

public class Contact {
    private String id;
    private String name;
    private String email;
    private String phone;
    private String category_id;
    private String category_name;

    String url = "https://my-contacts-amaurilima.vercel.app/contacts";
    HttpClient client = HttpClient.newHttpClient();
    
    public String listContacts() {
        try {
            HttpResponse<String> response;
            response = client.send(apiCommands.request(url), HttpResponse.BodyHandlers.ofString());
            String responseBody = response.body();
            Gson gson = new Gson();
            List<Contact> contactList = new ArrayList<>();
            Type contactListType = new TypeToken<ArrayList<Contact>>() {}.getType();
            contactList = gson.fromJson(responseBody, contactListType);
            for (Contact contact : contactList) {
                System.out.println(contact);
            }
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
        return "";
    }

    public Contact findContact(String id) {
        Contact contact = null;
        try {
            String idUrl = url + "/" + id;
            HttpResponse<String> response;
            response = client.send(apiCommands.request(idUrl), HttpResponse.BodyHandlers.ofString());
            String responseBody = response.body();
            Gson gson = new Gson();
            contact = gson.fromJson(responseBody, Contact.class);
            if (contact.getId() == null) {
                System.out.println("Usuário não encontrado!\n");
            } else {
                System.out.println(contact);
            }
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
        return contact;
    }

    public void createContact(String name, String email, String phone) {
        Contact contact = new Contact();
        contact.setName(name);
        contact.setEmail(email);
        contact.setPhone(phone);

        Gson gson = new GsonBuilder()
        .registerTypeAdapter(Contact.class, new ContactAdapter())
        .create();
        
        String jsonContact = gson.toJson(contact);

        try {
            HttpRequest request = apiCommands.postRequest(url, jsonContact);
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
            String responseBody = response.body();
            int statusCode = response.statusCode();

            if (statusCode == 201) {
                Contact createdContact = gson.fromJson(responseBody, Contact.class);
                System.out.println("Contato criado com sucesso:");
                System.out.println(createdContact);
            } else {
                System.out.println("Falha ao criar o contato. Código de resposta: " + statusCode);
                System.out.println("Mensagem de erro: " + responseBody);
            }

        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void updateContact(String id, Contact updateInfos) {
        String idUrl = url + "/" + id;
        
        Gson gson = new GsonBuilder()
        .registerTypeAdapter(Contact.class, new ContactAdapter())
        .create();

        String updateInfosJson = gson.toJson(updateInfos);
        System.out.println(updateInfosJson);

        try {
            HttpResponse<String> response = client.send(apiCommands.putRequest(idUrl, updateInfosJson),
            HttpResponse.BodyHandlers.ofString());
            String responseBody = response.body();
            int statusCode = response.statusCode();
            
            if (statusCode == 200) {
                Contact updatedContact = gson.fromJson(responseBody, Contact.class);
                System.out.println("Contato atualizado com sucesso:");
                System.out.println(updatedContact);
            } else {
                System.out.println("Falha ao atualizar o contato. Código de resposta: " + statusCode);
                System.out.println("Mensagem de erro: " + responseBody);
            }
            
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }

    }

    public void deleteContact(String id) {
        String idUrl = url + "/" + id;
        
        try {
            HttpResponse<String> response = client.send(apiCommands.deleteRequest(idUrl),
            HttpResponse.BodyHandlers.ofString());
            int statusCode = response.statusCode();
            
            if (statusCode == 204) {
                System.out.println("Contato excluído com sucesso.");
            } else {
                System.out.println("Falha ao excluir o contato. Código de resposta: " + statusCode);
            }
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }
    

    public static Contact fromJson(String jsonString) {
        Gson gson = new Gson();
        return gson.fromJson(jsonString, Contact.class);
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getCategory_id() {
        return category_id;
    }

    public void setCategory_id(String category_id) {
        this.category_id = category_id;
    }

    public String getCategory_name() {
        return category_name;
    }

    public void setCategory_name(String category_name) {
        this.category_name = category_name;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    @Override
    public String toString() {
            return String.format("\n\nId: %s\nNome: %s\nNúmero: %s\nId da categoria: %s\nNome da categoria: %s\n\n",id, name, phone, category_id, category_name);
    }

}
