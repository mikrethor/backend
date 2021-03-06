package fr.ablx.daycare.deserializable;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.ObjectCodec;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonNode;
import fr.ablx.daycare.jpa.Child;
import fr.ablx.daycare.jpa.DayCareRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.IOException;
import java.util.ArrayList;

public class ChildDeserializer extends JsonDeserializer<Child> {
    @Autowired
    private DayCareRepository dayCareRepo;

    @Override
    public Child deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException {
        ObjectCodec oc = jsonParser.getCodec();
        JsonNode node = oc.readTree(jsonParser);

        Child child = new Child(node.get("firstName").asText(), node.get("lastName").asText());
        JsonNode daycareNode = node.get("daycare");
        if (null != daycareNode) {
            Long daycareId = daycareNode.asLong();
            child.setDaycare(dayCareRepo.findOne(daycareId));
        }
        child.setDaySumups(new ArrayList<>());
        child.setParents(new ArrayList<>());
        return child;
    }
}
