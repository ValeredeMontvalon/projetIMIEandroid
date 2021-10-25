package com.example.lorcdi02.applicationandroimie.Tools;

import android.os.AsyncTask;
import android.util.Log;

import com.example.lorcdi02.applicationandroimie.Feeds.Note;

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
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

/**
 * Created by lor.cdi02 on 24/08/2015.
 */
public class LoadNoteStudentData {
    private static List<Note> listNote;
    private static LoadNoteStudentData ourInstance = new LoadNoteStudentData();

    public static LoadNoteStudentData getInstance() {
        return ourInstance;
    }

    private LoadNoteStudentData() {
    }

    public static List<Note> loadNote(int IdStudent) throws ExecutionException, InterruptedException {
        HttpAsyncTask http = new HttpAsyncTask();
        http.execute("http://10.0.1.98:8080/ProjetImie/resources/note/"+IdStudent);
        http.get();
        return listNote;
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
                listNote = convertInputStreamToString(inputStream);
            else
                result = "Did not work!";

        } catch (Exception e) {
            Log.d("InputStream", e.getLocalizedMessage());
        }

        return result;
    }

    private static List<Note> convertInputStreamToString(InputStream inputStream) throws IOException, JSONException {
        BufferedReader bufferedReader = new BufferedReader( new InputStreamReader(inputStream));
        String line = "";
        String result = "";
        List<Note> listCurrentNote = new ArrayList<>();
        while((line = bufferedReader.readLine()) != null)
            result += line;

        inputStream.close();

        JSONArray array = new JSONArray(result);
        for (int i = 0; i < array.length(); i++) {
            // On récupère un objet JSON du tableau

                JSONObject obj = new JSONObject(array.getString(i));
                Note note = new Note();
                note.setNoteValue(obj.getInt("noteValue"));
                note.setIdNote(obj.getInt("noteId"));
                note.setCommentary(obj.getString("noteCommentary"));
                //-----------------------------------------
                JSONObject objEcf = new JSONObject(obj.getString("idEcf"));
                note.setEcfName(objEcf.getString("ecfName"));

                listCurrentNote.add(note);

        }
        return listCurrentNote;

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


