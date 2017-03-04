package fr.ablx.daycare.serializable;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import fr.ablx.daycare.jpa.Child;

import java.io.IOException;

public class ChildSerializer extends JsonSerializer<Child> {

    @Override
    public void serialize(Child child, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        if (child == null) {
            jsonGenerator.writeNull();
        } else {
            jsonGenerator.writeNumber(child.getId());
        }
    }
}
