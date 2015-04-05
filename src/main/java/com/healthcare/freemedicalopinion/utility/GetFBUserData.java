package com.healthcare.freemedicalopinion.utility;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;

import org.json.JSONObject;

import com.healthcare.freemedicalopinion.utility.FreeMedicalOpinionRoles.Gender;
import com.healthcare.freemedicalopinion.valueobject.UserProfileVO;

public class GetFBUserData {
	private String accessToken;
	private String code;
	private String currUrl;

	public GetFBUserData(String code) {
		this.code = code;
	}

	public GetFBUserData() {
	}

	public UserProfileVO getFBGraph() throws Exception {
		String graph = null;
		try {

			String g = "https://graph.facebook.com/me?" + accessToken;
			URL u = new URL(g);
			URLConnection c = u.openConnection();
			BufferedReader in = new BufferedReader(new InputStreamReader(
					c.getInputStream()));
			String inputLine;
			StringBuffer b = new StringBuffer();
			while ((inputLine = in.readLine()) != null)
				b.append(inputLine + "\n");
			in.close();
			graph = b.toString();
			// System.out.println(graph);
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException("ERROR in getting FB graph data. " + e);
		}
		return getGraphData(graph);
	}

	private UserProfileVO getGraphData(String fbGraph) throws Exception {

		JSONObject json = new JSONObject(fbGraph);
		UserProfileVO user = new UserProfileVO();
		user.setUsername(json.getString("email"));
		user.setFname(json.getString("first_name"));
		user.setLname(json.getString("last_name"));
		user.setFbLink(json.getString("link"));
		if (json.getString("gender").equalsIgnoreCase("male")) {
			user.setGender(Gender.MALE);
		} else {
			user.setGender(Gender.FEMALE);
		}

		return user;

	}

	public String getFBGraphUrl(String code) {
		String fbGraphUrl = "";
		try {
			fbGraphUrl = "https://graph.facebook.com/oauth/access_token?"
					+ "client_id=" + AppConstants.FB_APP_ID + "&redirect_uri="
					+ URLEncoder.encode(AppConstants.REDIRECT_URI, "UTF-8")
					+ "&client_secret=" + AppConstants.FB_APP_SECRET + "&code="
					+ code;
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return fbGraphUrl;
	}

	public String getAccessToken() {
		if ("".equals(accessToken) || accessToken == null) {
			URL fbGraphURL;
			try {
				fbGraphURL = new URL(getFBGraphUrl(code));
			} catch (MalformedURLException e) {
				e.printStackTrace();
				throw new RuntimeException("Invalid code received " + e);
			}
			URLConnection fbConnection;
			StringBuffer b = null;
			try {
				fbConnection = fbGraphURL.openConnection();
				BufferedReader in;
				in = new BufferedReader(new InputStreamReader(
						fbConnection.getInputStream()));
				String inputLine;
				b = new StringBuffer();
				while ((inputLine = in.readLine()) != null)
					b.append(inputLine + "\n");
				in.close();
			} catch (IOException e) {
				e.printStackTrace();
				throw new RuntimeException("Unable to connect with Facebook "
						+ e);
			}

			accessToken = b.toString();
			if (accessToken.startsWith("{")) {
				throw new RuntimeException("ERROR: Access Token Invalid: "
						+ accessToken);
			}
		}
		return accessToken;
	}

	/**
	 * @return the currUrl
	 */
	public String getCurrUrl() {
		return currUrl;
	}

	/**
	 * @param currUrl
	 *            the currUrl to set
	 */
	public void setCurrUrl(String currUrl) {
		this.currUrl = currUrl;
	}

}
