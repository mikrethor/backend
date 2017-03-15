package fr.ablx.daycare.serializable;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import fr.ablx.daycare.jpa.Educator;

import java.io.IOException;

public class EducatorSerializer extends JsonSerializer<Educator> {

    @Override
    public void serialize(Educator educator, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        if (educator == null) {
            jsonGenerator.writeNull();
        } else {


            jsonGenerator.writeStartObject();
            jsonGenerator.writeObjectField("id", educator.getId());


            jsonGenerator.writeObjectField("firstName", educator.getFirstName());

            jsonGenerator.writeObjectField("lastName", educator.getLastName());


            jsonGenerator.writeEndObject();
            
        }
    }
}
