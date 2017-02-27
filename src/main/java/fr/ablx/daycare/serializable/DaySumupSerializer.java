package fr.ablx.daycare.serializable;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import fr.ablx.daycare.jpa.DaySumup;

import java.io.IOException;

/**
 * Created by Thor on 2016-11-17.
 */
public class DaySumupSerializer extends JsonSerializer<DaySumup> {

    @Override
    public void serialize(DaySumup sumup, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        if (sumup == null) {
            jsonGenerator.writeNull();
        } else {
            jsonGenerator.writeNumber(sumup.getId());
        }
    }
}
