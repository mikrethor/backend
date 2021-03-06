package fr.ablx.daycare.deserializable;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.ObjectCodec;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonNode;
import fr.ablx.daycare.jpa.DayCareRepository;
import fr.ablx.daycare.jpa.Parent;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.IOException;

public class ParentDeserializer extends JsonDeserializer<Parent> {
    @Autowired
    private DayCareRepository dayCareRepo;

    @Override
    public Parent deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException {
        ObjectCodec oc = jsonParser.getCodec();
        JsonNode node = oc.readTree(jsonParser);

        Parent parent = new Parent(node.get("firstName").asText(), node.get("lastName").asText());
        JsonNode daycareNode = node.get("daycare");
        if (null != daycareNode) {
            Long daycareId = daycareNode.asLong();
            parent.setDaycare(dayCareRepo.findOne(daycareId));
        }

        return parent;
    }
}
