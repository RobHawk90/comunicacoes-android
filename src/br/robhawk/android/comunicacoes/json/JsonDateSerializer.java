package br.robhawk.android.comunicacoes.json;

import java.io.IOException;
import java.util.Date;

import br.robhawk.android.comunicacoes.util.DateFormater;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

public class JsonDateSerializer extends JsonSerializer<Date> {

	@Override
	public void serialize(Date date, JsonGenerator generator, SerializerProvider provider) throws IOException,
			JsonProcessingException {

		String dataString = DateFormater.servidor(date);

		generator.writeString(dataString);
	}
}
