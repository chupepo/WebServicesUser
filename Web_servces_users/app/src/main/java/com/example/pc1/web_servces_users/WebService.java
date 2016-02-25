package com.example.pc1.web_servces_users;

import android.os.AsyncTask;
import android.text.StaticLayout;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URI;

/**
 * Created by pc1 on 24/02/2016.
 */
public class WebService  {



    JSONObject responseJson;
    private static String URL="https://api.github.com/users";

    public String getInternetData(TextView tv)throws Exception{
        BufferedReader in = null;
        String data = "";
        String url2="http://www.mybringback.com";
        try {

            HttpClient httpclient = new DefaultHttpClient();
            URI website = new URI(url2);
            HttpGet httpGet = new HttpGet();
            httpGet.setURI(website);
            tv.setText("zzzzzzzzzzzzzzz");
            HttpResponse httpResponse = httpclient.execute(httpGet);
            tv.setText("xxxxxxxxxxxxxxxxxxxxx");
            in = new BufferedReader(new InputStreamReader(httpResponse.getEntity().getContent()));
            /////////System.out.println(httpGet.getFirstHeader(""));

            StringBuffer sb = new StringBuffer("");
            String l = "";
            String nl = System.getProperty("line.separator");

            while ((l = in.readLine()) != null) {
                sb.append(l + nl);
            }
            in.close();
            data = sb.toString();

            return data;
        }finally {
            if (in != null){
                try{
                    in.close();
                    return data;
                }catch (Exception e){
                    e.printStackTrace();
                }

            }

        }

    }
}
