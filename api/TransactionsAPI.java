package de.obermeier.lisk.api;

import java.net.MalformedURLException;
import java.net.URL;

import org.json.JSONException;
import org.json.JSONObject;

import de.obermeier.lisk.main.HttpHandler;

public class TransactionsAPI {

	HttpHandler httpHandler = new HttpHandler();
	final String TRANSACTION_API = "/api/transactions";

	/**
	 * <b>Send transaction to broadcast network.</b> </br>
	 * <i>PUT /api/transactions</i>
	 *
	 * @param url
	 *            - <i>api URL to call</i>
	 * 
	 * @param secret
	 *            - <i>Secret key of account</i>
	 * @param amount
	 *            - <i>Amount of transaction </i>
	 *            <code>(e.g. 1 for 1 LSK)</code>
	 * @param recipientId
	 *            -<i>Recipient of transaction. Address or username.</i>
	 * @return JSONObject of response {"success":true,"transactionId":"id of
	 *         added transaction"}
	 * @throws JSONException
	 * @throws MalformedURLException
	 */
	public JSONObject makeTransaction(URL url, String secret, int amount, String recipientId)
			throws JSONException, MalformedURLException {
		return makeTransaction(url, secret, amount, recipientId, null, null);
	}

	/**
	 * <b>Send transaction to broadcast network.</b> </br>
	 * <i>PUT /api/transactions</i>
	 *
	 * @param url
	 *            - <i>api URL to call</i>
	 * 
	 * @param secret
	 *            - <i>Secret key of account</i>
	 * @param amount
	 *            - <i>Amount of transaction </i>
	 *            <code>(e.g. 1 for 1 LSK)</code>
	 * @param recipientId
	 *            -<i>Recipient of transaction. Address or username.</i>
	 * @param publicKey
	 *            - <i>Public key of sender account, to verify secret passphrase
	 *            in wallet.</i>
	 * @return JSONObject of response {"success":true,"transactionId":"id of
	 *         added transaction"}
	 * @throws JSONException
	 * @throws MalformedURLException
	 */
	public JSONObject makeTransaction(URL url, String secret, int amount, String recipientId, String publicKey)
			throws JSONException, MalformedURLException {
		return makeTransaction(url, secret, amount, recipientId, publicKey, null);
	}

	/**
	 * <b>Send transaction to broadcast network.</b> </br>
	 * <i>PUT /api/transactions</i>
	 *
	 * @param url
	 *            - <i>api URL to call</i>
	 * 
	 * @param secrets
	 *            - <i>Both secret keys of account as <code>StringArray</code>
	 *            (only if user uses second signature)</i>
	 * @param amount
	 *            - <i>Amount of transaction </i>
	 *            <code>(e.g. 1 for 1 LSK)</code>
	 * @param recipientId
	 * @return JSONObject of response {"success":true,"transactionId":"id of
	 *         added transaction"}
	 * @throws Exception
	 */
	public JSONObject makeTransaction(URL url, String[] secrets, int amount, String recipientId) throws Exception {
		if (secrets.length > 2) {
			throw new Exception("Error - please provide exactly two passphrases!");
		}
		return makeTransaction(url, secrets[0], amount, recipientId, null, secrets[1]);
	}

	/**
	 * <b>Send transaction to broadcast network.</b> </br>
	 * <i>PUT /api/transactions</i>
	 *
	 * @param url
	 *            - <i>URL of node to call api from</i>
	 * 
	 * @param secret
	 *            - <i>Secret key of account</i>
	 * @param amount
	 *            - <i>Amount of transaction </i>
	 *            <code>(e.g. 1 for 1 LSK)</code>
	 * @param recipientId
	 *            -<i>Recipient of transaction. Address or username.</i>
	 * @param publicKey
	 *            - <i>Public key of sender account, to verify secret passphrase
	 *            in wallet.</i>
	 * @param secondSecret
	 *            - <i>Secret key from second transaction, required if user uses
	 *            second signature</i>
	 * @return JSONObject of response {"success":true,"transactionId":"id of
	 *         added transaction"}
	 * @throws JSONException
	 * @throws MalformedURLException
	 */
	public JSONObject makeTransaction(URL url, String secret, int amount, String recipientId, String publicKey,
			String secondSecret) throws JSONException, MalformedURLException {

		url = new URL(url + TRANSACTION_API);
		/* amount has to be * 10^8 for lisk transaction format */
		amount = (int) (amount * Math.pow(10, 8));

		JSONObject json = new JSONObject();
		json.put("secret", secret);
		json.put("amount", amount);
		json.put("recipientId", recipientId);
		if (publicKey != null) {
			json.put("publicKey", publicKey);
		}
		if (secondSecret != null) {
			json.put("secondSecret", secondSecret);
		}

		return httpHandler.httpPUT(url, json);
	}
}
