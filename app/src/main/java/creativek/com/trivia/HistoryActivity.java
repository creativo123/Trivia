package creativek.com.trivia;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

import creativek.com.trivia.Model.TestStructure;

public class HistoryActivity extends AppCompatActivity {

    // This activity is to call fetchAll() function and populate the list view with all the records from the database;

    ListView listView = null;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history);

        listView = findViewById(R.id.history);

        HistoryListAdapter historyListAdapter = new HistoryListAdapter(getApplicationContext(), R.layout.item_history, TestStructure.fetchAll(getApplicationContext()));

        listView.setAdapter(historyListAdapter);

        Button next = findViewById(R.id.button_next_history);
        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(HistoryActivity.this, QuestionActivity.class);
                startActivity(intent);
                finish();
            }
        });


    }
}
