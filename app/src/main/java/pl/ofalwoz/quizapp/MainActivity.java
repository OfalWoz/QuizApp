package pl.ofalwoz.quizapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toolbar;


import com.google.android.material.snackbar.Snackbar;

import java.text.DecimalFormat;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private final Question[] questions = new Question[]{
            new Question(R.string.question1, true),
            new Question(R.string.question2, false),
            new Question(R.string.question3, true)
    };

    Button butTrue, butFalse, butRestart, butCheat, butSearch;
    public static final String ANS = "pl.ofalwoz.quizapp.ANS";
    private TextView textView;
    private TextView summaryView;
    private TextView question;
    public static final int TEXT_REQUEST = 1;

    public int i = 0;
    private int points = 0;
    private int ansQuestCounter = 0;
    Boolean btnTrueBool, btnFalseBool;
    ArrayList<Integer> answeredQuestions = new ArrayList<>(questions.length);
    Snackbar infoGood, infoBad;
    DecimalFormat df = new DecimalFormat("#.##");


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textView = findViewById(R.id.textView);
        question = findViewById(R.id.question);
        question.setText(questions[i].getTextId());

        butTrue = (Button)findViewById(R.id.butTrue);
        butFalse = (Button)findViewById(R.id.butFalse);
        butRestart = (Button)findViewById(R.id.reset);
        butCheat = (Button)findViewById(R.id.cheat);
        butSearch = (Button)findViewById(R.id.search);

        btnTrueBool=Boolean.parseBoolean(butTrue.getText().toString());
        btnFalseBool=Boolean.parseBoolean(butFalse.getText().toString());

        summaryView = findViewById(R.id.textViewSummary);

        if(savedInstanceState != null){
            i = savedInstanceState.getInt("counter_state");
            points = savedInstanceState.getInt("points_state");
            ansQuestCounter = savedInstanceState.getInt("ans_counter");
            answeredQuestions = savedInstanceState.getIntegerArrayList("ans_array_state");
        }
    }

    public void Check() {
        if(answeredQuestions.contains(i)){
            butTrue.setEnabled(false);
            butFalse.setEnabled(false);
            butCheat.setVisibility(View.GONE);
            butSearch.setVisibility(View.GONE);
            ; }
        else{
            butTrue.setEnabled(true);
            butFalse.setEnabled(true);
            butCheat.setVisibility(View.VISIBLE);
            butSearch.setVisibility(View.VISIBLE);
                ; }
    }

    public void backActivity(View view) {
        if (i<=0)
            i = 2;
        else
            i--;
        question.setText(questions[i].getTextId());

        Check();
    }

    public void nextActivity(View view) {
        if(i<2)
            i++;
        else
            i = 0;
        question.setText(questions[i].getTextId());

        Check();
    }
    @SuppressLint("NonConstantResourceId")
    public void submitAnswer(View view) {
        if(answeredQuestions.contains(i)){ }
        else answeredQuestions.add(i);
        infoGood= Snackbar.make(textView, "Nice", Snackbar.LENGTH_SHORT);
        infoBad = Snackbar.make(textView, "Wrong", Snackbar.LENGTH_SHORT);
        switch (view.getId()){

            case R.id.butTrue:
                ansQuestCounter++;
                butTrue.setEnabled(false);
                butFalse.setEnabled(false);
                butCheat.setVisibility(View.GONE);
                butSearch.setVisibility(View.GONE);
                butRestart.setEnabled(true);
                if(questions[i].isAnswer() == btnTrueBool) {
                    points++;
                    infoGood.show();
                }
                else infoBad.show();
                summary();
                break;

            case R.id.butFalse:
                ansQuestCounter++;
                butTrue.setEnabled(false);
                butFalse.setEnabled(false);
                butCheat.setVisibility(View.GONE);
                butSearch.setVisibility(View.GONE);
                butRestart.setEnabled(true);
                if(questions[i].isAnswer() == btnFalseBool) {
                    points++;
                    infoGood.show();
                }else infoBad.show();
                summary();
                break;
        }
    }
    public void summary() {
        if(ansQuestCounter == questions.length){
            butRestart.setEnabled(true);
            summaryView.setVisibility(View.VISIBLE);
            summaryView.setText("Total points:" + points + "\n" + "Correct Answers: " + points +"\n" + "Incorrect Answers: " + (questions.length - points) +"\n" + "Cheated Questions: " + SecondActivity.cheatCounter + "\n" + "Score: " + df.format(totalResult()));
        }
    }

    public double totalResult(){
        double x = SecondActivity.cheatCounter * 15;
        double pointsD = points;
        if((pointsD/questions.length * 100 - x) < 0 || x > 100 ){
            return 0;
        }
        else
            return (pointsD/questions.length * 100 - x);
    }

    public void startCheatActivity(View view){
        Intent intent = new Intent(this, SecondActivity.class);
        intent.putExtra(ANS ,Boolean.toString(questions[i].isAnswer()));
        startActivity(intent);
    }

    public void SearchAnswer(View view) {
        String questionSearch = question.getText().toString();
        startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://duckduckgo.com/?q=" + questionSearch)));
        SecondActivity.cheatCounter++;
    }

    public void restartQuiz(View view) {
        summaryView.setText("You lose points if you click on search.");
        question.setText(questions[i].getTextId());
        i = 0;
        points = 0;
        ansQuestCounter = 0;
        answeredQuestions.clear();
        butTrue.setEnabled(true);
        butFalse.setEnabled(true);
        butRestart.setEnabled(true);
        butCheat.setVisibility(View.VISIBLE);
        butSearch.setVisibility(View.VISIBLE);
        SecondActivity.cheatCounter = 0;

    }

    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt("counter_state", i);
        outState.putIntegerArrayList("ans_array_state", answeredQuestions);
        outState.putInt("points_state", points);
        outState.putInt("ans_counter", ansQuestCounter);

    }

    @Override
    protected void onRestoreInstanceState(@NonNull Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        question.setText(questions[i].getTextId());
        Check();
        summary();
    }
}