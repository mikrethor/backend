package fr.ablx.daycare.serializable;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import fr.ablx.daycare.jpa.Admin;

import java.io.IOException;

public class AdminSerializer extends JsonSerializer<Admin> {

    @Override
    public void serialize(Admin admin, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        if (admin == null) {
            jsonGenerator.writeNull();
        } else {
            jsonGenerator.writeNumber(admin.getId());
        }
    }
}
