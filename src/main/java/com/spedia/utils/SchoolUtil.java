package com.spedia.utils;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.StringReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.swing.text.MutableAttributeSet;
import javax.swing.text.html.HTML.Tag;
import javax.swing.text.html.HTMLEditorKit.ParserCallback;
import javax.swing.text.html.parser.ParserDelegator;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpression;
import javax.xml.xpath.XPathFactory;

import org.springframework.security.web.savedrequest.SavedRequest;
import org.w3c.dom.Document;

import com.google.gson.Gson;
import com.spedia.model.SchoolData;

/**
 * 
 */

/**
 * @author pawan
 * 
 */
public class SchoolUtil {
	private static final String HTTP_MAPS_GOOGLEAPIS_COM_MAPS_API_GEOCODE_XML_ADDRESS = "http://maps.googleapis.com/maps/api/geocode/xml?address=";
	public static Gson gson = new Gson();

	public static Gson getGson() {
		return gson;
	}

	public static final String key = "AIzaSyCvobcJHI5jZBrwM2L2xILApux1zFZwNrc";

	public static String getDataBetween2String(String str1, String str2,
			String content) {
		int start_location = content.indexOf(str1);
		int end_location = content.indexOf(str2);
		return content.substring(start_location + str1.length(), end_location);
	}

	public static String getDataBetween2StringRegex(String str1, String str2,
			String content) {
		try {
			Pattern p = Pattern.compile(str1 + "(.*)" + str2, Pattern.DOTALL);

			Matcher matcher = p.matcher(content);

			if (matcher.matches()) {
				return matcher.group(1).trim();
			} else {
				return getDataBetween2String(str1, str2, content).trim();
			}
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}

	}

	public static SchoolData getData(String article) {
		String school_name = getDataBetween2StringRegex("Name of Institution",
				"Affiliation Number", article);
		String aff_no = getDataBetween2StringRegex("Affiliation Number",
				"State", article);
		if (SocialUtility.chkNull(aff_no)) {
			return null;
		}
		String state = getDataBetween2StringRegex("State", "District", article);
		String district = getDataBetween2StringRegex("District",
				"Postal Address", article);
		String postal_address = getDataBetween2StringRegex("Postal Address",
				"Pin Code", article);
		String pin_code = getDataBetween2StringRegex("Pin Code",
				"Phone No. with STD Code", article);
		String phone_no = getDataBetween2StringRegex("Phone No. with STD Code",
				"Email", article);
		String email = getDataBetween2StringRegex("Email", "Website", article);
		String website = getDataBetween2StringRegex("Website",
				"Year of Foundation", article);
		String yof = getDataBetween2StringRegex("Year of Foundation",
				"Date of First Opening of School", article);
		String pn = getDataBetween2StringRegex(
				"Name of Principal/ Head of Institution", "Sex", article);
		String sos = getDataBetween2StringRegex("Status of The School",
				"Type of affiliation", article);
		String tn = getDataBetween2StringRegex(
				"Name of Trust/ Society/ Managing Committee",
				"Location of School", article);
		String cos = getDataBetween2StringRegex("Category of School",
				"Medium of Instruction", article);
		String moi = getDataBetween2StringRegex("Medium of Instruction",
				"Types of School", article);
		String tos = getDataBetween2StringRegex("Types of School",
				"Enrolment of the Students", article);
		Date createdOn = new Date();
		SchoolData schoolData = new SchoolData(0, school_name, aff_no, state,
				district, postal_address, pin_code, phone_no, email, website,
				"school_code", "school_url", "city", "tags", "", yof, pn, sos,
				tn, cos, moi, tos, createdOn.getTime(), 0);
		System.out.println(school_name);
		return schoolData;
	}

	public static String readFile(String fileNanme) {
		String strLine = "";
		StringBuffer content = new StringBuffer("");
		try {
			// Open the file that is the first
			// command line parameter
			// InputStream stream =
			// SchoolData.class.getResourceAsStream(fileNanme);

			FileInputStream fstream = new FileInputStream(fileNanme);
			// Get the object of DataInputStream
			// DataInputStream in = new DataInputStream(fstream);
			BufferedReader br = new BufferedReader(new InputStreamReader(
					fstream));

			// Read File Line By Line
			while ((strLine = br.readLine()) != null) {
				// Print the content on the console
				content.append(strLine);
			}
			// Close the input stream
		} catch (Exception e) {// Catch exception if any
			e.printStackTrace();
			System.err.println("Error: " + e.getMessage());
		}
		return content.toString();
	}

	public static void writeFile(String fileName, String content) {
		try {

			File file = new File("/var/www/schoolspedia.com/cbse_data/"
					+ fileName + ".txt");
			// if file doesnt exists, then create it
			if (!file.exists()) {
				file.createNewFile();
			}

			FileWriter fw = new FileWriter(file.getAbsoluteFile());
			BufferedWriter bw = new BufferedWriter(fw);
			bw.write(content);
			bw.close();

			System.out.println("file create for for " + fileName);

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static String extractText(String html) throws IOException {
		final ArrayList<String> list = new ArrayList<String>();

		ParserDelegator parserDelegator = new ParserDelegator();
		ParserCallback parserCallback = new ParserCallback() {
			public void handleText(final char[] data, final int pos) {
				list.add(new String(data));
			}

			public void handleStartTag(Tag tag, MutableAttributeSet attribute,
					int pos) {
			}

			public void handleEndTag(Tag t, final int pos) {
			}

			public void handleSimpleTag(Tag t, MutableAttributeSet a,
					final int pos) {
			}

			public void handleComment(final char[] data, final int pos) {
			}

			public void handleError(final java.lang.String errMsg, final int pos) {
			}
		};
		parserDelegator.parse(new StringReader(html), parserCallback, true);

		String text = "";

		for (String s : list) {
			text += " " + s;
		}

		return text;
	}

	public static String removeHTML(String htmlString) {
		// Remove HTML tag from java String
		String noHTMLString = htmlString.replaceAll("\\<.*?\\>", "");

		// Remove Carriage return from java String
		noHTMLString = noHTMLString.replaceAll("\r", "<br/>");

		// Remove New line from java string and replace html break
		noHTMLString = noHTMLString.replaceAll("\n", " ");
		noHTMLString = noHTMLString.replaceAll("\'", "&#39;");
		noHTMLString = noHTMLString.replaceAll("\"", "&quot;");
		return noHTMLString;
	}

	public static StringBuffer getContent(String url)
			throws MalformedURLException, IOException {
		int c;
		URL hp = new URL(url);
		URLConnection hpCon = hp.openConnection();
		int len = hpCon.getContentLength();
		System.out.println("Content-Length: " + len);
		StringBuffer content = new StringBuffer("");
		if (len > 0) {
			System.out.println("=== Content ===");
			InputStream input = hpCon.getInputStream();
			int i = len;

			while (((c = input.read()) != -1) && (--i > 0)) {
				content.append((char) c);
			}
			input.close();
		} else {
			System.out.println("No Content Available");
		}
		return content;
	}

	/*
	 * public static String readFile(String fileNanme){ String strLine="";
	 * StringBuffer content=new StringBuffer(""); try{ // Open the file that is
	 * the first // command line parameter InputStream stream =
	 * FetchData.class.getResourceAsStream(fileNanme);
	 * 
	 * // FileInputStream fstream = new FileInputStream("/cbse_data.html"); //
	 * Get the object of DataInputStream //DataInputStream in = new
	 * DataInputStream(fstream); BufferedReader br = new BufferedReader(new
	 * InputStreamReader(stream));
	 * 
	 * //Read File Line By Line while ((strLine = br.readLine()) != null) { //
	 * Print the content on the console content.append(strLine); } //Close the
	 * input stream }catch (Exception e){//Catch exception if any
	 * System.err.println("Error: " + e.getMessage()); } return
	 * content.toString(); }
	 */
	public static String makeHttpCall(String address) throws Exception {
		URL url = new URL(address);
		HttpURLConnection urlc = (HttpURLConnection) url.openConnection();
		urlc.setDoOutput(true);
		urlc.setConnectTimeout(60000);
		// Get the response
		BufferedReader reader = new BufferedReader(new InputStreamReader(
				urlc.getInputStream()));
		StringBuffer sb = new StringBuffer();
		String line;
		System.out.println(urlc.getResponseCode());
		while ((line = reader.readLine()) != null) {
			sb.append(line);
		}
		return sb.toString();

	}

	public static Boolean isValidCaptcha(HttpServletRequest httpServletRequest) {
		Boolean isResponseCorrect = Boolean.FALSE;
		// retrieve the response
		String code = httpServletRequest.getParameter("j_captcha_response");
		// Call the Service method
		String captcha = (String) httpServletRequest.getSession().getAttribute(
				"captcha");
		if (captcha != null && code != null) {
			if (captcha.equalsIgnoreCase(code)) {
				isResponseCorrect = Boolean.TRUE;
			}
		}
		return isResponseCorrect;
	}

	public static String getRedirectUrl(HttpServletRequest request) {
		HttpSession session = request.getSession(false);
		if (session != null) {
			SavedRequest savedRequest = (SavedRequest) session
					.getAttribute("SPRING_SECURITY_SAVED_REQUEST");
			if (savedRequest != null) {
				return savedRequest.getRedirectUrl();
			}
		}

		/* return a sane default in case data isn't there */
		return request.getHeader("referer");
	}

	public static String generateRandomString(int length) {
		StringBuffer buffer = new StringBuffer();
		String characters = "1abc7def1ghijk3lmn2p2qrst2u1vwxyzAB2C9DEFGH8IJKL3MNPQR8STU75VWXYZ123456789";
		int charactersLength = characters.length();
		for (int i = 0; i < length; i++) {
			double index = Math.random() * charactersLength;
			buffer.append(characters.charAt((int) index));
		}
		return buffer.toString();
	}

	public static String[] getLatLong(String address) {
		int responseCode = 0;
		try {
			String api = HTTP_MAPS_GOOGLEAPIS_COM_MAPS_API_GEOCODE_XML_ADDRESS
					+ URLEncoder.encode(address, "UTF-8") + "&sensor=true";
			URL url = new URL(api);
			HttpURLConnection httpConnection = (HttpURLConnection) url
					.openConnection();
			httpConnection.connect();
			responseCode = httpConnection.getResponseCode();
			if (responseCode == 200) {
				DocumentBuilder builder = DocumentBuilderFactory.newInstance()
						.newDocumentBuilder();
				;
				Document document = builder.parse(httpConnection
						.getInputStream());
				XPathFactory xPathfactory = XPathFactory.newInstance();
				XPath xpath = xPathfactory.newXPath();
				XPathExpression expr = xpath.compile("/GeocodeResponse/status");
				String status = (String) expr.evaluate(document,
						XPathConstants.STRING);
				if (status.equals("OK")) {
					expr = xpath.compile("//geometry/location/lat");
					String latitude = (String) expr.evaluate(document,
							XPathConstants.STRING);
					expr = xpath.compile("//geometry/location/lng");
					String longitude = (String) expr.evaluate(document,
							XPathConstants.STRING);
					return new String[] { latitude, longitude };
				}
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	public static void main(String[] args) {
		String address = "E3/1 sector 11 rohini delhi 110085";
		String[] latLong = getLatLong(address);
		System.out.println(latLong[0]);
		System.out.println(latLong[1]);
	}

}
