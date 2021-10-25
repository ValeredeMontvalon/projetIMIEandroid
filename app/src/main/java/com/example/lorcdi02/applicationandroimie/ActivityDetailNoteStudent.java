package com.example.lorcdi02.applicationandroimie;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import com.example.lorcdi02.applicationandroimie.Feeds.Note;
import com.example.lorcdi02.applicationandroimie.Feeds.Student;
import com.example.lorcdi02.applicationandroimie.Tools.LoadNoteStudentData;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;


public class ActivityDetailNoteStudent extends ActionBarActivity {
    private Student currentStudent;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_activity_detail_note_student);
        GetStudentObject();
        try {
            loadListNote();
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
    private void GetStudentObject() {
        Intent currentIntent= getIntent();
        Bundle bundle = currentIntent.getExtras();

        if(bundle!=null)
        {
            currentStudent =(Student) bundle.get("Student");

        }
    }
    private void loadListNote() throws ExecutionException, InterruptedException {
        LoadNoteStudentData loadNoteStudentData = LoadNoteStudentData.getInstance();
        List<Note> listNote = new ArrayList<>();
        listNote = loadNoteStudentData.loadNote(currentStudent.getStudentId());
        // données du tableau
        String[] col1 = new String[listNote.size()];
        String[] col2= new String[listNote.size()];
        String[] col3= new String[listNote.size()];
        int idNote = 0;
        for(Note note : listNote){
            col1[idNote] = note.getEcfName();
            col2[idNote]=String.valueOf(note.getNoteValue());
            col3[idNote]=note.getCommentary();
            idNote = idNote+1;
        }
        TableLayout table = (TableLayout) findViewById(R.id.idTable); // on prend le tableau défini dans le layout
        TableRow row; // création d'un élément : ligne
        TextView tv1,tv2,tv3; // création des cellules
        // pour chaque ligne
        for(int i=0;i<col1.length;i++) {
            row = new TableRow(this); // création d'une nouvelle ligne

            tv1 = new TextView(this); // création cellule
            tv1.setText(col1[i]); // ajout du texte
            tv1.setGravity(Gravity.CENTER); // centrage dans la cellule
            // adaptation de la largeur de colonne à l'écran :
            tv1.setLayoutParams( new TableRow.LayoutParams( 0, android.view.ViewGroup.LayoutParams.WRAP_CONTENT, 1 ) );

            // idem 2ème cellule
            tv2 = new TextView(this);
            tv2.setText(col2[i]);
            tv2.setGravity(Gravity.CENTER);
            tv2.setLayoutParams( new TableRow.LayoutParams( 0, android.view.ViewGroup.LayoutParams.WRAP_CONTENT, 1 ) );

            // idem 2ème cellule
            tv3 = new TextView(this);
            tv3.setText(col3[i]);
            tv3.setGravity(Gravity.CENTER);
            tv3.setLayoutParams( new TableRow.LayoutParams( 0, android.view.ViewGroup.LayoutParams.WRAP_CONTENT, 1 ) );

            // ajout des cellules à la ligne
            row.addView(tv1);
            row.addView(tv2);
            row.addView(tv3);
            // ajout de la ligne au tableau
            table.addView(row);
        }
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_activity_detail_note_student, menu);
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
