package com.wamcstudios.moviesapp.core.utils

/*class LocalDateSerializer : KSerializer<LocalDate?> {
    override val descriptor: SerialDescriptor = PrimitiveSerialDescriptor(
        serialName = "LocalDate",
        kind = PrimitiveKind.STRING
    )

    override fun serialize(encoder: Encoder, value: LocalDate?) =
        encoder.encodeString(value.toString())
1
    override fun deserialize(decoder: Decoder): LocalDate? = try {
        val string = decoder.decodeString()
        if (string.isNotEmpty()) {
            string.toLocalDate()
        } else {
            null
        }
    } catch (exception: ParseException) {
        throw SerializationException(exception)
    }
}*/
