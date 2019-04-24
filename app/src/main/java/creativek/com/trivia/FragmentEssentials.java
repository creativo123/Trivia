package creativek.com.trivia;

import creativek.com.trivia.Model.TestStructure;

public interface FragmentEssentials {

    // This interface is to standardize the Fragments we are creating for displaying questions.

    public TestStructure testStructure = null;

    public void setTestStructure(TestStructure testStructure);
    //This function is used to pass TestStructure object to all the fragments.

    public TestStructure getTestStructure();

    public TestStructure submit();
    // This function is used to update the TestStructure object which is called from onclick listener of the next button from QuestionActivity.
    // This function also allows us to create policy for our fragments, means what it should do when the page is submitted out of the fragment.
}
