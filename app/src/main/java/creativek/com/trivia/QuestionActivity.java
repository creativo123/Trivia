package creativek.com.trivia;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import java.util.LinkedList;

import creativek.com.trivia.Model.TestStructure;


public class QuestionActivity extends AppCompatActivity {

    // This single activity is used to display all the fragments namely for prompting name, questions and the result

    LinkedList<Fragment> fragments = new LinkedList<>();

    public TestStructure testStructure = new TestStructure();
    public Fragment currentFragment = null;
    public FragmentEssentials fragmentEssentials = null;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.question_display_activity);

        // Adding fragment in the sequence they have to be displayed.

        fragments.push(new ResultFragment());
        fragments.push(new MultipleChoiceQuestionFragment());
        fragments.push(new SingleAnswerQuestionFragment());
        fragments.push(new NameFragment());

        loadFragment();

        Button next = findViewById(R.id.button_next_question);
        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                loadFragment();
            }
        });
    }

    private void loadFragment() {

        if (fragmentEssentials != null) {
            testStructure = fragmentEssentials.submit();
            if (testStructure == null) {
                fragments.push(currentFragment);
                return;
            }
        }

        if (fragments.isEmpty()) {
            return;
        }

        currentFragment = fragments.pop();

        fragmentEssentials = (FragmentEssentials) currentFragment; // Casting Fragment to FragmentEssentials so that we can use our functions associated with it
        fragmentEssentials.setTestStructure(testStructure);
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.question_fragment, (Fragment) fragmentEssentials);
        fragmentTransaction.commit();
    }
}
