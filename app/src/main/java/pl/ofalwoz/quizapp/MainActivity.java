package pl.ofalwoz.quizapp;

import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private final Question[] questions = new Question[]{
            new Question(R.string.question1, true),
            new Question(R.string.question2, true),
            new Question(R.string.question3, true)
    };

    public static final String Extra_MESSAGE = "pl.ofalwoz.quizapp.MESSAGE";
    private TextView textView;
    private TextView test;
    private TextView question;
    public static final int TEXT_REQUEST = 1;

    public int i = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textView = findViewById(R.id.points);
        test = findViewById(R.id.textView);
        question = findViewById(R.id.question);
        question.setText(questions[i].getTextId());
    }

    public void backActivity(View view) {
        if (i<=0)
            i = 2;
        else
            i=i-1;
        question.setText(questions[i].getTextId());
    }

    public void nextActivity(View view) {
        if(i<2)
            i++;
        else
            i = 0;
        question.setText(questions[i].getTextId());
    }

    public void cheat(View view) {
        String message = textView.getText().toString();
        Intent intent = new Intent(this, SecondActivity.class);
        intent.putExtra(Extra_MESSAGE, message);
        //startActivity(intent);
        startActivityForResult(intent, TEXT_REQUEST);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data){
        super.onActivityResult(resultCode,resultCode,data);

        String reply = "";
        if (data != null) {
            reply = data.getStringExtra(SecondActivity.EXTRA_REPLY);
        }
        test.setText(reply);
    }
}