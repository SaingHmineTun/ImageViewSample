package it.saimao.datecounter;

import android.app.DatePickerDialog;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.DatePicker;
import android.widget.TextView;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import it.saimao.datecounter.databinding.ActivityDateCounterBinding;
import it.saimao.datecounter.databinding.DialogUserNameInputBinding;

public class DateCounterActivity extends AppCompatActivity {

    private static final String MALE = "male"; // male -> Kyaw
    private static final String FEMALE = "female";
    private static final String SELECTED_DATE = "selected_date";
    private ActivityDateCounterBinding binding;
    private LocalDate selectedDate;

    private SharedPreferences sp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityDateCounterBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        initSharedPreference();
        initUi();
        initListeners();
    }

    private void initSharedPreference() {
        sp = getSharedPreferences("date_counter", MODE_PRIVATE);
    }

    private void initListeners() {
        binding.tvMale.setOnClickListener(v -> {
            showAlertDialog(v);
        });
        binding.tvFemale.setOnClickListener(v -> {
            showAlertDialog(v);
        });

        binding.btDate.setOnClickListener(v -> {
            showDatePickerDialog();
        });

    }

    private void showDatePickerDialog() {

        DatePickerDialog datePickerDialog = new DatePickerDialog(this);
        datePickerDialog.updateDate(selectedDate.getYear(), selectedDate.getMonthValue() - 1, selectedDate.getDayOfMonth());
        datePickerDialog.setOnDateSetListener((view, year, month, dayOfMonth) -> {
            Log.d("Date Picker", String.format("%d %d %d", year, month, dayOfMonth));
            selectedDate = LocalDate.of(year, month + 1, dayOfMonth);
            //
            Log.d("ABC", String.format("%02d/%02d/%04d", dayOfMonth, month + 1, year));
            saveData(SELECTED_DATE, String.format("%02d/%02d/%04d", dayOfMonth, month + 1, year));
            updateDate();
        });
        datePickerDialog.show();

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
                var name = dialogBinding.etName.getText().toString();
                if (textView.getId() == R.id.tv_male) {
                    saveData(MALE, name);
                } else {
                    // Female
                    saveData(FEMALE, name);
                }
                textView.setText(name);
                alertDialog.cancel();
            }
        }
        dialogBinding.btCancel.setOnClickListener(v -> alertDialog.cancel());
        // ExoPlayer (Notification)
        dialogBinding.btConfirm.setOnClickListener(new OnDialogClick());

    }

    /*
    Shared Preference name -
    key : value / Map // SQLite or Room (SQL Query)
     */
    private void saveData(String key, String value) {
        SharedPreferences.Editor editor = sp.edit();
        editor.putString(key, value);
        editor.apply();
    }

    private String read(String key) {

        return sp.getString(key, "");
    }

    private String readDate() {
        return sp.getString(SELECTED_DATE, "01/05/2024");
    }


    private void initUi() {
        // min - 26
        binding.tvMale.setText(read(MALE));
        binding.tvFemale.setText(read(FEMALE));
        selectedDate = fromStringToLocalDate(readDate());
        updateDate();
    }


    private void updateDate() {
        LocalDate todayDate = LocalDate.now(); // todayDate - selectedDate = dateBetween
        long dateBetween = todayDate.toEpochDay() - selectedDate.toEpochDay(); // 1970 Jan 1
        String days = dateBetween > 1 ? dateBetween + " Days" : dateBetween + " Day";
        binding.tvDateBetween.setText(days);
        binding.btDate.setText(fromLocalDateToString(selectedDate));
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

/*
If login success, saved isLogin and Username in SharedPreference!
when login again, if isLogin is true, skip login page and go to main page!
in main page, when click logout button, clear the shared preference and exit from the app!
 */