package fr.ablx.daycare.serializable;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import fr.ablx.daycare.jpa.User;

import java.io.IOException;

public class UserSerializer extends JsonSerializer<User> {

    @Override
    public void serialize(User user, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        if (user == null) {
            jsonGenerator.writeNull();
        } else {

            jsonGenerator.writeStartObject();
            jsonGenerator.writeObjectField("id", user.getId());



            jsonGenerator.writeObjectField("login", user.getLogin());

            jsonGenerator.writeObjectField("daycareId", user.getDaycare().getId());


            jsonGenerator.writeObjectField("educator", user.getEducator());
            jsonGenerator.writeObjectField("parent", user.getParent());
            jsonGenerator.writeObjectField("admin", user.getAdmin());


            jsonGenerator.writeEndObject();


        }
    }
}
