package com.platzi.api.httpRequest;

import java.util.HashMap;
import java.util.Map;

import com.platzi.api.baseAPI.BaseClient;

import io.restassured.filter.session.SessionFilter;
import io.restassured.response.Response;

	public class HttpRequest extends BaseClient{
		
		public static Response response;
		public static SessionFilter session;
		public static Map<String,String> headerMap = new HashMap<>();

		public HashMap<String, String> setHeaders() {
			HashMap<String,String> headerMap = new HashMap<>();
					headerMap.put("User-Agent", "");
					headerMap.put("Host", "Admin");
					headerMap.put("Accept-Encoding", "gzip, deflate, br");
					headerMap.put("Connection", "keep-alive");
					headerMap.put("Content-Type", "application/json");
					
		return headerMap;
		}
			
		public Response createProductUsingPostCall(String body) {
			
			session=new SessionFilter();
			response = getbaseUrl().headers(headerMap)
					.body(body).log().all().filter(session).when().post(getInputProperty("loginendPointurl")).then().log().all().extract().response();
			return response;
		}
		
		public Response retrieveProductUsingGetCall() {
			
			headerMap = setHeaders();
			SessionFilter session=new SessionFilter();
			Response response =  getbaseUrl().headers(headerMap)
					.log().all().filter(session).when().get(getInputProperty("endPointurl")).then().log().all().extract().response();
			return response;
		}
		
		public Response updateProductUsingPutCall(String body) {
			
			
			session=new SessionFilter();
			response =  getbaseUrl().headers(headerMap)
					.body(body).log().all().filter(session).when().post(getInputProperty("endPointUrl")).then().log().all().extract().response();
			return response;
		}
		
		public Response deleteProductUsingDeleteCall(int id) {
			
			response =  getbaseUrl().headers(headerMap).delete(getInputProperty("endPointUrl")+id);
			return response;
		}
		
		public Response retrieveProductUsingGetCall(int id) {
			
			headerMap = setHeaders();
			SessionFilter session=new SessionFilter();
			Response response =  getbaseUrl().headers(headerMap)
					.log().all().filter(session).when().get(getInputProperty("endPointurl")+ id).then().log().all().extract().response();
			return response;
		}
		
}


