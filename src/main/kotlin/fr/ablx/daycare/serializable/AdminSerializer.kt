package fr.ablx.daycare.serializable

import com.fasterxml.jackson.core.JsonGenerator
import com.fasterxml.jackson.databind.JsonSerializer
import com.fasterxml.jackson.databind.SerializerProvider
import fr.ablx.daycare.jpa.Admin
import java.io.IOException

class AdminSerializer : JsonSerializer<Admin>() {

    @Throws(IOException::class)
    override fun serialize(admin: Admin?, jsonGenerator: JsonGenerator, serializerProvider: SerializerProvider) {
        if (admin == null) {
            jsonGenerator.writeNull()
        } else {
            jsonGenerator.writeNumber(admin.id)
        }
    }
}
