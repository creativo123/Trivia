package creativek.com.trivia;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import creativek.com.trivia.Model.TestStructure;

public class NameFragment extends Fragment implements FragmentEssentials {

    // This fragment is for prompting user to enter name.

    private static int RESPONSE_COUNT = 0;
    EditText name = null;
    public TestStructure testStructure = null;



    public void setTestStructure(TestStructure testStructure) {
        this.testStructure = testStructure;
    }

    public TestStructure getTestStructure() {
        return this.testStructure;
    }

    @Override
    public TestStructure submit() {
        // The below code is to validate the text such that it contains full name of a person e.g., Shreevin Sharma

        if (Validation.validate(name, true)) {
            testStructure.addResponse(name.getText().toString(), RESPONSE_COUNT);
            return testStructure;
        }
        return null;
    }


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_name, container, false);

        name = view.findViewById(R.id.name);
        name.setHint(testStructure.getQuestion(RESPONSE_COUNT));

        return view;
    }


}

