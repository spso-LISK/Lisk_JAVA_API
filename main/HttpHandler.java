package de.obermeier.lisk.main;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import org.json.JSONException;
import org.json.JSONObject;

public class HttpHandler {

	public JSONObject httpGET(URL url) {
		try {

			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			conn.setRequestMethod("GET");
			conn.setRequestProperty("Accept", "application/json");

			if (conn.getResponseCode() != HttpURLConnection.HTTP_OK) {
				throw new RuntimeException("Failed : HTTP error code : " + conn.getResponseCode());
			}

			BufferedReader br = new BufferedReader(new InputStreamReader((conn.getInputStream())));

			String output;
			JSONObject json = null;
			while ((output = br.readLine()) != null) {
				json = new JSONObject(output);
			}

			conn.disconnect();

			return json;

		} catch (Exception e) {

			e.printStackTrace();
		}
		return null;
	}

	public JSONObject httpPUT(URL url, JSONObject json) throws JSONException {

		try {

			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			conn.setDoOutput(true);
			conn.setRequestMethod("PUT");
			conn.setRequestProperty("Content-Type", "application/json");

			OutputStream os = conn.getOutputStream();
			os.write(json.toString().getBytes());
			os.flush();

			if (conn.getResponseCode() != HttpURLConnection.HTTP_OK) {
				throw new RuntimeException("Failed : HTTP error code : " + conn.getResponseCode());
			}

			BufferedReader br = new BufferedReader(new InputStreamReader((conn.getInputStream())));

			String output;
			JSONObject jsonOut = null;
			while ((output = br.readLine()) != null) {
				System.out.println(output);
				jsonOut = new JSONObject(output);
			}

			conn.disconnect();
			return jsonOut;

		} catch (MalformedURLException e) {

			e.printStackTrace();
			System.out.println(e.getLocalizedMessage());

		} catch (IOException e) {

			e.printStackTrace();
			System.out.println(e.getLocalizedMessage());

		}
		return null;
	}

	public JSONObject httpPOST(URL url) {
		try {

			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			conn.setDoOutput(true);
			conn.setRequestMethod("POST");
			conn.setRequestProperty("Accept", "application/json");

			if (conn.getResponseCode() != HttpURLConnection.HTTP_OK) {
				throw new RuntimeException("Failed : HTTP error code : " + conn.getResponseCode());
			}

			BufferedReader br = new BufferedReader(new InputStreamReader((conn.getInputStream())));

			String output;
			JSONObject json = null;
			while ((output = br.readLine()) != null) {
				json = new JSONObject(output);
			}

			conn.disconnect();

			return json;

		} catch (Exception e) {

			e.printStackTrace();
		}
		return null;
	}
}
