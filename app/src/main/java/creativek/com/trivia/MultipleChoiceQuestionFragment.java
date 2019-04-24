package creativek.com.trivia;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.RadioGroup;
import android.widget.TextView;

import creativek.com.trivia.Model.TestStructure;

public class MultipleChoiceQuestionFragment extends Fragment implements FragmentEssentials {

    // This is fragment is for displaying multiple choice questions

    public TestStructure testStructure = null;
    private static int RESPONSE_COUNT = 2;

    private RadioGroup radioGroup = null;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_multiple_choice_question, container, false);

        TextView question = view.findViewById(R.id.multiple_answer_question);

        // This for setting question.
        question.setText(testStructure.getQuestion(RESPONSE_COUNT));

        radioGroup = view.findViewById(R.id.multiple_answer_group);
        CheckBox checkBox = null;

        // Setting the text for checkbox.
        for (int i = 0; i < radioGroup.getChildCount(); i++) {
            checkBox = (CheckBox) radioGroup.getChildAt(i);
            checkBox.setText(testStructure.getOption(RESPONSE_COUNT, i));
        }


        return view;
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
        // This function will be called from onclick listener of next button from QuestionActivity.

        //The below block of code will validate the check box whether atleast one checkbox is selected or not.

        if (Validation.validate(radioGroup, true, true)) {
            String response = "";
            for (int i = 0; i < radioGroup.getChildCount(); i++) {
                CheckBox checkBox = (CheckBox) radioGroup.getChildAt(i);

                if (checkBox.isChecked()) {
                    response = response + checkBox.getText();

                    if (i != radioGroup.getChildCount()) {
                        response += ",";
                    }
                }
            }
            //
            testStructure.addResponse(response, RESPONSE_COUNT);
            return testStructure;
        }
        // If no options are selected it will return null.
        return null;
    }
}
