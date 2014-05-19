package net.ryandoyle.libjnagios;

import org.apache.commons.codec.binary.Base64; /*FIXME: don't depend on apache-commons */

import java.util.AbstractMap;
import java.util.HashMap;

public class HttpClient {


    private final String url;
    private HashMap<String,String> headers = new HashMap<String, String>();

    public HttpClient(String url, String userName, String password){
        this(url);
        String userNameAndPasswordInBasicAuthFormat = new String(userName + ":" + password);
        String encodedBasicAuth = new String(Base64.encodeBase64(userNameAndPasswordInBasicAuthFormat.getBytes()));
        headers.put("Authorization", "Basic " + encodedBasicAuth);
    }

    public HttpClient(String url){
        this.url = url;
    }

    public HashMap<String,String> getHeaders(){
        return headers;
    }
}
