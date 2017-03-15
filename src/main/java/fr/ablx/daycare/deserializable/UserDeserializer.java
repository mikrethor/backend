package fr.ablx.daycare.deserializable;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.ObjectCodec;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonNode;
import fr.ablx.daycare.jpa.Admin;
import fr.ablx.daycare.jpa.Daycare;
import fr.ablx.daycare.jpa.User;

import java.io.IOException;

public class UserDeserializer extends JsonDeserializer<User> {

    @Override
    public User deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException {
        ObjectCodec oc = jsonParser.getCodec();
        JsonNode node = oc.readTree(jsonParser);


        User user = new User();
        user.setLogin(node.get("login").asText());
        JsonNode daycareNode = node.get("daycareId");
        if (null != daycareNode) {
            Long daycareId = daycareNode.asLong();
            Daycare daycare = new Daycare();
            daycare.setId(daycareId);
            user.setDaycare(daycare);
        }

        JsonNode adminNode = node.get("admin");
        if (null != adminNode) {
            Long adminId = adminNode.asLong();
            Admin admin = new Admin();
            admin.setId(adminId);
            user.setAdmin(admin);
        }

        return user;
    }
}
