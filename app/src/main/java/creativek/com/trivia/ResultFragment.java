package creativek.com.trivia;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import creativek.com.trivia.Model.TestStructure;


public class ResultFragment extends Fragment implements FragmentEssentials {

    // This fragment is used to display summary.

    private TestStructure testStructure = null;

    @Override
    public void setTestStructure(TestStructure testStructure) {
        this.testStructure = testStructure;
    }

    @Override
    public TestStructure getTestStructure() {
        return null;
    }

    @Override
    public TestStructure submit() {

        // Here our submit policy for next button is to start the quiz again.

        Intent intent = new Intent(getContext(), QuestionActivity.class);
        startActivity(intent);
        getActivity().finish();
        return null;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_result, container, false);

        TextView textView = null;
        textView = view.findViewById(R.id.name_holder);
        textView.setText("Hello " + testStructure.getResponse(0) + ",");


        textView = view.findViewById(R.id.question1);
        textView.setText(testStructure.getQuestion(1));


        textView = view.findViewById(R.id.response1);
        textView.setText("Answer: " + testStructure.getResponse(1));

        textView = view.findViewById(R.id.question2);
        textView.setText(testStructure.getQuestion(2));


        textView = view.findViewById(R.id.response2);
        textView.setText("Answer: " + testStructure.getResponse(2));


        Button history = view.findViewById(R.id.history_button);
        history.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getContext(), HistoryActivity.class);
                startActivity(intent);
                getActivity().finish();
            }
        });

        testStructure.write(getContext());

        return view;
    }


}
