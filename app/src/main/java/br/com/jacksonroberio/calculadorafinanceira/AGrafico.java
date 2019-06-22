package br.com.jacksonroberio.calculadorafinanceira;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import com.github.mikephil.charting.animation.Easing;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.utils.ColorTemplate;
import java.util.ArrayList;
import java.util.List;
import br.com.jacksonroberio.calculadorafinanceira.negocio.entidade.builder.GraficoPizzaFactory;


/**
 * Tela responsável por mostrar os gráficos da aplicação, ela é invocada após ação na tela {@link MainActivity}.
 *
 * @author Jackson Roberio
 **/
public class AGrafico extends AppCompatActivity {

    List<PieEntry> valores = new ArrayList<>();

    //Variáveis de consumo da activity que irá lhe invocar.
    private float salarioLiquido;
    private float valorInss;
    private float valorIRPF;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_grafico);

        PieChart pieChart = findViewById(R.id.ag_grafico);
        definirDesignDosValoresDoGrafico();
        GraficoPizzaFactory.iniciar(pieChart)
                                .valoresGrafico(valores)
                                .comAnimacao(Easing.EaseInOutBack)
                                .coresTemplate(ColorTemplate.PASTEL_COLORS)
                                .corFonteValores(Color.WHITE)
                                .larguraSeparacaoEntrePartes(2f)
                                .montar();

    }


    private void definirDesignDosValoresDoGrafico(){
        resgatarValoresDeParametros();
        valores.add(new PieEntry(salarioLiquido, "Salário"));
        valores.add(new PieEntry(valorInss, "INSS"));
        valores.add(new PieEntry(valorIRPF, "IRPF"));
    }

    private void resgatarValoresDeParametros(){
        Bundle parametros = getIntent().getExtras();
        salarioLiquido  = parametros.getFloat("salario");
        valorInss       = parametros.getFloat("inss");
        valorIRPF       = parametros.getFloat("irpf");
    }
}
