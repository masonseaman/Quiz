package software.mason.quiz;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class TakeQuiz extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_take_quiz);
        final DatabaseOpenHelper myDbHelper = new DatabaseOpenHelper(this);

        ArrayList array_list = myDbHelper.getAll();
        ArrayAdapter arrayAdapter=new ArrayAdapter(this,android.R.layout.simple_list_item_1, array_list);

        final ListView list = (ListView)findViewById(R.id.listView);
        list.setAdapter(arrayAdapter);

        list.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, final int position, long id) {
                new AlertDialog.Builder(TakeQuiz.this)
                        .setTitle("Are you sure you want to remove this question?")
                        .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                myDbHelper.removeQuestions(list.getItemAtPosition(position).toString());
                                Intent intent = new Intent(getBaseContext(), MainActivity.class);
                                startActivity(intent);
                                Toast.makeText(getBaseContext(),"Question Deleted", Toast.LENGTH_SHORT).show();
                            }
                        })
                        .setNegativeButton("No", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.cancel();
                            }
                        })
                        .create()
                        .show();

                return true;
            }
        });

    }
}
