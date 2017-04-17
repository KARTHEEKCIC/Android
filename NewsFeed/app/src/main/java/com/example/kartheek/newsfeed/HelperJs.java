package com.example.kartheek.newsfeed;

import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by kartheek on 14/3/17.
 */

public class HelperJs {

    //method to get the new articles from the guardian api
    public static List<NewsItem> getNews (String mUrl) {

        //list of all earthquakes to be fetched from the api
        List<NewsItem> newsList = new ArrayList<>();

        String newsJson = new String(); // string containing the json obtained from the url

        URL url = createUrl(mUrl); //creating the url from the string

        //making the request to the url for the json
        newsJson = makeHttpsRequest(url);

        //parsing the json to retrieve the news
        newsList = parseJson(newsJson);

        return newsList;
    }

    //method to parse the json obtained by making request
    public static List<NewsItem> parseJson(String newsJson) {

        List<NewsItem> newList = new ArrayList<NewsItem>();
        if(newsJson == null)
            return null;
        try {
            //Log.e("HelperJs.class","got g ob");
            JSONObject gObject = new JSONObject(newsJson);
            JSONObject response = gObject.getJSONObject("response");
            //Log.e("HelperJs.class","got the Array");
            JSONArray results = response.getJSONArray("results");
            for(int i=0;i<results.length();i++) {
                //Log.e("HelperJs.class","getting the title");
                newList.add(new NewsItem(results.getJSONObject(i).getJSONObject("fields").getString("headline"),modifyStandFirst(results.getJSONObject(i).getJSONObject("fields").getString("standfirst"))));
            }

        } catch (JSONException e) {
            Log.e("HelperJs.class","Problem in parsing the json");
        }
        return newList;
    }

    //method to create and return url from the given string
    public static URL createUrl(String mUrl) {

        URL url = null; // url to be formed from the given string
        try {
            url = new URL(mUrl);
        } catch (MalformedURLException e){
            Log.e("HelperJs.class","Url is Malformed",e); //check for the malformed url if null is returned or not
        }
        Log.e("HelperJs.class",url.toString());
        return url;
    }

    //method to make https request to the guardian api to fetch the data and the return the json
    public static String makeHttpsRequest(URL mUrl) {
        String newsJson = null;
        try {

            HttpURLConnection urlConnection = (HttpURLConnection) mUrl.openConnection();

            //setting the connection time out to 10 seconds
            urlConnection.setConnectTimeout(10000);

            //setting the read time out to 5 seconds
            urlConnection.setReadTimeout(5000);

            //setting the request method to get
            urlConnection.setRequestMethod("GET");

            //connecting to the api
            urlConnection.connect();

            if(urlConnection.getResponseCode() == 200) {
                newsJson = readFromStream(urlConnection);
            }
            else {
                Log.e("HelperJs.class","Error in response Code");
            }

        } catch (IOException e) {
            Log.e("HelperJs.class","Problem in making Https request",e);
        }
        return newsJson;
    }

    public static String modifyStandFirst(String sJson) {
       return sJson.substring(3,sJson.indexOf("</p>"));
    }


    //method to read the json data from the input stream
    public static String readFromStream(HttpURLConnection urlConnection) throws IOException {

        //input stream  to read from the connection
        InputStream inp = urlConnection.getInputStream();

        //inputstreamreader to read the binary from input stream and convert them into charset as defined
        InputStreamReader inpreader = new InputStreamReader(inp,"UTF-8");

        //buffered reader to read from the input stream reader line by line
        BufferedReader reader = new BufferedReader(inpreader);

        String json; //temporary string to read from the reader and to store in output
        StringBuilder output = new StringBuilder(); //the final output json from the reader

        json = reader.readLine();
        while(json != null) {
            output.append(json);
            json = reader.readLine();
        }
        return output.toString();
    }

}