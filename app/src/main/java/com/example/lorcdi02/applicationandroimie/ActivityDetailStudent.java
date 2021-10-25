package com.example.lorcdi02.applicationandroimie;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Base64;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.lorcdi02.applicationandroimie.Feeds.Student;
import com.example.lorcdi02.applicationandroimie.Tools.LoadStudentDataImage;

import java.util.concurrent.ExecutionException;


public class ActivityDetailStudent extends ActionBarActivity {

    Student currentStudent;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_activity_detail_student);
        
        GetStudentObject();
        LoadListenerClickDetailNote();
        
    }

    private void LoadListenerClickDetailNote() {
        Button button= (Button) findViewById(R.id.btnDetails);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loadDetailNote();
            }
        });
    }

    private void loadDetailNote() {
        Intent intent = new Intent(this, ActivityDetailNoteStudent.class);
        intent.putExtra("Student", currentStudent);
        startActivity(intent);
    }

    private void GetStudentObject() {
        Intent currentIntent= getIntent();
        Bundle bundle = currentIntent.getExtras();

        if(bundle!=null)
        {
            currentStudent =(Student) bundle.get("Student");
            try {
                LoadDetailStudent();
            } catch (ExecutionException e) {
                e.printStackTrace();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    private void LoadDetailStudent() throws ExecutionException, InterruptedException {
        //firstName
        TextView textFirstName = (TextView)findViewById(R.id.tvName);
        textFirstName.setText(currentStudent.getStudentFirstName());
        //lastName
        TextView textLastName = (TextView)findViewById(R.id.tvLastName);
        textLastName.setText(currentStudent.getStudentLastName());
        //age
        TextView textage = (TextView)findViewById(R.id.tvAge);
        textage.setText(currentStudent.getStudentAge().toString());
        //Mail
        TextView textMail = (TextView)findViewById(R.id.tvMail);
        textMail.setText(currentStudent.getStudentMail());
        //CurrentPromo
        TextView textCurrentPromo = (TextView)findViewById(R.id.tvPromoCurrent);
        textCurrentPromo.setText(currentStudent.getIdPromotion().getPromotionName());
        //OldPromo
        if (currentStudent.getIdOldPromotion() != null) {
            TextView textOldPromo = (TextView) findViewById(R.id.tvOldPromo);
            textOldPromo.setText(currentStudent.getIdOldPromotion().getPromotionName());
        }
        //Retard
        TextView textRetard = (TextView) findViewById(R.id.tvRetard);
        if (currentStudent.getStudentRetard() != null) {
            textRetard.setText(currentStudent.getStudentRetard().toString());
        }
        else{
            textRetard.setText("0");
        }
        //Telephone
        if (currentStudent.getStudentTelephonNumber() != null) {
            TextView textTelephone = (TextView) findViewById(R.id.tvTelephonNumber);
            textTelephone.setText(currentStudent.getStudentTelephonNumber());
        }
        //Commentary
        TextView textCommentary = (TextView) findViewById(R.id.tvCommentary);
        if(currentStudent.getStudentCommentary() != null){
            textCommentary.setText(currentStudent.getStudentCommentary());
        }else
        {
            textCommentary.setText("Pas de commentaire.");
        }
        //image
        String strimage = LoadStudentDataImage.loadImageStudent(currentStudent.getStudentId());
        if (strimage != null) {
            try {
                byte[] base64Decoded = Base64.decode(strimage, Base64.DEFAULT);
                ImageView image = (ImageView) findViewById(R.id.ivStudentImage);
                Bitmap bitmap = BitmapFactory.decodeByteArray(base64Decoded, 0, base64Decoded.length);
                image.setImageBitmap(bitmap);
            }
            catch(Exception e){

            }
        }

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_activity_detail_student, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
