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

    public static final String Extra_MESSAGE = "pl.ofalwoz.quizapp.MESSAGE";
    private TextView textView;
    private TextView test;
    public static final int TEXT_REQUEST = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textView = findViewById(R.id.points);
        test = findViewById(R.id.textView);
    }

    public void backActivity(View view) {
    }

    public void nextActivity(View view) {
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