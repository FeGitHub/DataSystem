package com.DS.utils.common;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
/**
 * Square公司的Ok_http
 * 用于调用其他服务器的action
 */
public class Ok_httpUtil {
	/*public static void main(String[] args) throws IOException {
		//参数处理
		 Gson gs = new GsonBuilder().setPrettyPrinting().disableHtmlEscaping().create(); 
		 Map<String, Object> map = new HashMap<String, Object>(); 
		 String temp="test";
		 map.put("data", temp); 
		 String jsonmap = gs.toJson(map);
		 String url="http://localhost:8080/RemoteAction/OriginalHttp";
		 //请求处理	
		Ok_httpUtil.doPOST(url, jsonmap);		
	}  */
	
	/***
	 * GET方法
	 * @param url 调用的action
	 * @return
	 * @throws IOException
	 */
	   public static String doGET(String url) throws IOException {
		OkHttpClient client = new OkHttpClient();
	    Request request = new Request.Builder().url(url).build();
	    Response response = client.newCall(request).execute();
	    if (response.isSuccessful()) {
	        return response.body().string();
	    } else {
	        throw new IOException("Unexpected code " + response);
	    }
	}
	   
	  
	   /***
	    * POST方法
	    * @param url 调用的action
	    * @param json 传递的参数
	    * @return
	    * @throws IOException
	    */
	   public static String doPOST(String url, String json) throws IOException {
		   MediaType JSON = MediaType.parse("application/json; charset=utf-8");
		   OkHttpClient client = new OkHttpClient();
	       RequestBody body = RequestBody.create(JSON, json);
	       Request request = new Request.Builder()
	         .url(url)
	         .post(body)
	         .build();
	       Response response = client.newCall(request).execute();
	       if(response.isSuccessful()) {
	           return response.body().string();
	       } else {
	           throw new IOException("Unexpected code " + response);
	       }
	   }
}
