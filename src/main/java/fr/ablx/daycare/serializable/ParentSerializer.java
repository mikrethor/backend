package fr.ablx.daycare.serializable;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import fr.ablx.daycare.jpa.Parent;

import java.io.IOException;

public class ParentSerializer extends JsonSerializer<Parent> {

    @Override
    public void serialize(Parent parent, JsonGenerator jsonGenerator, SerializerProvider serializerProvider)
            throws IOException {
        if (parent == null) {
            jsonGenerator.writeNull();
        } else {

            jsonGenerator.writeStartObject();
            jsonGenerator.writeObjectField("id", parent.getId());

            jsonGenerator.writeObjectField("firstName", parent.getFirstName());

            jsonGenerator.writeObjectField("lastName", parent.getLastName());

            jsonGenerator.writeEndObject();
        }
    }
}
