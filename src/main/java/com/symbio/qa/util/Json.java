package com.symbio.qa.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import org.codehaus.jettison.json.JSONArray;
import org.codehaus.jettison.json.JSONException;
import org.codehaus.jettison.json.JSONObject;

import org.apache.commons.io.IOUtils;

public class Json {

    public Json() {

    }

    public JSONObject parseJsonObj(String fileName) throws IOException, JSONException {
        File file = new File(fileName);
        JSONObject json = null;
        if (file.exists()) {
            InputStream is = new FileInputStream(fileName);
            String jsonTxt = IOUtils.toString(is);
            json = new JSONObject(jsonTxt);
        } else {
            throw new FileNotFoundException("File not found.");
        }

        return json;
    }
    
    public JSONArray parseJsonArr(String fileName) throws IOException, JSONException{
        File file = new File(fileName);
        JSONArray json = null;
        if (file.exists()) {
            InputStream is = new FileInputStream(fileName);
            String jsonTxt = IOUtils.toString(is);
            json = new JSONArray(jsonTxt);
        } else {
            throw new FileNotFoundException("File not found.");
        }
        return json;
    }

    @SuppressWarnings("rawtypes")
    public JSONObject m2j(HashMap map){
        return new JSONObject(map);
    }
    
    @SuppressWarnings("rawtypes")
    public JSONArray a2j(ArrayList list){
        return new JSONArray(list);
    }
    
    @SuppressWarnings("rawtypes")
    public HashMap j2m(String fileName) throws IOException, JSONException {
        JSONObject jobj = parseJsonObj(fileName);
        return j2m(jobj);
    }

    @SuppressWarnings({ "unchecked", "rawtypes" })
    public HashMap j2m(JSONObject jobj) throws IOException, JSONException {
        HashMap result = new HashMap();
        for (Iterator keys = jobj.keys(); keys.hasNext();) {
            String key = keys.next().toString();
            if (jobj.get(key) instanceof JSONObject) {

                result.put(key, j2m((JSONObject) jobj.get(key)));
                continue;
            }
            if (jobj.get(key) instanceof JSONArray) {
                result.put(key, j2a((JSONArray) jobj.get(key)));
                continue;
            }
            result.put(key, jobj.get(key));
        }
        return result;

    }

    @SuppressWarnings({ "unchecked", "rawtypes" })
    public ArrayList j2a(JSONArray jArr) throws JSONException, IOException {
        ArrayList result = new ArrayList();
        for (int i = 0; i < jArr.length(); i++) {
            if (jArr.get(i) instanceof JSONObject) {
                result.add(j2m((JSONObject) jArr.get(i)));
                continue;
            }
            if (jArr.get(i) instanceof JSONArray) {
                result.add(j2a((JSONArray) jArr.get(i)));
                continue;
            }
            result.add(jArr.get(i));
        }
        return result;
    }


    public static void main(String[] args) throws IOException, JSONException {
        String fileName = "E:\\workspace\\SkillSoft\\SumT_API\\CES-v1-soapui-project\\CES-v1-soapui-project\\payload\\JSON\\AFJ\\discovery.json";
        Json json = new Json();
        JSONObject lll = json.parseJsonObj(fileName);

        Object a = lll.get("version");
        
        System.out.println(a);
        System.out.println(lll.toString());
        System.out.println(json.j2m(fileName));

    }

}
