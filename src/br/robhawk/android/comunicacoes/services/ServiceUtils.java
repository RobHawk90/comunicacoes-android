package br.robhawk.android.comunicacoes.services;

import static br.robhawk.android.comunicacoes.json.JsonParser.convertToJson;

import java.io.IOException;
import java.io.UnsupportedEncodingException;

import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicHeader;

import android.util.Log;

public class ServiceUtils {

	private ServiceUtils() {
	}

	public static void setBasicHeaders(HttpUriRequest request) {
		request.addHeader(new BasicHeader("accept", "application/json"));
		request.addHeader(new BasicHeader("content-type", "application/json"));
	}

	public static <T> StringEntity buildEntityFrom(T object) throws UnsupportedEncodingException {
		String json = convertToJson(object);
		StringEntity entity = new StringEntity(json);

		return entity;
	}

	public static HttpResponse execute(HttpUriRequest request, String service) throws ClientProtocolException,
			IOException {
		DefaultHttpClient client = new DefaultHttpClient();
		HttpResponse response = client.execute(request);
		log(service, response);

		return response;
	}

	public static boolean isOk(HttpResponse response) {
		int statusCode = response.getStatusLine().getStatusCode();

		return statusCode == 200 || statusCode == 201;
	}

	private static void log(String service, HttpResponse response) {
		String[] names = service.split(":");
		String classe = names[1];
		String method = names[0];
		int statusCode = response.getStatusLine().getStatusCode();
		Log.i(classe, method + " - " + statusCode);
	}

}
