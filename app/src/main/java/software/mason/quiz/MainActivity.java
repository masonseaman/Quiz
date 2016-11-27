package software.mason.quiz;

import android.content.DialogInterface;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.preference.DialogPreference;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener{
    ArrayList topics = new ArrayList();
    String[] questionTypes = new String[]{"True/False","ShortAnswer"};
    String current = "";
    final static String TABLE_NAME = "QUESTIONS";
    final static String ARTIST_NAME = "name";
    final static String _ID = "_id";
    TextView selectedTopic;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        selectedTopic = (TextView) findViewById(R.id.textView2);
        Spinner spinner = (Spinner) findViewById(R.id.spinner);
        spinner.setOnItemSelectedListener(this);
        final ArrayList<String> topics = new ArrayList<String>();
        topics.add("Android");
        topics.add("Compilers");
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item,topics);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);

        final EditText newTopicName = (EditText) findViewById(R.id.newTopicName);

        Button takeQuiz = (Button) findViewById(R.id.takeTheQuiz);
        takeQuiz.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getBaseContext(), TakeQuiz.class);
                startActivity(intent);
            }
        });

        Button createAQuestion = (Button) findViewById(R.id.createAQuestion);
        createAQuestion.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               new AlertDialog.Builder(MainActivity.this)
                       .setTitle("What type of question do you want to create?")
                       .setItems(questionTypes, new DialogInterface.OnClickListener() {
                                   public void onClick(DialogInterface dialog, int which) {
                                       if(questionTypes[which].equals("True/False")){
                                           Intent tf = new Intent(getApplicationContext(),TrueFalse.class);
                                           startActivity(tf);
                                       }
                                       else {
                                           Intent sa = new Intent(getApplicationContext(),ShortAnswer.class);
                                           startActivity(sa);
                                       }

                                   }
                               }
                       )
                       .create()
                       .show();
           }
       });


                Button addTopic = (Button) findViewById(R.id.addTopicButton);
        addTopic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(TextUtils.isEmpty(newTopicName.getText().toString())){
                    Toast.makeText(getApplicationContext(),"New topic name cannot be empty",Toast.LENGTH_SHORT).show();
                }
                else {
                    topics.add(newTopicName.getText().toString());
                    newTopicName.setText("");
                }
            }
        });

        Button deleteTopic = (Button) findViewById(R.id.deleteTopic);
        deleteTopic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                System.out.println(current);
            }
        });
    }


    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        current = parent.getItemAtPosition(position).toString();
        selectedTopic.setText("Selected Topic: " + current);
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}
