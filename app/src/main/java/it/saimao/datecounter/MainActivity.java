package it.saimao.datecounter;

import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import it.saimao.datecounter.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private ActivityMainBinding binding;
    int[] imgResources = {R.drawable.athen_cho_swe, R.drawable.chan_chan, R.drawable.d_phyo};
    String[] names = {"အေသင်ချိုဆို", "ချမ်းချမ်း", "ဒီဖြိုး"};
    int imgIndex = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        initUi();
        Log.d("TEST", "" + R.drawable.athen_cho_swe);
        initListeners();
    }

    private void initListeners() {
        binding.btNext.setOnClickListener(this);
        binding.btPrevious.setOnClickListener(this);
    }

    /*
    camelCase -
    snake_case -
     */
    private void initUi() {
        binding.imageView.setImageResource(imgResources[imgIndex]);
    }

    /*
    View.INVISIBLE
    View.GONE
    View.VISIBLE
     */
    @Override
    public void onClick(View v) {
        binding.piLoading.setVisibility(View.VISIBLE);
        if (v.getId() == R.id.btNext) {
            if (imgIndex == imgResources.length - 1) {
                imgIndex = 0;
            } else {
                imgIndex++;
            }
        } else {
            if (imgIndex == 0) {
                imgIndex = imgResources.length - 1;
            } else {
                imgIndex--;
            }
        }
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                binding.piLoading.setVisibility(View.INVISIBLE);
                binding.imageView.setImageResource(imgResources[imgIndex]);
                binding.tvName.setText(names[imgIndex]);
            }
        }, 5000); // 1000 milli-seconds - 1 second
    }
}













