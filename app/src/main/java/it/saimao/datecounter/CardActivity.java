package it.saimao.datecounter;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

public class CardActivity extends AppCompatActivity {

    int[] imgResources = {R.drawable.chan_chan, R.drawable.phyu_phyu_kyaw_thein, R.drawable.d_phyo};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_card);
    }

    public void cardItemClicked(View view) {
        Intent intent = new Intent(this, MainActivity.class); // from -> to
        startActivity(intent);
//        int id = view.getId();
//        if (id == R.id.cv_phyu_phyu) {
//            System.out.println("Phyu Phyu");
//        } else if (id == R.id.cv_dphyo) {
//            System.out.println("D Phyo");
//        } else if (id == R.id.cv_moe_moe) {
//
//        } else if (id == R.id.cv_rzarni) {
//
//        } else {
//            System.out.println("Chan Chan");
//        }

    }
}