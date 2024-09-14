package com.platzi.api.baseAPI;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Map;
import java.util.Properties;

import com.dev.orange.hrm.utilities.Log;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class BaseClient {

	public static Response response=null;
	public static Properties prop = null;
	public static RequestSpecification httprequest =  RestAssured.given();
	public static FileInputStream inputStream = null;
	private static final String defaultPropertiesFile = System.getProperty("user.dir")+"//src//main//resources//platziApi.properties";

	private static Properties readPropertyFile() {

		File file = new File(defaultPropertiesFile);
		try {
			inputStream = new FileInputStream(file);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			Log.error("File Not Found at Path");
		}
		prop = new Properties();
		try {
			prop.load(inputStream);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return prop;
	}

	public static String getInputProperty(String input) {
		
		prop = readPropertyFile();
		String property = prop.getProperty(input);
		return property;
	}
	
	public static RequestSpecification getbaseUrl() {
		
		RequestSpecification setBaseUri = httprequest.baseUri(getInputProperty("baseuri"));
		return setBaseUri;
	}
	
	public static String createPostCallBody() {
		
		String body = "{\r\n"
				+ "  \"title\": \"Party Wear\",\r\n"
				+ "  \"price\": 10,\r\n"
				+ "  \"description\": \"A description\",\r\n"
				+ "  \"categoryId\": 1,\r\n"
				+ "  \"images\": [\"https://placeimg.com/640/480/any\"]\r\n"
				+ "}";
		return body;
	}
	public static String getPutBody() {
		
		String body = "{\r\n"
				+ "  \"title\": \"Winter Wear\",\r\n"
				+ "  \"price\": 100\r\n"
				+ "}";
		return body;
	}
	
	public static String createNewUser() {
		String userDetails = "{\r\n"
				+ "  \"name\": \"Admin57\",\r\n"
				+ "  \"email\": \"Admin57@gmail.com\",\r\n"
				+ "  \"password\": \"Admin123\",\r\n"
				+ "  \"avatar\": \"https://picsum.photos/800\",\r\n"
				+ "  \"role\": \"admin\"\r\n"
				+ "}";
	return userDetails;
}
	
	
	public static String getKeyValueFromJsonResponse(String response,String key) {
		
		String value = null;
		try{ 
			if(response.isBlank() || response==null) {
				JsonPath responsecode = new JsonPath(response);
				value =responsecode.get(key);
			}
			} catch(Exception e){
				e.printStackTrace();
				Log.info("Response Key Value not Found From the Response");
			}
			return value;			
	}
	
	public static String loginKeys() {
	
		String value="{\r\n"
				+ "  \"email\": \"john@mail.com\",\r\n"
				+ "  \"password\": \"changeme\"\r\n"
				+ "}";
		return value;
	}
	public static void createRequest(Map<String,String> data,String key) {
		
		if(response.asString()!=null) {
		data.put(key,getKeyValueFromJsonResponse(response.asString(),key));
		}else {
			Log.info("Response doesn't have Keys and Values ");
		}
	}
}
