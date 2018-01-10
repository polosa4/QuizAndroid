package com.example.oleg.quiz;

import android.content.Intent;
import android.nfc.Tag;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

public class QuizActivity extends AppCompatActivity {

    private int Counter;
    private static final String TAG = "QuizActivity";
    private Button mTrueButton;
    private Button mFalseButton;
    private Button mCheatButton;
    private ImageButton mNextButton;
    private ImageButton mPrevButton;
    private TextView mQuestionTextView;
    private Question[] mQuestionBank = new Question[]{
            new Question(R.string.question_australia,
                    true),
            new Question(R.string.question_oceans, true),
            new Question(R.string.question_mideast, false),
            new Question(R.string.question_africa, false),
            new Question(R.string.question_americas, true),
            new Question(R.string.question_asia, true),
    };
    private int mCurrentIndex = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d(TAG, "onCreate(Bundle) called");
        setContentView(R.layout.activity_quiz);

        mQuestionTextView = (TextView)findViewById(R.id.question_text_view);
//        int question = mQuestionBank[mCurrentIndex].getTextResId();
//        mQuestionTextView.setText(question);
        mTrueButton = (Button)findViewById(R.id.true_button);
        mTrueButton.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v) {
                checkAnswer(true);
                mFalseButton.setEnabled(false);
                mTrueButton.setEnabled(false);
//                Toast t1 = Toast.makeText(QuizActivity.this, R.string.correct_toast, Toast.LENGTH_SHORT);
//                t1.setGravity(Gravity.TOP|Gravity.CENTER_HORIZONTAL,0,250);
//                t1.show();
                // Does nothing yet, but soon!
            }
        });
        mFalseButton = (Button)findViewById(R.id.false_button);
        mFalseButton.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v) {
                checkAnswer(false);
                mFalseButton.setEnabled(false);
                mTrueButton.setEnabled(false);

//                Toast t1 = Toast.makeText(QuizActivity.this, R.string.incorrect_toast, Toast.LENGTH_SHORT);
//                t1.setGravity(Gravity.TOP|Gravity.CENTER_HORIZONTAL,0,250);
//                t1.show();
                // Does nothing yet, but soon!
            }
        });

        mNextButton = (ImageButton)findViewById(R.id.next_button);
        mNextButton.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v) {
                mCurrentIndex = (mCurrentIndex + 1) % mQuestionBank.length;
                updateQuestion();
                getScore();
            }

        });

        mPrevButton = (ImageButton)findViewById(R.id.prev_button);
        mPrevButton.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v) {
                if (mCurrentIndex > 0) {
                    mCurrentIndex = (mCurrentIndex - 1) % mQuestionBank.length;
                    updateQuestion();
                    getScore();
                }

            }

        });

        mCheatButton = (Button)findViewById(R.id.cheat_button);
        mCheatButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(QuizActivity.this, CheatActivity.class);
                startActivity(intent);
            }
        });

        updateQuestion();
    }

    @Override
    public void onStart() {
        super.onStart();
        Log.d(TAG, "onStart() called");
    }
    @Override
    public void onResume() {
        super.onResume();
        Log.d(TAG, "onResume() called");
    }
    @Override
    public void onPause() {
        super.onPause();
        Log.d(TAG, "onPause() called");
    }
    @Override
    public void onStop() {
        super.onStop();
        Log.d(TAG, "onStop() called");
    }
    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.d(TAG, "onDestroy() called");
    }

    private void getScore() {
        if (mCurrentIndex == 5){
            Toast toast = Toast.makeText(this, "Your score is: " +Integer.toString(Counter), Toast.LENGTH_SHORT);
            toast.setGravity(Gravity.TOP|Gravity.CENTER_HORIZONTAL, 0, 200);
            toast.show();
        }
    }


    private void updateQuestion() {
        int question = mQuestionBank[mCurrentIndex].getTextResId();
        mQuestionTextView.setText(question);
        mFalseButton.setEnabled(true);
        mTrueButton.setEnabled(true);

    }

    private void checkAnswer(boolean userPressedTrue)
    {
        boolean answerIsTrue =
                mQuestionBank[mCurrentIndex].isAnswerTrue();
        int messageResId = 0;
        if (userPressedTrue == answerIsTrue) {
            messageResId = R.string.correct_toast;
            Counter++;
        }
        else {
            messageResId = R.string.incorrect_toast;
        }
        Toast.makeText(this, messageResId,
                Toast.LENGTH_SHORT)
                .show();
    }
}
