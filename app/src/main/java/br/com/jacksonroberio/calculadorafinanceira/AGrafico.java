package br.com.jacksonroberio.calculadorafinanceira;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;


/**
 * Tela responsável por mostrar os gráficos da aplicação, ela é invocada após ação na tela {@link MainActivity}.
 *
 * @author Jackson Roberio
 **/
public class AGrafico extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_grafico);
    }
}
