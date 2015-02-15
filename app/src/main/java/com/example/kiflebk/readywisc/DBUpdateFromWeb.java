package com.example.kiflebk.readywisc;

import android.util.Log;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.ParseException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

/**
 * Created by piela_000 on 2/14/2015.
 */
public class DBUpdateFromWeb {

    public String[] updateLocalDB() {
        JSONArray jArray = null;

        String result = null;

        StringBuilder sb = null;

        InputStream is = null;

        String[] ct_name = null;

        ArrayList<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>();
//http post
        try {
            HttpClient httpclient = new DefaultHttpClient();

            //Why to use 10.0.2.2
            HttpPost httppost = new HttpPost("http://piela.co/database/");
            httppost.setEntity(new UrlEncodedFormEntity(nameValuePairs));
            HttpResponse response = httpclient.execute(httppost);
            HttpEntity entity = response.getEntity();
            is = entity.getContent();
        } catch (Exception e) {
            Log.e("log_tag", "Error in http connection" + e.toString());
        }
//convert response to string
        try {
            BufferedReader reader = new BufferedReader(new InputStreamReader(is, "iso-8859-1"), 8);
            sb = new StringBuilder();
            sb.append(reader.readLine() + "\n");

            String line = "0";
            while ((line = reader.readLine()) != null) {
                sb.append(line + "\n");
            }
            is.close();
            result = sb.toString();
        } catch (Exception e) {
            Log.e("log_tag", "Error converting result " + e.toString());
        }

        String name;
        try {
            jArray = new JSONArray(result);
            JSONObject json_data = null;

            ct_name = new String[jArray.length()];
            for (int i = 0; i < jArray.length(); i++) {
                json_data = jArray.getJSONObject(i);
                ct_name[i] = json_data.getString("name"); //here "Name" is the column name in database
            }
        } catch (JSONException e1) {

        } catch (ParseException e1) {
            e1.printStackTrace();
        }
        return ct_name;
    }
}

