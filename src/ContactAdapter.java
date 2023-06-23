import com.google.gson.TypeAdapter;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;

import java.io.IOException;

public class ContactAdapter extends TypeAdapter<Contact> {

    @Override
    public void write(JsonWriter out, Contact contact) throws IOException {
        out.beginObject();
        out.name("name").value(contact.getName());
        out.name("email").value(contact.getEmail());
        out.name("phone").value(contact.getPhone());
        out.name("category_id").value(contact.getCategory_id());
        out.endObject();
    }

    @Override
    public Contact read(JsonReader in) throws IOException {
        // Implementar se necessário (leitura de JSON para objeto Contact)
        return null;
    }
}
