package de.obermeier.lisk.api;

import java.net.MalformedURLException;
import java.net.URL;

import org.json.JSONObject;

import de.obermeier.lisk.main.HttpHandler;

public class AccountsAPI {

	HttpHandler httpHandler = new HttpHandler();
	final String GET_BALANCE_API = "/api/accounts/getBalance?";

	/**
	 * Get Account Balance Request the balance of an account.
	 * 
	 * GET /api/accounts/getBalance?address=<address>
	 * 
	 * Response { "success": true, "balance": "Balance of account",
	 * "unconfirmedBalance": "Unconfirmed balance of account" }
	 * 
	 * @throws MalformedURLException
	 */

	public JSONObject getBalanceByAddress(URL url, String address) throws MalformedURLException {
		String suffix = "address=" + address;
		url = new URL(url + GET_BALANCE_API + suffix);
		return httpHandler.httpGET(url);
	}
}
