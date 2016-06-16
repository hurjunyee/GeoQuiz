package com.daou.geoquiz;


import android.app.Activity;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class CheatActivity extends Activity {

	public static final String EXTRA_ANSWER_IS_TRUE = "com.daou.geoquiz.answer_is_true";
	public static final String EXTRA_ANSWER_SHOWN = "com.daou.geoquiz.answer_shown";

	private static final String KEY_SHOWN = "shown";
	
	private TextView mAnswerTextView;
	private Button mShowAnswer;
	
	private TextView mAPILevel;
	
	private boolean mAnswerIsTrue;
	private boolean mIsAnswerShown;
	
	private void setAnswerShownResult(boolean isAnswerShown) {
		Intent data = new Intent();
		data.putExtra(EXTRA_ANSWER_SHOWN, isAnswerShown);
		setResult(RESULT_OK, data);
	}
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_cheat);
		
		Log.d("�� mIsAnswerShown", ""+ mIsAnswerShown);
		
		mAnswerIsTrue = getIntent().getBooleanExtra(EXTRA_ANSWER_IS_TRUE, false);
		mIsAnswerShown = getIntent().getBooleanExtra(EXTRA_ANSWER_SHOWN, false);
		
		if(savedInstanceState != null) {
			mIsAnswerShown = savedInstanceState.getBoolean(KEY_SHOWN);
		}
		
		mAnswerTextView = (TextView) findViewById(R.id.answerTextView);
		
		// ����ڰ� ��ư�� �����߸�
		// ������ �����ش�.
		setAnswerShownResult(mIsAnswerShown);
		
		mShowAnswer = (Button) findViewById(R.id.showAnswerButton);
		mShowAnswer.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				if(mAnswerIsTrue) {
					mAnswerTextView.setText(R.string.true_button);
				} else {
					mAnswerTextView.setText(R.string.false_button);
				}
				mIsAnswerShown = true;
				setAnswerShownResult(mIsAnswerShown);
			}
		});
		
		mAPILevel = (TextView) findViewById(R.id.txtAPILevel);
		mAPILevel.setText("API ���� " + Build.VERSION.SDK_INT);
	}
	

	@Override
	protected void onSaveInstanceState(Bundle outState) {
		// TODO Auto-generated method stub
		super.onSaveInstanceState(outState);
		outState.putBoolean(KEY_SHOWN, mIsAnswerShown);
	}
}
