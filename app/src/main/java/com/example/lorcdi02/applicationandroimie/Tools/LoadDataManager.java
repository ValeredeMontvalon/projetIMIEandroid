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
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

/**
 * Created by Latlanh on 01/08/2015.
 */
public class LoadDataManager {
    private static List<Student> listStudents;
    private static List<Promotion> listPromotions;

    public static List<Student> getListStudents() {

            loadListStudent();

        return listStudents;
    }

    public static List<Promotion> getListPromotions() {

            try {
                loadListPromotion();
            }catch (InterruptedException e) {
                e.printStackTrace();
            } catch (ExecutionException e) {
                e.printStackTrace();
            }

        return listPromotions;
    }

    private static LoadDataManager ourInstance = new LoadDataManager();

    public static LoadDataManager getInstance() {
        return ourInstance;
    }

    private LoadDataManager() {

    }

    public static List<Student> loadListStudent(){
        return listStudents;
    }

    public static List<Promotion> loadListPromotion() throws ExecutionException, InterruptedException {
        listPromotions = new ArrayList<Promotion>();
        listStudents = new ArrayList<Student>();

        HttpAsyncTask http = new HttpAsyncTask();
        http.execute("http://10.0.1.98:8080/ProjetImie/resources/promotion/dataPromotion");
        HttpAsyncTask http2 = new HttpAsyncTask();
        http2.execute("http://10.0.1.98:8080/ProjetImie/resources/students");
        http.get();
        http2.get();
        return listPromotions;
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
                result = convertInputStreamToString(inputStream);
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
        //JSONObject jsonObject = new JSONObject(result);
        // On récupère le tableau d'objets qui nous concernent
        JSONArray array = new JSONArray(result);
        // Pour tous les objets on récupère les infos
        for (int i = 0; i < array.length(); i++) {
            // On récupère un objet JSON du tableau

            JSONObject obj = new JSONObject(array.getString(i));
            if (!obj.has("promotionName")){
                Student s = new Student();
                JSONObject objPromotion = new JSONObject(obj.getString("idPromotion"));
                Promotion studentPromotion = new Promotion();
                studentPromotion.setPromotionName(objPromotion.getString("promotionName"));
                studentPromotion.setPromotionId(objPromotion.getInt("promotionId"));
                s.setIdPromotion(studentPromotion);
                //-----------------------------------------
                Promotion studentOldPromotion = new Promotion();
                if (obj.has("idOldPromotion")) {
                    JSONObject objOldPromotion = new JSONObject(obj.getString("idOldPromotion"));
                    studentOldPromotion.setPromotionName(objOldPromotion.getString("promotionName"));
                    studentOldPromotion.setPromotionId(objOldPromotion.getInt("promotionId"));
                }
                s.setIdOldPromotion(studentOldPromotion);
                //-----------------------------------------
                s.setStudentId(obj.getInt("studentId"));
                s.setStudentFirstName(obj.getString("studentFirstName"));
                s.setStudentLastName(obj.getString("studentLastName"));
                s.setStudentAge(obj.getInt("studentAge"));
                if (obj.has("studentCommentary")) {
                    s.setStudentCommentary(obj.getString("studentCommentary"));
                }
                if (obj.has("studentMail")) {
                    s.setStudentMail(obj.getString("studentMail"));
                }
                if (obj.has("studentRetard")) {
                    s.setStudentRetard(obj.getInt("studentRetard"));
                }
                if (obj.has("studentTelephonNumber")) {
                    s.setStudentTelephonNumber(obj.getString("studentTelephonNumber"));
                }
                listStudents.add(s);
            }
            else {
                Promotion p = new Promotion();
                p.setPromotionName(obj.getString("promotionName"));
                p.setPromotionId(obj.getInt("promotionId"));
                listPromotions.add(p);
            }
        }
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
