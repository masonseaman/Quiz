package software.mason.quiz;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.support.v4.text.TextUtilsCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import android.widget.ToggleButton;

public class TrueFalse extends AppCompatActivity {
    Boolean isTrue = false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_true_false);

        final EditText questionText = (EditText) findViewById(R.id.questionTextTrueFalse);

        final ToggleButton true_false = (ToggleButton) findViewById(R.id.trueButton);
        true_false.setText("False");
        true_false.setTextOff("False");
        true_false.setTextOn("True");

        final DatabaseOpenHelper myDbHelper = new DatabaseOpenHelper(this);

        Button addQuestion = (Button) findViewById(R.id.button);
        addQuestion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(TextUtils.isEmpty(questionText.getText().toString())){
                    Toast.makeText(getApplicationContext(),"Cannot make an empty question",Toast.LENGTH_SHORT).show();
                }
                else {
                    if(true_false.getText().toString().equals("True"))
                        isTrue=true;
                    myDbHelper.insertQuestions("No Topic", "True/False", questionText.getText().toString(), isTrue.toString());
                }
            }
        });
    }
}
