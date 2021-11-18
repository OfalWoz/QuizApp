package pl.ofalwoz.quizapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class SecondActivity extends AppCompatActivity {

    //public static String EXTRA_REPLY = "pl.ofalwoz.quziapp.sendbackresult.REPLY";
    private TextView textViewAnswer;
    private Button butShowCheat;
    private  String answer;
    public static int cheatCounter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
        textViewAnswer = findViewById(R.id.answer);
        butShowCheat = (Button)findViewById(R.id.showCheat);
        answer = getIntent().getStringExtra(MainActivity.ANS);

        if(savedInstanceState != null) {
            textViewAnswer.setText(savedInstanceState.getString("showAnswer_state"));
            butShowCheat.setEnabled(savedInstanceState.getBoolean("butCheat_state"));
        }
    }

    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);

        outState.putString("showAnswer_state", textViewAnswer.getText().toString());
        outState.putBoolean("butCheat_state", butShowCheat.isEnabled());
        outState.putString("showAnswer_state", textViewAnswer.getText().toString());
    }

    public void showCorrectAnswer(View view) {
        textViewAnswer.setText(answer);
        butShowCheat.setEnabled(false);
        cheatCounter++;
    }


}