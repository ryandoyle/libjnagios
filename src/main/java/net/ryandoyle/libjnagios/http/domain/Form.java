package net.ryandoyle.libjnagios.http.domain;

import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;

public class Form {

    private final HashMap<String, String> formData;

    public Form(){
        this.formData = new HashMap<String, String>();
    }

    public Form add(String key, String value){
        formData.put(key, value);
        return this;
    }

    public int getContentLength(){
        return toString().getBytes().length;
    }

    @Override
    public String toString(){
        StringBuilder formString = new StringBuilder();
        for(Map.Entry<String, String> entry : formData.entrySet()){
            formString.append(URLEncoder.encode(entry.getKey()) + "=" + URLEncoder.encode(entry.getValue()) + "&");
        }
        removeTrailingAmpersand(formString);
        return formString.toString();
    }

    private void removeTrailingAmpersand(StringBuilder formString) {
        formString.deleteCharAt(formString.length()-1);
    }


}
