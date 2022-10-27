package POjO;

import io.restassured.http.Headers;

public class RequestBuilders {
	
	String method;
	String url;
	String body;
	String cookie;
	Headers headers;
	String contentType;
	String multiPartFile;	
	
	
	
	public RequestBuilders(String method, String url, String body, String cookie, Headers headers, String contentType) {
		super();
		this.method = method;
		this.url = url;
		this.body = body;
		this.cookie = cookie;
		this.headers = headers;
		this.contentType = contentType;
	}
	public RequestBuilders(String method, String url, String body, String cookie, Headers headers, String contentType,
			String multiPartFile) {
		super();
		this.method = method;
		this.url = url;
		this.body = body;
		this.cookie = cookie;
		this.headers = headers;
		this.contentType = contentType;
		this.multiPartFile = multiPartFile;
	}
	public RequestBuilders(String method, String url, String cookie, Headers headers, String contentType) {
		super();
		this.method = method;
		this.url = url;
		this.cookie = cookie;
		this.headers = headers;
		this.contentType = contentType;
	}
	public String getMethod() {
		return method;
	}
	public void setMethod(String method) {
		this.method = method;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public String getBody() {
		return body;
	}
	public void setBody(String body) {
		this.body = body;
	}
	public String getCookie() {
		return cookie;
	}
	public void setCookie(String cookie) {
		this.cookie = cookie;
	}
	public Headers getHeaders() {
		return headers;
	}
	public void setHeaders(Headers headers) {
		this.headers = headers;
	}
	public String getContentType() {
		return contentType;
	}
	public void setContentType(String contentType) {
		this.contentType = contentType;
	}
	public String getMultiPartFile() {
		return multiPartFile;
	}
	public void setMultiPartFile(String multiPartFile) {
		this.multiPartFile = multiPartFile;
	}
	
	

}
