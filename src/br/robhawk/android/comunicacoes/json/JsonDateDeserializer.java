package br.robhawk.android.comunicacoes.json;

import java.io.IOException;
import java.util.Date;

import br.robhawk.android.comunicacoes.util.DateFormater;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;

public class JsonDateDeserializer extends JsonDeserializer<Date> {

	@Override
	public Date deserialize(JsonParser parser, DeserializationContext context) throws IOException,
			JsonProcessingException {

		String dataString = parser.getText();
		Date data = null;

		if (dataString.length() > 8) // Eh somente data
			data = DateFormater.yyyyMMdd(dataString);
		else
			data = DateFormater.HHmmss(dataString); // Eh somente hora

		return data;
	}
}
