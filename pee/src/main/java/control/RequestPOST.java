package control;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.URL;
import java.net.URLConnection;

import org.json.JSONException;
import org.json.JSONObject;

public class RequestPOST {
	private String endPoint;
	public RequestPOST(String url) {
		this.endPoint = url;
	}
	public JSONObject postJsonObject(JSONObject jsonObj)
			throws JSONException {
		StringBuilder jsonResponse = null;
		try {
			URL url = new URL(this.endPoint);
			URLConnection connection = url.openConnection();
			connection.setDoOutput(true);
			connection.setRequestProperty("Content-Type",
					"application/json");
			connection.setConnectTimeout(5000);
			connection.setReadTimeout(5000);
			OutputStreamWriter out = new
					OutputStreamWriter(connection.getOutputStream());
			out.write(jsonObj.toString());
			out.close();
			System.out.println("PUT json object enviado" +
					jsonObj.toString());
			//retorno
			jsonResponse = new StringBuilder();
			BufferedReader in = new
					BufferedReader( new	InputStreamReader(connection.getInputStream()));
			String retorno;
			while ((retorno = in.readLine()) != null) {
				jsonResponse.append(retorno);
			}
			System.out.println("PUT json resposta = " +
					jsonResponse);
			in.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		//converte retorno para json
		return (new JSONObject(jsonResponse.toString()));
	}
}