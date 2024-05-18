package it.saimao.datecounter;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;

import androidx.appcompat.app.AppCompatActivity;

import it.saimao.datecounter.databinding.ActivityCardBinding;

public class CardActivity extends AppCompatActivity {

    private ActivityCardBinding binding;
    int[] imgResources = {R.drawable.chan_chan, R.drawable.phyu_phyu_kyaw_thein, R.drawable.d_phyo};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_card);
        LinearLayout ll = findViewById(R.id.lyLayout);
    }

    public void cardItemClicked(View view) {
        Intent intent = new Intent(this, MainActivity.class); // from -> to
        startActivity(intent);
    }
}