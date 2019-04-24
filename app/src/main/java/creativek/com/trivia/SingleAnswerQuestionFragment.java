package creativek.com.trivia;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import creativek.com.trivia.Model.TestStructure;

public class SingleAnswerQuestionFragment extends Fragment implements FragmentEssentials {

    // This fragment is for displaying multiple choice questions


    private TestStructure testStructure = null;
    private static int RESPONSE_COUNT = 1;
    RadioGroup radioGroup = null;
    RadioButton radioButton = null;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_single_answer_question, container, false);

        // Retrieving and Setting text for Question.

        TextView question = view.findViewById(R.id.single_answer_question);
        question.setText(testStructure.getQuestion(RESPONSE_COUNT));

        // Retrieving and Setting text for the options
        radioGroup = view.findViewById(R.id.single_answer_group);
        for (int i = 0; i < radioGroup.getChildCount(); i++) {
            radioButton = (RadioButton) radioGroup.getChildAt(i);
            radioButton.setText(TestStructure.getOption(RESPONSE_COUNT, i));
        }


        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
    }

    @Override
    public void setTestStructure(TestStructure testStructure) {
        this.testStructure = testStructure;
    }

    @Override
    public TestStructure getTestStructure() {
        return testStructure;
    }

    @Override
    public TestStructure submit() {

        if (Validation.validate(radioGroup, true)) {
            RadioButton radioButton = getView().findViewById(radioGroup.getCheckedRadioButtonId());
            testStructure.addResponse(radioButton.getText().toString(), RESPONSE_COUNT);
            return testStructure;
        }
        return null;
    }
}
