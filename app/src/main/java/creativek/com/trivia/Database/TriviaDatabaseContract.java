package creativek.com.trivia.Database;

import android.provider.BaseColumns;

public class TriviaDatabaseContract {

    public static class TriviaDatabase implements BaseColumns {
        public static final String TABLE_NAME = "trivia";
        public static final String COLUMN_NAME = "name";
        public static final String COLUMN_RESPONSE_1 = "response1";
        public static final String COLUMN_RESPONSE_2 = "response2";
        public static final String DATABASE_NAME = "trivia.db";

        public static final int DB_VERSION = 1;


        public static final String CREATE_TABLE = "CREATE TABLE " +
                TABLE_NAME + "(" + TriviaDatabase._ID + " INTEGER PRIMARY KEY, " + COLUMN_NAME + " TEXT, " + COLUMN_RESPONSE_1 + " TEXT," + COLUMN_RESPONSE_2 + " TEXT )";

        public static final String DROP_TABLE = "DROP TABLE " + TABLE_NAME;


    }
}
