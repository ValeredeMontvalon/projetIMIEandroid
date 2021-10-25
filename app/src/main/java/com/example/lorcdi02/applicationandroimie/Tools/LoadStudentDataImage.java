package com.example.lorcdi02.applicationandroimie.Tools;

import android.os.AsyncTask;
import android.util.Log;

import com.example.lorcdi02.applicationandroimie.Feeds.Promotion;
import com.example.lorcdi02.applicationandroimie.Feeds.Student;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.List;
import java.util.concurrent.ExecutionException;


/**
 * Created by lor.cdi02 on 21/08/2015.
 */
public class LoadStudentDataImage {
    private static LoadStudentDataImage ourInstance = new LoadStudentDataImage();
    private static String currentImage;
    public static LoadStudentDataImage getInstance() {
        return ourInstance;
    }

    private LoadStudentDataImage() {
    }

    public static String loadImageStudent(int IdStudent) throws ExecutionException, InterruptedException {


        HttpAsyncTask http = new HttpAsyncTask();
        http.execute("http://10.0.1.98:8080/ProjetImie/resources/students/imageStudent/"+IdStudent);
        http.get();
        return currentImage;
    }

    public static String GET(String url){
        InputStream inputStream = null;
        String result = "";
        try {

            // create HttpClient
            HttpClient httpclient = new DefaultHttpClient();

            // make GET request to the given URL
            HttpResponse httpResponse = httpclient.execute(new HttpGet(url));

            // receive response as inputStream
            inputStream = httpResponse.getEntity().getContent();

            // convert inputstream to string
            if(inputStream != null)
                currentImage = convertInputStreamToString(inputStream);
            else
                result = "Did not work!";

        } catch (Exception e) {
            Log.d("InputStream", e.getLocalizedMessage());
        }

        return result;
    }

    private static String convertInputStreamToString(InputStream inputStream) throws IOException, JSONException {
        BufferedReader bufferedReader = new BufferedReader( new InputStreamReader(inputStream));
        String line = "";
        String result = "";

        while((line = bufferedReader.readLine()) != null)
            result += line;

        inputStream.close();

        return result;

    }

    private static class HttpAsyncTask extends AsyncTask<String, Void, String> {
        @Override
        protected String doInBackground(String... urls) {

            return GET(urls[0]);
        }
        // onPostExecute displays the results of the AsyncTask.
        @Override
        protected void onPostExecute(String result) {

        }

    }
}
