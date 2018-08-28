package android.example.com.keyboardsamples;

import android.app.Activity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements
        AdapterView.OnItemSelectedListener {

    private static final String TAG = MainActivity.class.getSimpleName();
    private String mSpinnerLabel = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.label_array, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        Spinner spinner = findViewById(R.id.label_spinner);
        if (spinner != null) {
            spinner.setOnItemSelectedListener(this);
            spinner.setAdapter(adapter);
        }
    }

    public void showText(View view) {
        EditText editText = null;
        String msgType = "";

        int currView = view.getId();
        if (currView == R.id.button_pass) {
            editText = findViewById(R.id.editText_pass);
            msgType = "password: ";
        } else if (currView == R.id.button_caps) {
            editText = findViewById(R.id.editText_caps);
            msgType = "msg: ";
        } else if (currView == R.id.button_email) {
            editText = findViewById(R.id.editText_email);
            msgType = "email: ";
        } else if (currView == R.id.label_spinner) {
            ((TextView) findViewById(R.id.text_phone_content))
                .setText(((EditText) findViewById(R.id.editText_phone))
                        .getText().toString());
            msgType = mSpinnerLabel + ": ";
        }

        if (editText != null) {
            String txt = msgType + editText.getText().toString();
            Toast.makeText(this, txt, Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        Log.d(TAG, "onItemSelected: ");
        mSpinnerLabel = parent.getItemAtPosition(position).toString();
        showText(findViewById(R.id.label_spinner));
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {
        Log.d(TAG, "onNothingSelected: ");
    }
}
