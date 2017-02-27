package fr.ablx.daycare.serializable;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import fr.ablx.daycare.jpa.Parent;

import java.io.IOException;
import java.util.List;

/**
 * Created by Thor on 2016-11-17.
 */
public class ParentsSerializer extends JsonSerializer<List<Parent>> {

    @Override
    public void serialize(List<Parent> parents, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        if (parents == null) {
            jsonGenerator.writeNull();
        } else {

            jsonGenerator.writeStartArray();
             for (Parent parent : parents) {
                 jsonGenerator.writeStartObject();
                 jsonGenerator.writeObjectField("parent", parent.getId());
                 jsonGenerator.writeEndObject();
            }
            jsonGenerator.writeEndArray();

        }
    }
}
