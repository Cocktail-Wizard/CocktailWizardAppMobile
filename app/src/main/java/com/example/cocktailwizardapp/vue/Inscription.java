package com.example.cocktailwizardapp.vue;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.cocktailwizardapp.R;

import java.util.Calendar;

public class Inscription extends AppCompatActivity implements View.OnClickListener {

    ImageView calendrier;
    TextView outputDate;
    EditText inputNomIns,inputCourIns,inputMdp,inputConfMdp;
    Button btnInscrire;
    @Override
    protected void onCreate(Bundle savedInstanceState)  {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inscription);

        calendrier = findViewById(R.id.imageCalendrier_id);
        calendrier.setOnClickListener(this);

        outputDate = findViewById(R.id.outputDateIns_id);

        inputNomIns = findViewById(R.id.inputNomIns_id);
        inputCourIns = findViewById(R.id.inputCourIns_id);
        inputMdp = findViewById(R.id.inputMdpIns_id);
        inputConfMdp = findViewById(R.id.inputConfMdpIns_id);

        btnInscrire = findViewById(R.id.btnInscrire_id);
        btnInscrire.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        if (v == calendrier) {

            final Calendar calen = Calendar.getInstance();

            int annee = calen.get(Calendar.YEAR);
            int mois = calen.get(Calendar.MONTH);
            int jour = calen.get(Calendar.DAY_OF_MONTH);


            // Creer le DatePicker
            DatePickerDialog selecteurDate = new DatePickerDialog(
                    Inscription.this, new DatePickerDialog.OnDateSetListener() {
                @Override
                public void onDateSet(DatePicker view, int annee,
                                      int mois, int jour) {
                    // Modifier le TextView avec les valeurs de date choisi
                    outputDate.setText(jour + "-" + (mois + 1) + "-" + annee);

                }
            }, annee, mois, jour);
            selecteurDate.show();
        } else if (v == btnInscrire) {
            //TODO
            //  Faire le verification de donner et startActivity
        }

    }
}