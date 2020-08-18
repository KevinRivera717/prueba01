package com.example.conversores;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TabHost;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    final String[] datos = new String[]{"Dolar", "Euro", "Colones"};

    private Spinner monedaActualSP;
    private Spinner monedaCambioSP;
    private EditText valorCambioET;
    private TextView resultadoTV;

    final private double factorDolarEuro = 0.84;
    final private double factorColonDolar = 8.72;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        TabHost tbhConversores = (TabHost)findViewById(R.id.tbhConversor );
        tbhConversores .setup() ;

       tbhConversores.addTab(tbhConversores.newTabSpec("Moneda").setContent(R.id.Moneda ).setIndicator("Moneda",null) );
        tbhConversores.addTab(tbhConversores.newTabSpec("Longitud").setContent(R.id.Longitud  ).setIndicator("Longitud",null) );

        //ArrayAdapter: agregamos los elementos del array dato al spinner monedaActual

        ArrayAdapter<String> adactador = new ArrayAdapter<String>(this, R.layout.support_simple_spinner_dropdown_item, datos);

        monedaActualSP = (Spinner) findViewById(R.id.MonedaActualSP);
        monedaActualSP.setAdapter(adactador);

    }

    //agregamos el evento click:Este evento se enlazara con el boton de la interfaz

    public void clickCovertir(View v) {
        monedaActualSP = (Spinner) findViewById(R.id.MonedaActualSP);
        monedaCambioSP = (Spinner) findViewById(R.id.MonedaCambioSP);
        valorCambioET = (EditText) findViewById(R.id.ValorCambioET);
        resultadoTV = (TextView) findViewById(R.id.ResultadoTV);

        String monedaActual = monedaActualSP.getSelectedItem().toString();
        String monedaCambio = monedaCambioSP.getSelectedItem().toString();

        double valorCambio = Double.parseDouble(valorCambioET.getText().toString());
        double resultado = ProcesarCoversion(monedaActual, monedaCambio, valorCambio);

        if (resultado > 0) {
            resultadoTV.setText(String.format("por %5.2f %s, usted recibira %5.2f %s", valorCambio, monedaActual, resultado, monedaCambio));
            valorCambioET.setText("");
        } else {
            resultadoTV.setText(String.format("Usted recibira"));
            Toast.makeText(MainActivity.this, "Las Opciones elegidas no tienenun factor de conversion", Toast.LENGTH_SHORT).show();
        }
    }

    private double ProcesarCoversion(String monedaActual, String monedaCambio, double valoCambio){

        double resultadoConersion=0;

        switch (monedaActual){
            case "Dolar":
               if (monedaCambio.equals("Euro")){
                resultadoConersion =valoCambio * factorDolarEuro ;
               }
               if (monedaCambio.equals("Colones")){
                resultadoConersion = valoCambio / factorColonDolar;
               }
                break;
            case "Euro":
                if (monedaCambio .equals("Dolar")){
                    resultadoConersion = valoCambio / factorDolarEuro;
                }
                break;
            case "Colones":
                if (monedaCambio .equals("Dolar")){
                    resultadoConersion = valoCambio = factorColonDolar;
                }
                break;

        }
        return resultadoConersion;
    }



}




