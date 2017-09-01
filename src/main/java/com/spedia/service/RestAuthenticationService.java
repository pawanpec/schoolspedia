package com.spedia.service;

import java.io.IOException;
import java.util.StringTokenizer;

import org.apache.commons.codec.binary.Base64;

import com.spedia.utils.WebConstants;


public class RestAuthenticationService {
	public boolean authenticate(String authCredentials) {

		if (null == authCredentials)
			return false;
		// header value format will be "Basic encodedstring" for Basic
		// authentication. Example "Basic YWRtaW46YWRtaW4="
		final String encodedUserPassword = authCredentials.replaceFirst("Basic"
				+ " ", "");
		String usernameAndPassword = null;
		try {
			byte[] decodedBytes = Base64.decodeBase64(encodedUserPassword.getBytes());
			usernameAndPassword = new String(decodedBytes, "UTF-8");
		} catch (IOException e) {
			e.printStackTrace();
		}
		/*final StringTokenizer tokenizer = new StringTokenizer(
				usernameAndPassword, ":");*/
		String[] tokenizer=usernameAndPassword.split(":");
		// we have fixed the userid and password as admin
		// call some UserService/LDAP here
		boolean authenticationStatus=false;
		if (tokenizer.length==2) {
			final String username = tokenizer[0];
			final String password = tokenizer[1];
			authenticationStatus = WebConstants.apiUser.equals(username)
					&& WebConstants.apiPassword.equals(password);
		}
		return authenticationStatus;
	}
}
