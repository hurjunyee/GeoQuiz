package com.daou.geoquiz;

/**
 * Created by Administrator on 2016-06-01.
 */
public class TrueFalse {
    private int mQuestion;

    private boolean mTrueQuestion;

    public int getQuestion() {
        return mQuestion;
    }

    public boolean isTrueQuestion() {
        return mTrueQuestion;
    }

    public void setTrueQuestion(boolean trueQuestion) {
        mTrueQuestion = trueQuestion;
    }

    public void setQuestion(int question) {
        mQuestion = question;
    }

    public TrueFalse(int question, boolean trueQuestion) {
        mQuestion = question;
        mTrueQuestion = trueQuestion;

    }
}
