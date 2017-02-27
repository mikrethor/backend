package fr.ablx.daycare.deserializable;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.ObjectCodec;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonNode;
import fr.ablx.daycare.jpa.DayCareRepository;
import fr.ablx.daycare.jpa.Educator;
import fr.ablx.daycare.jpa.Parent;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.IOException;

/**
 * Created by Thor on 2016-11-17.
 */
public class EducatorDeserializer extends JsonDeserializer<Educator> {
    @Autowired
    DayCareRepository dayCareRepo;

    @Override
    public Educator deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException, JsonProcessingException {
        ObjectCodec oc = jsonParser.getCodec();
        JsonNode node = oc.readTree(jsonParser);

        Educator educator = new Educator(node.get("firstName").asText(), node.get("lastName").asText());
        JsonNode daycareNode = node.get("daycare");
        if (null != daycareNode) {
            Long daycareId = daycareNode.asLong();
            educator.setDaycare(dayCareRepo.findOne(daycareId));
        } else {

        }

        return educator;

    }
}
