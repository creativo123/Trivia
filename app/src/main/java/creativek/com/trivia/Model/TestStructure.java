package creativek.com.trivia.Model;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;

import creativek.com.trivia.Database.TriviaDatabaseContract;
import creativek.com.trivia.Database.TriviaDatabaseHelper;

public class TestStructure {

    /*

    This class is designed to structure the data and store the data to database.

     */


    public static ArrayList<String> questions = new ArrayList<>();

    // Since we have only two questions to display, we are preferring to store the questions in an Array List

    static {
        questions.add(0, "Your Full Name");
        questions.add(1, "Who is the best cricketer in the world?");
        questions.add(2, "What are the colors in the Indian national flag? Select all:");
    }

    public static ArrayList<ArrayList<String>> options = new ArrayList<>();

    // Storing options to ArrayList as we have only two questions display and they are static so that it will retain only one copy of the data.

    static {
        options.add(0, null);
        options.add(1, new ArrayList<String>() {
            {
                add("Sachin Tendulkar");
                add("Virat Kolli");
                add("Adam Gilchirst");
                add("Jacques Kallis");
            }
        });
        options.add(2, new ArrayList<String>() {
            {
                add("White");
                add("Yellow");
                add("Safron");
                add("Green");
            }
        });
    }

    // Constructor to store responses in to array.

    public TestStructure(String name, String Response1, String Response2) {
        ArrayList<String> responses = new ArrayList<>();
        responses.add(0, name);
        responses.add(1, Response1);
        responses.add(2, Response2);
        this.responses = responses;
    }

    // ArrayList to store response, this will be individually created for every instance of this class.

    private ArrayList<String> responses = new ArrayList() {
        {
            add("");
            add("");
            add("");
        }
    };

    public TestStructure() {

    }

    public void addResponse(String response, int i) {
        responses.add(i, response);
    }

    public static String getQuestion(int i) {
        return questions.get(i);
    }

    public ArrayList<String> getAllResponses() {
        return responses;
    }

    public String getResponse(int i) {
        return responses.get(i);
    }

    public static String getOption(int question_n, int option_n) {
        return options.get(question_n).get(option_n);
    }

    // Writes record to database

    public boolean write(Context context) {

        TriviaDatabaseHelper triviaDatabaseHelper = new TriviaDatabaseHelper(context, TriviaDatabaseContract.TriviaDatabase.DATABASE_NAME, null, TriviaDatabaseContract.TriviaDatabase.DB_VERSION);
        SQLiteDatabase sqLiteDatabase = triviaDatabaseHelper.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(TriviaDatabaseContract.TriviaDatabase.COLUMN_NAME, getResponse(0));
        contentValues.put(TriviaDatabaseContract.TriviaDatabase.COLUMN_RESPONSE_1, getResponse(1));
        contentValues.put(TriviaDatabaseContract.TriviaDatabase.COLUMN_RESPONSE_2, getResponse(2));
        long id = sqLiteDatabase.insert(TriviaDatabaseContract.TriviaDatabase.TABLE_NAME, null, contentValues);
        sqLiteDatabase.close();
        if (id > -1) {
            return true;
        } else {
            return false;
        }
    }

    // Fetches All record from the database in the descending order of thier id.

    public static ArrayList<TestStructure> fetchAll(Context context) {
        ArrayList<TestStructure> testStructures = new ArrayList<>();
        TriviaDatabaseHelper triviaDatabaseHelper = new TriviaDatabaseHelper(context, TriviaDatabaseContract.TriviaDatabase.DATABASE_NAME, null, TriviaDatabaseContract.TriviaDatabase.DB_VERSION);
        SQLiteDatabase sqLiteDatabase = triviaDatabaseHelper.getReadableDatabase();

        String[] Projection = {
                TriviaDatabaseContract.TriviaDatabase._ID,
                TriviaDatabaseContract.TriviaDatabase.COLUMN_NAME,
                TriviaDatabaseContract.TriviaDatabase.COLUMN_RESPONSE_1,
                TriviaDatabaseContract.TriviaDatabase.COLUMN_RESPONSE_2
        };


        String SortOrder = TriviaDatabaseContract.TriviaDatabase._ID + " DESC";


        Cursor cursor = sqLiteDatabase.query(TriviaDatabaseContract.TriviaDatabase.TABLE_NAME, Projection, null, null, null, null, SortOrder);


        while (cursor.moveToNext()) {
            TestStructure testStructure = new TestStructure(cursor.getString(cursor.getColumnIndexOrThrow(TriviaDatabaseContract.TriviaDatabase.COLUMN_NAME)), cursor.getString(cursor.getColumnIndexOrThrow(TriviaDatabaseContract.TriviaDatabase.COLUMN_RESPONSE_1)), cursor.getString(cursor.getColumnIndexOrThrow(TriviaDatabaseContract.TriviaDatabase.COLUMN_RESPONSE_2)));
            testStructures.add(testStructure);
        }
        sqLiteDatabase.close();
        return testStructures;
    }

}
