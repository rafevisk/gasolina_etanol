package com.galeno.gasolinavsetanol;

import android.support.design.widget.TextInputEditText;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;

import java.text.NumberFormat;

public class MainActivity extends AppCompatActivity {

    private static final NumberFormat currencyFormaty = NumberFormat.getCurrencyInstance();

    private double gasolina = 5.0;
    private double etanol = 5.0;

    private TextView gasolinaTextView;
    private TextView etanolTextView;
    private TextView amountGasolinaTextView;
    private TextView amountEtanolTextView;
    private ImageView fuel_imageView;
    private SeekBar seekBar_gasolina;
    private SeekBar seekBar_etanol;
    private TextInputEditText resultadoEditText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        gasolinaTextView = (TextView) findViewById(R.id.gasolinaTextView);
        etanolTextView = (TextView) findViewById(R.id.etanolTextView);
        amountEtanolTextView = (TextView) findViewById(R.id.amountEtanolTextView);
        amountGasolinaTextView = (TextView) findViewById(R.id.amountGasolinaTextView);
        fuel_imageView = (ImageView) findViewById(R.id.fuel_imageView);
        seekBar_etanol = (SeekBar) findViewById(R.id.seekBar_etanol);
        seekBar_gasolina = (SeekBar) findViewById(R.id.seekBar_gasolina);
        resultadoEditText = (TextInputEditText) findViewById(R.id.resultadoEditText);

        seekBar_gasolina.setOnSeekBarChangeListener(seekBarWatcher);
        seekBar_etanol.setOnSeekBarChangeListener(seekBarWatcher);
    }

    private SeekBar.OnSeekBarChangeListener seekBarWatcher = new SeekBar.OnSeekBarChangeListener() {
        @Override
        public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
            if (seekBar.getId() == R.id.seekBar_gasolina){
                gasolina = progress/100.0;
                amountGasolinaTextView.setText(currencyFormaty.format(gasolina));
            }
            else {
                etanol = progress/100.0;
                amountEtanolTextView.setText(currencyFormaty.format(etanol));
            }
            mostrar_melhorCombustivel();
        }

        @Override
        public void onStartTrackingTouch(SeekBar seekBar) {

        }

        @Override
        public void onStopTrackingTouch(SeekBar seekBar) {

        }
    };
    private void mostrar_melhorCombustivel (){
        double resultado = etanol / gasolina;
        amountGasolinaTextView.setText(currencyFormaty.format(gasolina));
        amountEtanolTextView.setText(currencyFormaty.format(etanol));
        if(resultado >= 0.7){
            resultadoEditText.setText(getString(R.string.gasolinaText));
            fuel_imageView.setImageResource(R.drawable.petroll1);
        }
        else{
            resultadoEditText.setText(getString(R.string.etanolText));
            fuel_imageView.setImageResource(R.drawable.ethanol);
        }
    }
}
