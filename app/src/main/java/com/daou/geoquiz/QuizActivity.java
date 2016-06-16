package com.daou.geoquiz;

import android.annotation.TargetApi;
import android.app.ActionBar;
import android.app.Activity;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

public class QuizActivity extends Activity {
	private static final String TAG = "QuizActivity";
    private static final String KEY_INDEX = "index";
	private static final String KEY_SHOWN = "shown";
	private static final String KEY_SHOWNARRAY = "shownarray";

    private Button mTrueButton;
    private Button mFalseButton;
    private ImageButton mNextButton;
    private ImageButton mPrevButton;
    private TextView mQuestionTextView;
    
    private Button mCheatButton;

    private int mCurrentIndex = 0;

    private TrueFalse[] mQuestionBank = new TrueFalse[] {
            new TrueFalse(R.string.question_oceans, true),
            new TrueFalse(R.string.question_mideast, false),
            new TrueFalse(R.string.question_africa, false),
            new TrueFalse(R.string.question_americas, true),
            new TrueFalse(R.string.question_asia, true),
    };
    
    private boolean[] mCheaterArray = new boolean[mQuestionBank.length];

    @Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		if( data == null) {
			return;
		}
		
		Log.d("onActivityResult 디버깅", "" + data.getBooleanExtra(CheatActivity.EXTRA_ANSWER_SHOWN, false));
		mCheaterArray[mCurrentIndex] = data.getBooleanExtra(CheatActivity.EXTRA_ANSWER_SHOWN, false);
	}

    private void updateQuestion() {
        int question = mQuestionBank[mCurrentIndex].getQuestion();
        mQuestionTextView.setText(question);
    }

    private void checkAnswer(boolean userPressedTrue) {
        boolean answerIsTrue = mQuestionBank[mCurrentIndex].isTrueQuestion();

        int messageResId = 0;

        if(mCheaterArray[mCurrentIndex]) {
        	messageResId = R.string.judgment_toast;
        } else {
	        if(userPressedTrue == answerIsTrue) {
	            messageResId = R.string.correct_toast;
	        } else {
	            messageResId = R.string.incorrect_toast;
	        }
        }

        Toast.makeText(getApplicationContext(), messageResId, Toast.LENGTH_SHORT).show();
    }

    @TargetApi(11)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d(TAG, "onCreate(Bundle) called");
        setContentView(R.layout.activity_quiz);
        
        if( Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB) {
        	ActionBar actionBar = getActionBar();
            actionBar.setSubtitle("세계의 바다와 강과 호수");
        }

        if(savedInstanceState != null) {
            mCurrentIndex = savedInstanceState.getInt(KEY_INDEX, 0);
            mCheaterArray = savedInstanceState.getBooleanArray(KEY_SHOWNARRAY);
        }

        mQuestionTextView = (TextView) findViewById(R.id.question_text_view);
        mQuestionTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                mCurrentIndex = (mCurrentIndex + 1) % mQuestionBank.length;
                updateQuestion();
            }
        });
        updateQuestion();

        mTrueButton = (Button) findViewById(R.id.btn_true);
        mTrueButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkAnswer(true);
            }
        });

        mFalseButton = (Button) findViewById(R.id.btn_false);
        mFalseButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkAnswer(false);
            }
        });

        mNextButton = (ImageButton) findViewById(R.id.next_button);
        mNextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mCurrentIndex = (mCurrentIndex + 1) % mQuestionBank.length;
                updateQuestion();
            }
        });

        mPrevButton = (ImageButton) findViewById(R.id.prev_button);
        mPrevButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mCurrentIndex = ((mCurrentIndex - 1) % mQuestionBank.length);
                if(mCurrentIndex < 0)
                    mCurrentIndex += mQuestionBank.length;
                updateQuestion();
            }
        });
        
        mCheatButton = (Button) findViewById(R.id.btn_cheat);
        mCheatButton.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				Intent i = new Intent(getApplicationContext(), CheatActivity.class);
				boolean answerIsTrue = mQuestionBank[mCurrentIndex].isTrueQuestion();
				i.putExtra(CheatActivity.EXTRA_ANSWER_IS_TRUE, answerIsTrue);
				i.putExtra(CheatActivity.EXTRA_ANSWER_SHOWN, mCheaterArray[mCurrentIndex]);
				startActivityForResult(i, 0);
			}
		});
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt(KEY_INDEX, mCurrentIndex);
        Log.d(TAG, ""+ mCheaterArray[mCurrentIndex]);
        outState.putBoolean(KEY_SHOWN, mCheaterArray[mCurrentIndex]);
        outState.putBooleanArray(KEY_SHOWNARRAY, mCheaterArray);
    }

    @Override
    protected void onStart() {
        super.onStart();
//        Log.d(TAG, "onStart() called");
    }

    @Override
    protected void onPause() {
        super.onPause();
//        Log.d(TAG, "onPause() called");
    }

    @Override
    protected void onResume() {
        super.onResume();
//        Log.d(TAG, "onResume() called");
    }

    @Override
    protected void onStop() {
        super.onStop();
//        Log.d(TAG, "onStop() called");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
//        Log.d(TAG, "onDestroy() called");
    }
}
