package com.example.lorcdi02.applicationandroimie;

import android.content.Intent;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;


import com.example.lorcdi02.applicationandroimie.Feeds.Student;
import com.example.lorcdi02.applicationandroimie.Tools.LoadDataManager;

import java.util.ArrayList;
import java.util.List;

public class ActivityListStudent extends AppCompatActivity {
    List<Student> listStudents = LoadDataManager.getListStudents() ;
    List<Student> listStudentByIdPromotionAll;
    int idPromotionSelected=0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_activity_list_student);


        GetPromotionSelectedId();
        SetListListener();
    }

    private void SetListListener() {
        ListView listStudent = (ListView)findViewById(R.id.lvListStudent);
        listStudent.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> arg0, View view, int pos,
                                    long arg3) {

                LoadActivityDetailStudent(pos);
            }
        });
    }

    private void LoadActivityDetailStudent(int pos) {

        Intent intent = new Intent(this, ActivityDetailStudent.class);
        intent.putExtra("Student", listStudentByIdPromotionAll.get(pos));
        startActivity(intent);

    }

    private void GetPromotionSelectedId(){
        Intent currentIntent= getIntent();
        Bundle bundle = currentIntent.getExtras();

        if(bundle!=null)
        {
            idPromotionSelected =(int) bundle.get("idPromotion");
            LoadListStudentByIdPromotion();
        }
    }

    private void LoadListStudentByIdPromotion() {
        List<String> listStudentByIdPromotion = new ArrayList<>();
        listStudentByIdPromotionAll = new ArrayList<>();
        for (Student student : listStudents){
            if (student.getIdPromotion().getPromotionId() == idPromotionSelected){
                listStudentByIdPromotion.add(student.getStudentFirstName() +" "+ student.getStudentLastName());
                listStudentByIdPromotionAll.add(student);
            }
        }

        ListView listStudent = (ListView)findViewById(R.id.lvListStudent);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(
                this, R.layout.liststudentlayout,
                R.id.Itemname, listStudentByIdPromotion);
        listStudent.setAdapter(adapter);
        //Integer[] listImage = new Integer[10];
        //Drawable image = new BitmapDrawable(BitmapFactory.decodeByteArray(listStudentByIdPromotion.get(1).getStudentImage(), 0, listStudentByIdPromotion.get(1).getStudentImage().length));

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_activity_list_student, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        switch (item.getItemId()) {
            case R.id.actualise:
                listStudents = new ArrayList<>();
                listStudents = LoadDataManager.getListStudents() ;
                // LoadDataManager.getListPromotions();
                LoadListStudentByIdPromotion();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
