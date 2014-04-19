package br.robhawk.android.comunicacoes.json;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;
import com.fasterxml.jackson.databind.type.CollectionType;
import com.fasterxml.jackson.databind.type.TypeFactory;

public class JsonParser {

	private JsonParser() {
	}

	private static ObjectMapper mapper;

	static {
		SimpleModule module = new SimpleModule();
		module.addDeserializer(Date.class, new JsonDateDeserializer());
		module.addSerializer(Date.class, new JsonDateSerializer());

		mapper = new ObjectMapper();
		mapper.registerModule(module);
	}

	/**
	 * Converte um objeto em comum para um formato que possa ser trafegado via
	 * HTTP.
	 * 
	 * @param object
	 *            - bean comum entre cliente/servidor.
	 * @return String JSON.
	 */
	public static <T> String convertToJson(T object) {
		String json = "";

		try {
			json = mapper.writeValueAsString(object);
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}

		return json;
	}

	/**
	 * Converte JSON para um tipo específico comum entre cliente/servidor
	 * 
	 * @param type
	 *            - tipo do objeto
	 * @param json
	 * @return objeto
	 */
	public static <T> T convertToType(Class<T> type, String json) {
		T objeto = null;

		try {
			objeto = mapper.readValue(json, type);
		} catch (JsonParseException e) {
			e.printStackTrace();
		} catch (JsonMappingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		return objeto;
	}

	/**
	 * Converte JSON para uma lista especifica comum entre cliente/servidor
	 * 
	 * @param type
	 *            - tipo dos itens da lista
	 * @param json
	 * @return objeto
	 */
	public static <T> ArrayList<T> convertToList(Class<T> type, String json) {
		ArrayList<T> list = null;

		try {
			TypeFactory factory = TypeFactory.defaultInstance();
			CollectionType collectionType = factory.constructCollectionType(ArrayList.class, type);
			list = mapper.readValue(json, collectionType);
		} catch (JsonParseException e) {
			e.printStackTrace();
		} catch (JsonMappingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		return list;
	}
}
