package fr.ablx.daycare.serializable;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import fr.ablx.daycare.jpa.Daycare;

import java.io.IOException;

public class DayCareIdSerializer extends JsonSerializer<Daycare> {

    @Override
    public void serialize(Daycare daycare, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        if (daycare == null) {
            jsonGenerator.writeNull();
        } else {
            jsonGenerator.writeNumber(daycare.getId());
        }
    }
}
