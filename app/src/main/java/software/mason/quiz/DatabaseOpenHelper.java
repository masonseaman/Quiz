package software.mason.quiz;

import android.content.ContentValues;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;


import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;

public class DatabaseOpenHelper extends SQLiteOpenHelper {
    final private static String CREATE_CMD =
            "CREATE TABLE artists (" + MainActivity._ID +
                    " INTEGER PRIMARY KEY AUTOINCREMENT, " + MainActivity.ARTIST_NAME + " TEXT NOT NULL)";

    final private static String NAME = "QUESTIONS";
    final private static Integer VERSION = 1;
    final private Context context;

    public static final String DATABASE_NAME = "QUESTIONS.db";
    public static final String TABLE_NAME = "questions";
    public static final String QUESTIONTYPE = "questionType";
    public static final String QUESTIONTEXT = "questionText";
    public static final String ANSWER = "questionAnswer";

    public DatabaseOpenHelper(Context context) {
        super(context, DATABASE_NAME, null, 1);
        this.context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(
                "create table questions " +
                        "(topic text, questionType text, questionText text, questionAnswer text)"
        );

    }

    public boolean insertQuestions(String topic, String type, String text, String answer){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("topic", topic);
        contentValues.put("questionType", type);
        contentValues.put("questionText", text);
        contentValues.put("questionAnswer", answer);
        db.insert("questions", null, contentValues);
        return true;
    }

    public void removeQuestions(String text){
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete("questions", QUESTIONTEXT + " = '" + text + "'", null);
    }


    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // TODO Auto-generated method stub

    }
    public ArrayList<String> getAll() {
        ArrayList<String> array_list = new ArrayList<String>();
        //hp = new HashMap();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res =  db.rawQuery( "select * from questions", null );
        res.moveToFirst();

        while(res.isAfterLast() == false){
            array_list.add(res.getString(res.getColumnIndex(QUESTIONTEXT)));
            res.moveToNext();
        }
        return array_list;
    }

    void deleteDatabase ( ) {
        context.deleteDatabase(NAME);
    }
}