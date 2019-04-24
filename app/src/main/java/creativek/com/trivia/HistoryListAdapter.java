package creativek.com.trivia;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import creativek.com.trivia.Model.TestStructure;


public class HistoryListAdapter extends ArrayAdapter<TestStructure> {

    // This adapter is used to populate history item in history ListView.

    public int resource = -1;
    ArrayList<TestStructure> testStructures = null;

    public HistoryListAdapter(Context context, int resource, List<TestStructure> objects) {
        super(context, resource, objects);
        this.resource = resource;
        this.testStructures = (ArrayList<TestStructure>) objects;
    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(resource, parent, false);
        }

        TestStructure testStructure = testStructures.get(position);

        TextView textView = null;

        textView = convertView.findViewById(R.id.item_display_name);
        textView.setText("Name: " + testStructure.getResponse(0));

        textView = convertView.findViewById(R.id.item_display_q1);
        textView.setText(TestStructure.getQuestion(1));

        textView = convertView.findViewById(R.id.item_display_a1);
        textView.setText("Answer: " + testStructure.getResponse(1));

        textView = convertView.findViewById(R.id.item_display_q2);
        textView.setText(TestStructure.getQuestion(2));

        textView = convertView.findViewById(R.id.item_display_a2);
        textView.setText("Answer: " + testStructure.getResponse(2));

        return convertView;
    }
}
