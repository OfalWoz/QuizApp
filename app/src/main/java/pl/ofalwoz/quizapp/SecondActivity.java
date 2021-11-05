package pl.ofalwoz.quizapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class SecondActivity extends AppCompatActivity {

    public static String EXTRA_REPLY = "pl.ofalwoz.quziapp.sendbackresult.REPLY";
    private TextView textView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        textView = findViewById(R.id.points);

        Intent intent = getIntent();
        String message = intent.getStringExtra(MainActivity.Extra_MESSAGE);
        TextView textView = findViewById(R.id.points);
        textView.setText(message);
    }

    public void returnMessage(View view) {
        String reply = textView.getText().toString();
        Intent intent = new Intent();

        intent.putExtra(EXTRA_REPLY, reply);
        setResult(RESULT_OK, intent);
        finish();
    }
}