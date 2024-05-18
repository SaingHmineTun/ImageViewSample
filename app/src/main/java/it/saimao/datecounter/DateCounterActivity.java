package it.saimao.datecounter;

import android.app.AlarmManager;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import it.saimao.datecounter.databinding.ActivityDateCounterBinding;
import it.saimao.datecounter.databinding.DialogUserNameInputBinding;

public class DateCounterActivity extends AppCompatActivity {

    private ActivityDateCounterBinding binding;
    private LocalDate selectedDate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityDateCounterBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        initUi();
        initListeners();
    }

    private void initListeners() {
        binding.tvMale.setOnClickListener(v -> {
            showAlertDialog(v);
        });
        binding.tvFemale.setOnClickListener(v -> {
            showAlertDialog(v);
        });
    }

    private void showAlertDialog(View view) {
        // View vew = new TextView(this); // child to parent -> upcasting (implicit)
        TextView textView = (TextView) view; // parent to child -> downcasting (explicit)
        AlertDialog.Builder alertBuilder = new AlertDialog.Builder(this);

        DialogUserNameInputBinding dialogBinding = DialogUserNameInputBinding.inflate(getLayoutInflater());
        AlertDialog alertDialog = alertBuilder.setView(dialogBinding.getRoot()).create();
        alertDialog.show();

        class OnDialogClick implements View.OnClickListener {
            @Override
            public void onClick(View v) {
                textView.setText(dialogBinding.etName.getText().toString());
                alertDialog.cancel();
            }
        }
        dialogBinding.btCancel.setOnClickListener(v -> alertDialog.cancel());
        // ExoPlayer (Notification)
        dialogBinding.btConfirm.setOnClickListener(new OnDialogClick());

    }


    private void initUi() {
        // min - 26
        selectedDate = fromStringToLocalDate(binding.btDate.getText().toString());
        LocalDate todayDate = LocalDate.now(); // todayDate - selectedDate = dateBetween
        long dateBetween = todayDate.toEpochDay() - selectedDate.toEpochDay();
        String days = dateBetween > 1 ? dateBetween + " Days" : dateBetween + " Day";
        binding.tvDateBetween.setText(days);
    }

    private final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

    private String fromLocalDateToString(LocalDate localDate) {
        // 01/06/1996
        return formatter.format(localDate);
    }

    private LocalDate fromStringToLocalDate(String str) {
        return LocalDate.parse(str, formatter);
    }

}