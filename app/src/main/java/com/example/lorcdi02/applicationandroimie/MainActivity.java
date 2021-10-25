package com.example.lorcdi02.applicationandroimie;


import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;


import com.example.lorcdi02.applicationandroimie.Feeds.Promotion;
import com.example.lorcdi02.applicationandroimie.Tools.LoadDataManager;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    List<Promotion> listPromo = LoadDataManager.getListPromotions() ;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        LoadListPromo();
        SetListListener();
    }

    private void SetListListener() {
        ListView listPromo = (ListView)findViewById(R.id.listpromotion);
        listPromo.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> arg0, View view, int pos,
                                    long arg3) {

                LoadActivityListStudent(pos);
            }
        });
    }

    private void LoadActivityListStudent(int position){
        Intent intent = new Intent(this, ActivityListStudent.class);
        intent.putExtra("idPromotion", listPromo.get(position).getPromotionId());
        startActivity(intent);

    }
    private void LoadListPromo() {
        List<String> listPromotionName = new ArrayList<String>();
        for(Promotion p : listPromo){
            listPromotionName.add(p.getPromotionName());
        }

        ListView listPromo = (ListView)findViewById(R.id.listpromotion);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(
                this, R.layout.loadpromoxml,
                R.id.Itemname, listPromotionName);
        listPromo.setAdapter(adapter);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.

        switch (item.getItemId()) {
            case R.id.actualise:
                listPromo = new ArrayList<>();
                listPromo = LoadDataManager.getListPromotions() ;
               // LoadDataManager.getListPromotions();
                LoadListPromo();

                SetListListener();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }

    }

}
