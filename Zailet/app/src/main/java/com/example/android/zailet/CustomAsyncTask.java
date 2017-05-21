package com.example.android.zailet;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;

/**
 * Created by kartheek on 17/5/17.
 */

public class CustomAsyncTask extends AsyncTask<String,Void,String> {

    final static String TAG = "CustomAsyncTask";

    //progress bar for showing the sign in progress
    private ProgressDialog mProgress;

    //Application Context
    Context mCtx;

    public CustomAsyncTask(Context ctx) {
        super();
        mCtx = ctx;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();

        //Showing the progress dialog box when user is signing in
        mProgress = new ProgressDialog(mCtx);
        if(LoginActivity.type.equals("Login")) {
            mProgress.setMessage("Authenticating...");
        } else if(LoginActivity.type.equals("Register")) {
            mProgress.setMessage("Creating Account...");
        }
        mProgress.show();
    }

    @Override
    protected String doInBackground(String... params) {
        try {
            if(params[0].equals("Suggestions")) {
                return Suggestions(params[1]);
            } else if (LoginActivity.type.equals("Login")) {
                return PostLoginCredentials(params[0], params[1]);
            } else {
                return Register(params[0], params[1], params[2]);
            }
        } catch (IOException e) {
            Log.e(TAG, e + "");
            return "Failed To Connect";
        }
    }

    @Override
    protected void onPostExecute(String s) {
        mProgress.dismiss();
        if (s.equals("success")) {
                Intent mainIntent = new Intent(mCtx, MainActivity.class);
                mCtx.startActivity(mainIntent);
        } else Toast.makeText(mCtx, s, Toast.LENGTH_LONG).show();
    }


    //code for checking login credentials, Registering and searching for blogs in database
    public String PostLoginCredentials(String username, String password) throws IOException {

            String data = URLEncoder.encode("username", "UTF-8")
                    + "=" + URLEncoder.encode(username, "UTF-8") + "&" + URLEncoder.encode("password", "UTF-8") + "="
                    + URLEncoder.encode(password, "UTF-8");

            return PostAndReadData("http://192.168.43.232/login.php",data);
    }

    public String Register(String username, String password, String emailId) throws IOException {
            String data = URLEncoder.encode("username", "UTF-8") + "=" + URLEncoder.encode(username, "UTF-8")
                    + "&" + URLEncoder.encode("password","UTF-8") + "=" + URLEncoder.encode(password,"UTF-8")
                    + "&" + URLEncoder.encode("emailId","UTF-8") + "=" + URLEncoder.encode(emailId,"UTF-8");

            return PostAndReadData("http://192.168.43.232/Register.php",data);
    }

    public String Suggestions(String Query) throws IOException{

        String data = URLEncoder.encode("query", "UTF-8")
                + "=" + URLEncoder.encode(Query, "UTF-8");
        String suggestionsJson = PostAndReadData("http://192.168.43.232/test.php",data);
        SuggestionContentProvider.Suggestions = parseSuggestionsJson(suggestionsJson);
        return null;
    }


    //code for parsing json obtained for suggestions
    public ArrayList<Suggestions> parseSuggestionsJson(String suggestions) {
        try {
            JSONObject ob = new JSONObject(suggestions);
            JSONArray arr = ob.getJSONArray("suggestions");
            ArrayList<Suggestions> arrayList = new ArrayList<>();
            for(int i=0 ;i<arr.length();i++) {
                JSONObject ob1 = arr.getJSONObject(i);
                JSONArray jsonArray = ob1.getJSONArray("topic");
                String[] topics = new String[jsonArray.length()];
                for(int j=0 ; j<jsonArray.length(); j++) {
                    topics[i] = jsonArray.getString(j);
                }
                arrayList.add(new Suggestions(ob1.getString("title"),topics));
            }
            return arrayList;
        } catch (JSONException e) {
            Log.e(TAG, "" + e);
            return null;
        }
    }


    // code for making http request and posting and reading of data
    public String PostAndReadData(String mUrl, String data) throws IOException {

        URL url = createUrl(mUrl);
        HttpURLConnection conn = createUrlConnection(url);
        PostData(conn,data);
        return ReadData(conn);
    }

    public URL createUrl(String mUrl) {
        try {
            URL url = new URL(mUrl);
            return url;
        } catch (MalformedURLException e) {
            Log.e(TAG, e + "");
        }
        return null;
    }

    public HttpURLConnection createUrlConnection(URL url) throws IOException{
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setReadTimeout(15000);
            conn.setConnectTimeout(15000);
            conn.setRequestMethod("POST");
            conn.setDoInput(true);
            conn.setDoOutput(true);
            return conn;
    }

    public void PostData(HttpURLConnection conn, String data) throws IOException {
        Log.e(TAG, "GETTING THE OUTPUT STREAM");
        OutputStream os = conn.getOutputStream();
        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(os, "UTF-8"));
        Log.e(TAG, "POSTING");
        writer.write(data);
        // flushing the output if any left on the stream buffer to the server
        writer.flush();
        writer.close();
        os.close();
    }

    public String ReadData(HttpURLConnection conn) throws IOException {
        int responseCode = conn.getResponseCode();
        if (responseCode == HttpURLConnection.HTTP_OK) {
            BufferedReader reader = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            String input = "";
            StringBuilder sb = new StringBuilder("");
            while ((input = reader.readLine()) != null) {
                sb.append(input);
            }
            reader.close();
            return sb.toString();
        } else {
            Log.e(TAG, "Error in response code");
            return ("Failed : " + responseCode + "Error");
        }
    }
}
