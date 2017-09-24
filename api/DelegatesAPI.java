package de.obermeier.lisk.api;

import java.net.MalformedURLException;
import java.net.URL;

import org.json.JSONException;
import org.json.JSONObject;

import de.obermeier.lisk.main.HttpHandler;

public class DelegatesAPI {

	HttpHandler httpHandler = new HttpHandler();
	final String GENERAL_DELEGATE_API = "/api/delegates";
	final String GET_DELEGATE_API = GENERAL_DELEGATE_API + "/get?";
	final String GET_VOTERS_API = GENERAL_DELEGATE_API + "/voters?";
	final String GET_DELEGATES_API = GENERAL_DELEGATE_API + "?";
	final String SEARCH_DELEGATE_API = GENERAL_DELEGATE_API + "/search?";
	final String COUNT_DELEGATES_API = GENERAL_DELEGATE_API + "/count";
	// MOVE TO ACCOUNTS (lisk api docs: Category Delegates...)!!!
	// final String GET_VOTES_API = GET /api/accounts/delegates/?address=address
	final String ENABLE_FORGING_API = GENERAL_DELEGATE_API + "/forging/enable";
	final String DISABLE_FORGING_API = GENERAL_DELEGATE_API + "/forging/disable";
	final String GET_FORGED_API = GENERAL_DELEGATE_API + "/forging/getForgedByAccount?";
	final String NEXT_FORGERS_API = GENERAL_DELEGATE_API + "/getNextForgers?";

	/**
	 * <b>Gets delegate by public key</b> </br>
	 * <i>GET /api/delegates/get?publicKey=publicKey</i> </br>
	 * 
	 * @param url
	 *            - <i>URL of node to call api from</i> </br>
	 * @param publicKey
	 *            - <i>Public key of delegate account</i> (<code>String</code>)
	 *            </br>
	 *            </br>
	 * @return <code>JSONObject of response</code></br>
	 *         { "success": true, </br>
	 *         "delegate":</br>
	 *         { "username": "Username. <i>String</i>", </br>
	 *         "address": "Address. <i>String</i>", </br>
	 *         "publicKey": "Public key. <i>String</i>", </br>
	 *         "vote": "Total votes. <i>Integer</i>", </br>
	 *         "producedblocks": "Produced blocks. <i>Integer</i>", </br>
	 *         "missedblocks": "Missed blocks. <i>Integer</i>", </br>
	 *         "rate": "Ranking. <i>Integer</i>", </br>
	 *         "approval": "Approval percentage. <i>Float</i>", </br>
	 *         "productivity": "Productivity percentage. <i>Float</i>" } }
	 */

	public JSONObject getDelegateByPublicKey(URL url, String publicKey) throws MalformedURLException {
		String suffix = "publicKey=" + publicKey;
		url = new URL(url + GET_DELEGATE_API + suffix);
		return httpHandler.httpGET(url);
	}

	/**
	 * <b>Gets delegate by username</b> </br>
	 * <i>GET /api/delegates/get?username=username</i></br>
	 * <code>username</code>: Username of delegate account (<code>String</code>)
	 * </br>
	 * </br>
	 * <b>Response</b> </br>
	 * { "success": true, </br>
	 * "delegate":</br>
	 * { "username": "Username. <i>String</i>", </br>
	 * "address": "Address. <i>String</i>", </br>
	 * "publicKey": "Public key. <i>String</i>", </br>
	 * "vote": "Total votes. <i>Integer</i>", </br>
	 * "producedblocks": "Produced blocks. <i>Integer</i>", </br>
	 * "missedblocks": "Missed blocks. <i>Integer</i>", </br>
	 * "rate": "Ranking. <i>Integer</i>", </br>
	 * "approval": "Approval percentage. <i>Float</i>", </br>
	 * "productivity": "Productivity percentage. <i>Float</i>" } }
	 */
	public JSONObject getDelegateByUsername(URL url, String username) throws MalformedURLException {
		String suffix = "username=" + username;
		url = new URL(url + GET_DELEGATE_API + suffix);
		return httpHandler.httpGET(url);
	}

	/**
	 * <b>Get voters of delegate.</b> <br>
	 * <i>GET /api/delegates/voters?publicKey=publicKey</i> <br>
	 * <code>publicKey</code>: Public key of registered delegate
	 * account.(<code>String</code>) </br>
	 * </br>
	 * <b>Response</b> <br>
	 * { "success": true,<br>
	 * "accounts": <br>
	 * [ { username: "Voter username. <code>String</code>", <br>
	 * address: "Voter address. <code>String</code>", <br>
	 * publicKey: "Voter public key. <code>String</code>",<br>
	 * balance: "Voter balance. <code>String</code>" } ] }
	 * 
	 * @param url
	 * @param publicKey
	 * @return
	 * @throws MalformedURLException
	 */
	public JSONObject getVotersByPublicKey(URL url, String publicKey) throws MalformedURLException {
		String suffix = "publicKey=" + publicKey;
		url = new URL(url + GET_VOTERS_API + suffix);
		return httpHandler.httpGET(url);
	}

	/**
	 * <b>Enable Delegate on Account</b></br>
	 * <i>PUT /api/delegates</i> </br>
	 * </br>
	 * 
	 * @param url
	 *            - <i>URL of node to call api from</i>
	 * 
	 * @param secret
	 *            - <i>Secret key of account</i>
	 * @param secondSecret
	 *            - <i>Second secret of account</i>
	 * @param username
	 *            - <i>Username of delegate. String from 1 to 20 characters</i>
	 * @return <code>JSONObject of response</code></br>
	 *         { "success":true, </br>
	 *         "transaction":</br>
	 *         { "type": "Type of transaction. Integer", </br>
	 *         "amount": "Amount. Integer", </br>
	 *         "senderPublicKey": "Sender public key. String", </br>
	 *         "requesterPublicKey": "Requester public key. String", </br>
	 *         "timestamp": "Time. Integer", </br>
	 *         "asset":{ </br>
	 *         "delegate":</br>
	 *         { "username": "Delegate username. String" } </br>
	 *         }, </br>
	 *         "recipientId": "Recipient address. String", </br>
	 *         "signature": "Signature. String", </br>
	 *         "signSignature": "Sign signature. String", </br>
	 *         "id": "Tx ID. String", </br>
	 *         "fee": "Fee. Integer", </br>
	 *         "senderId": "Sender address. String", </br>
	 *         "relays": "Propagation. Integer", </br>
	 *         "receivedAt": "Time. String" } }
	 * @throws MalformedURLException
	 * @throws JSONException
	 * 
	 */
	// TODO: TEST!
	public JSONObject enableDelegateOnAccount(URL url, String secret, String secondSecret, String username)
			throws JSONException, MalformedURLException {
		url = new URL(url + GENERAL_DELEGATE_API);

		JSONObject json = new JSONObject();
		json.put("secret", secret);
		json.put("secondSecret", secondSecret);
		if (username.length() <= 20) {
			json.put("username", username);
		} else {
			throw new JSONException("Error: Username must be between 1 and 20 characters!");
		}

		return httpHandler.httpPUT(url, json);
	}

	/**
	 * Get Delegates List Gets list of delegates by provided filter.
	 * 
	 * GET /api/delegates?limit=limit&offset=offset&orderBy=orderBy
	 * 
	 * limit: Limit to show. Integer. Maximum is 100. (Integer) offset: Offset
	 * (Integer) orderBy: Order by field (String) Response { "success": true,
	 * "delegates": "delegates objects array" }
	 */
	public JSONObject getDelegatesList(URL url, int limit, int offset, String orderBy) {
		return null;
	}

	/**
	 * ======================================================================================================
	 * Search for Delegates Search for Delegates by "fuzzy" username.
	 * 
	 * GET /api/delegates/search?q=username&orderBy=producedblocks:desc
	 * 
	 * q: Search criteria. (String) orderBy: Order results by ascending or
	 * descending property. Valid sort fields are: username:asc, username:desc,
	 * address:asc, address:desc, publicKey:asc, publicKey:desc, vote:asc,
	 * vote:desc, missedblocks:asc, missedblocks:desc, producedblocks:asc,
	 * producedblocks:desc Response { "success": true, "delegates": [ "array of
	 * delegates" ] } Example curl -k -X GET
	 * http://localhost:8000/api/delegates/search?q=username&orderBy=producedblocks:desc
	 * ======================================================================================================
	 */
	public JSONObject getDelegateByFuzzyUsername(URL url) {
		return null;
	}

	/**
	 * Get Delegates Count Get total count of registered delegates.
	 * 
	 * GET /api/delegates/count
	 * 
	 * Response { "success": true, "count": 101 } Example curl -k -X GET
	 * http://localhost:8000/api/delegates/count
	 * ======================================================================================================
	 */
	public JSONObject getDelegatesCount(URL url) {
		return null;
	}

	/**
	 * <b>Get votes by account wallet address</b> </br>
	 * <i>GET /api/accounts/delegates/?address=address</i> </br>
	 * 
	 * 
	 * @param url-
	 *            <i>URL of node to call api from</i> </br>
	 * @param address-
	 *            <i>Address of account</i> </br>
	 * @return <code>JSONObject of response</code></br>
	 *         { "success": true, "delegates": [ "array of of delegates object
	 *         (see above delegate object response)" ] }Delegates Array
	 *         includes: delegateId, address, publicKey, vote (#section- of
	 *         votes), producedBlocks, missedBlocks, rate,
	 */
	public JSONObject getVotesByAccount(URL url, String address) {
		return null;
	}

	// TODO: TEST!
	/**
	 * <b>Enables forging for a delegate on the client node</b> </br>
	 * <i>POST /api/delegates/forging/enable</i> </br>
	 * 
	 * @param url
	 *            - <i>URL of node to call api from</i> </br>
	 * @param secret
	 *            - <i>The secret pass phrase of the account to enable</i> </br>
	 * @return <code>JSONObject of response</code></br>
	 *         { "success": true, </br>
	 *         "address": "address" }
	 * @throws MalformedURLException
	 * 
	 */
	public JSONObject enableForging(URL url) throws MalformedURLException {
		url = new URL(url + ENABLE_FORGING_API);
		return httpHandler.httpPOST(url);
	}

	// TODO: TEST!
	/**
	 * <b>Disables forging for a delegate on the client node</b> </br>
	 * <i>POST /api/delegates/forging/disable</i> </br>
	 * 
	 * @param url
	 *            - <i>URL of node to call api from</i> </br>
	 * @param secret
	 *            - <i>The secret pass phrase of the account to disable</i>
	 *            </br>
	 * @return <code>JSONObject of response</code></br>
	 *         { "success": true, </br>
	 *         "address": "address" }
	 * @throws MalformedURLException
	 * 
	 */
	public JSONObject disableForging(URL url, String secret) throws MalformedURLException {
		url = new URL(url + DISABLE_FORGING_API);
		return httpHandler.httpPOST(url);
	}

	// TODO: TEST!
	/**
	 * <b>Get amount of Lisk forged by an account.</b> </br>
	 * <i>GET</i>
	 * <i>/api/delegates/forging/getForgedByAccount?generatorPublicKey=generatorPublicKey</i>
	 * </br>
	 * </br>
	 * 
	 * @param url
	 *            - <i>URL of node to call api from</i> </br>
	 * @param generatorPublicKey-
	 *            <i>generator id of block in hex</i> <code>(String)</code></br>
	 * 
	 *            Response { "success": true, "fees": "Forged amount. Integer",
	 *            "rewards":"Forged amount. Integer", "forged":"Forged amount.
	 *            Integer" } Example curl -k -X GET
	 *            http://localhost:8000/api/delegates/forging/getForgedByAccount?generatorPublicKey=<generatorPublicKey>
	 * @throws MalformedURLException
	 * 
	 */
	public JSONObject getForgedByAccount(URL url, String generatorPublicKey) throws MalformedURLException {
		return getForgedByAccount(url, generatorPublicKey, null, null);
	}

	// TODO: TEST!
	/**
	 * <b>Get amount of Lisk forged by an account.</b> </br>
	 * <i>GET</i>
	 * <i>/api/delegates/forging/getForgedByAccount?generatorPublicKey=generatorPublicKey</i>
	 * </br>
	 * </br>
	 * 
	 * @param url
	 *            - <i>URL of node to call api from</i> </br>
	 * @param start
	 *            - <i>Sets the start time of the search [<u>timestamp UNIX
	 *            time</u>]</i> <code>(String)</code></br>
	 * @param end
	 *            - <i>ets the endtime of the search - [<u>timestamp UNIX
	 *            time</u>]</i> </i> <code>(String)</code></br>
	 * @param generatorPublicKey
	 *            - <i>generator id of block in hex</i>
	 *            <code>(String)</code></br>
	 * 
	 * @return <code>JSONObject of response</code></br>
	 *         { </br>
	 *         "success": true, "fees": "Forged amount. Integer",</br>
	 *         "rewards":"Forged amount. Integer",</br>
	 *         "forged":"Forged amount. Integer" </br>
	 *         }
	 * @throws MalformedURLException
	 * 
	 */
	public JSONObject getForgedByAccount(URL url, String generatorPublicKey, String start, String end)
			throws MalformedURLException {

		String suffix = "generatorPublicKey=" + generatorPublicKey;
		if (start != null && end != null) {
			suffix = suffix + "&start=" + start;
			suffix = suffix + "&end" + end;
		}
		url = new URL(url + GET_FORGED_API + suffix);
		return httpHandler.httpGET(url);
	}

	// TODO: TEST!
	/**
	 * <b>Get next delegate lining up to forge.</b></br>
	 * <i>GET /api/delegates/getNextForgers?limit=limit</i> </br>
	 * </br>
	 * </br>
	 * 
	 * Response
	 * 
	 * @param url
	 *            - <i>URL of node to call api from</i>
	 * @param limit
	 *            - <i>limits amount of delegates, default 10, max 101</i>
	 *            <code>(Integer)</code>
	 * @return <code>JSONObject of response</code></br>
	 *         { </br>
	 *         "success": true, </br>
	 *         "currentBlock": "Current block based on height.
	 *         <code>Integer</code>",</br>
	 *         "currentSlot": "Current slot based on time.
	 *         <code>Integer</code>",</br>
	 *         "delegates": [ "array of publicKeys. <code>Strings</code>" ]
	 *         </br>
	 *         }
	 * @throws MalformedURLException
	 */
	public JSONObject getNextForger(URL url, int limit) throws MalformedURLException {
		if (limit > 101 || limit <= 0) {
			limit = 10;
		}
		String suffix = "limit=" + limit;
		url = new URL(url + NEXT_FORGERS_API + suffix);

		return httpHandler.httpGET(url);
	}
}
