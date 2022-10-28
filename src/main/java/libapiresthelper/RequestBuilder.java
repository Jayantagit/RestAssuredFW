package libapiresthelper;

import io.restassured.http.Headers;

public class RequestBuilder {

	String method;
	String url;
	String body;
	String correlation_id;
	String auth;
	String cookie;
	Headers headers;
	String accept;
	String contentType;
	String multiPartfile;

	public RequestBuilder() {

	}

	public RequestBuilder(String method, String body, String correlation_id, String auth) {

		this.setMethod(method);
		if (body != null) {
			this.setBody(body);

		}
		this.setCorrelation_id(correlation_id);
		this.setAuth(auth);
	}

	public RequestBuilder(String method, String body, String correlation_id, String auth, Headers headers) {

		this.setMethod(method);
		if (body != null) {
			this.setBody(body);

		}
		this.setCorrelation_id(correlation_id);
		this.setAuth(auth);
		this.setHeaders(headers);
	}

	public RequestBuilder(String method, String body, String correlation_id, String auth, Headers headers,
			String contentType, String accept) {

		this.setMethod(method);
		if (body != null) {
			this.setBody(body);

		}
		this.setCorrelation_id(correlation_id);
		this.setAuth(auth);
		this.setHeaders(headers);
		this.setAccept(accept);
		this.setContentType(contentType);
	}

	public RequestBuilder(String method, String corr_id, String url, String auth, String body, Headers headers) {
		this.setMethod(method);
		if (body != null) {
			this.setBody(body);

		}
		this.setCorrelation_id(correlation_id);
		this.setAuth(auth);
		this.setHeaders(headers);
		this.setUrl(url);
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

	public String getCorrelation_id() {
		return correlation_id;
	}

	public void setCorrelation_id(String correlation_id) {
		this.correlation_id = correlation_id;
	}

	public String getAuth() {
		return auth;
	}

	public void setAuth(String auth) {
		this.auth = auth;
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

	public String getAccept() {
		return accept;
	}

	public void setAccept(String accept) {
		this.accept = accept;
	}

	public String getContentType() {
		return contentType;
	}

	public void setContentType(String contentType) {
		this.contentType = contentType;
	}

	public String getMultiPartfile() {
		return multiPartfile;
	}

	public void setMultiPartfile(String multiPartfile) {
		this.multiPartfile = multiPartfile;
	}

}
