package in.connectitude.newsx.network;

import android.text.TextUtils;
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
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;

import in.connectitude.newsx.model.NewsSources;

public final class NewsSourcesUtils {


    public static final String LOG_TAG = NewsSources.class.getName();

    private NewsSourcesUtils() {

    }

    //to create url//
    private static URL createUrl(String stringUrl) {
        URL url = null;
        try {
            url = new URL(stringUrl);
        } catch (MalformedURLException e) {
            Log.e(LOG_TAG, "Error in creating url", e);
            e.printStackTrace();
        }
        return url;
    }


    public static List<NewsSources> fetchHeadlinesData(String requestUrl) {
        URL url = createUrl(requestUrl);
        String jsonResponse = null;
        List<NewsSources> newsReport;
        try {
            jsonResponse = makeHttpRequest(url);
        } catch (IOException e) {
            e.printStackTrace();
        }


        newsReport = extractNewsHeadlines(jsonResponse);


        return newsReport;
    }


    public static List<NewsSources> fetchSourcesData(String requestUrl) {
        URL url = createUrl(requestUrl);
        String jsonResponse = null;
        List<NewsSources> newsReport;
        try {
            jsonResponse = makeHttpRequest(url);
        } catch (IOException e) {
            e.printStackTrace();
        }


        // newsReport = extractNewsHeadlines(jsonResponse);

        newsReport = extractNewsSources(jsonResponse);
        return newsReport;
    }

    private static String makeHttpRequest(URL url) throws IOException {
        String jsonResponse = null;
        HttpURLConnection urlConnection = null;
        InputStream inputStream = null;
        try {
            urlConnection = (HttpURLConnection) url.openConnection();
            urlConnection.setRequestMethod("GET");
            urlConnection.setReadTimeout(10000);
            urlConnection.setConnectTimeout(50000);
            urlConnection.connect();
            int responseCode = urlConnection.getResponseCode();
            if (responseCode == 200) {
                inputStream = urlConnection.getInputStream();
                jsonResponse = readFromStream(inputStream);
            } else {
                Log.e(LOG_TAG, "Response Code" + responseCode);
            }
        } catch (IOException e) {
            Log.e(LOG_TAG, "Problem in retreiving", e);
        } finally {
            if (urlConnection != null) {
                urlConnection.disconnect();
            }
            if (inputStream != null) {
                inputStream.close();
            }
        }
        return jsonResponse;
    }

    private static String readFromStream(InputStream inputStream) throws IOException {
        StringBuilder output = new StringBuilder();
        if (inputStream != null) {
            InputStreamReader inputStreamReader = new InputStreamReader(inputStream, Charset.forName("UTF-8"));
            BufferedReader reader = new BufferedReader(inputStreamReader);
            String line = reader.readLine();
            while (line != null) {
                output.append(line);
                line = reader.readLine();
            }
        }
        return output.toString();
    }

    private static List<NewsSources> extractNewsSources(String movieJson) {
        if (TextUtils.isEmpty(movieJson)) {
            return null;
        }
        List<NewsSources>  newsSources = new ArrayList<NewsSources>();
        try {
            JSONObject root = new JSONObject(movieJson);
            JSONArray results = root.getJSONArray("sources");
            for (int i = 0; i < results.length(); i++) {
                JSONObject sourceData = results.getJSONObject(i);
                String name = sourceData.getString("name");
                String url = sourceData.getString("url");
                String category = sourceData.getString("category");
                String description = sourceData.getString("description");
                //newsSources.add(new NewsSources(name, url, category, description));
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return newsSources;
    }


    private static List<NewsSources> extractNewsHeadlines(String newsJson){

        if (TextUtils.isEmpty(newsJson)) {
            return null;
        }
        List<NewsSources>  newsSources = new ArrayList<NewsSources>();
        try {
            JSONObject root = new JSONObject(newsJson);
            JSONArray results = root.getJSONArray("articles");
            for (int i = 0; i < results.length(); i++) {
                JSONObject sourceData = results.getJSONObject(i);
                JSONObject nameObject = sourceData.getJSONObject("source");
                String name = nameObject.getString("name");
                String author = sourceData.getString("author");
                String title = sourceData.getString("title");
                String description = sourceData.getString("description");
                String imageUrl = sourceData.getString("urlToImage");
                String datePublished = sourceData.getString("publishedAt");
                String url = sourceData.getString("url");
                String content = sourceData.getString("content");
                newsSources.add(new NewsSources(name,author,title,description, url,imageUrl,datePublished,content));
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return newsSources;



    }


}
