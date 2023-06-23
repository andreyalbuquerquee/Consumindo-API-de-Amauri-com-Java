import com.google.gson.TypeAdapter;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;

import java.io.IOException;

public class CategoryAdapter extends TypeAdapter<Categorie> {

    @Override
    public void write(JsonWriter out, Categorie categorie) throws IOException {
        out.beginObject();
        out.name("name").value(categorie.getName());
        out.endObject();
    }

    @Override
    public Categorie read(JsonReader in) throws IOException {
        // Implementar se necessário (leitura de JSON para objeto Categorie)
        return null;
    }
}
