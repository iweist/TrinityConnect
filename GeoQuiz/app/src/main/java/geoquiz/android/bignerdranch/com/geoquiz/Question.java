package geoquiz.android.bignerdranch.com.geoquiz;

/**
 * Created by owner on 9/23/2015.
 */
public class Question {

    private int mTextResId;
    private boolean mAnswerTrue;
    private boolean mHasCheated;

    public Question(int textResId, boolean answerTrue) {
        mTextResId = textResId;
        mAnswerTrue = answerTrue;

    }

    public int getTextResId() {
        return mTextResId;
    }

    public void setTextResId(int textResId) {
        mTextResId = textResId;
    }

    public boolean isAnswerTrue() {
        return mAnswerTrue;
    }

    public boolean isHasCheated(){
        return mHasCheated;
    }

    public void setHasCheated(boolean value){
        mHasCheated = value;
    }

    public void setAnswerTrue(boolean answerTrue) {
        mAnswerTrue = answerTrue;
    }
}