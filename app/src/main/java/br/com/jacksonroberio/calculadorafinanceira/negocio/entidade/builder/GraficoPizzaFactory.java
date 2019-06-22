package br.com.jacksonroberio.calculadorafinanceira.negocio.entidade.builder;


import android.graphics.Paint;
import com.github.mikephil.charting.animation.Easing;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import java.util.ArrayList;
import java.util.List;
import br.com.jacksonroberio.calculadorafinanceira.negocio.entidade.GraficoPizza;


/**
 * Implementação do padrão Builder no {@link GraficoPizza}.
 *
 * @author Jackson Roberio
 **/
public class GraficoPizzaFactory {

    private GraficoPizza grafico;

    //Valores complementares que serão setados no ato do builder (final).
    private Integer tempoAnimacao = null;
    private Easing.EasingFunction tipoAnimacao = null;


    /**
     * Inicia com valores padrões pré-definidos para montar um gráfico básico;
     *
     **/
    private GraficoPizzaFactory(PieChart pieChart) {
        grafico = new GraficoPizza();
        grafico.setPieChart(pieChart);
        grafico.setValores(new ArrayList<PieEntry>());
        grafico.setDataSet(new PieDataSet(null, null));
        grafico.setDados(new PieData());
        exibirValoresEnPorcentagem(true);
    }


    /***
     * Chama o construtor que é privado e só pode ser instanciado via padrão builder.
     *
     * @see #GraficoPizzaFactory(PieChart)
     */
    public static GraficoPizzaFactory iniciar(PieChart componentePieChat) {
        return new GraficoPizzaFactory(componentePieChat);
    }

    /*  VALORES PARA DEFINIÇÃO DO OBJETO GRAFICOPIZZA  */
    public GraficoPizzaFactory exibirValoresEnPorcentagem(boolean exibir){
        grafico.getPieChart().setUsePercentValues(exibir);
        return this;
    }

    public GraficoPizzaFactory descricaoGrafico(String descricao){
        grafico.getPieChart().getDescription().setText(descricao);
        return  this;
    }

    public GraficoPizzaFactory posicaoXYDescricaoGrafico(int posicaoX, int posicaoY){
        grafico.getPieChart().getDescription().setPosition(posicaoX, posicaoY);
        return this;
    }

    public GraficoPizzaFactory alinharDescricaoGrafico(Paint.Align alinhamento){
        grafico.getPieChart().getDescription().setTextAlign(alinhamento);
        return this;
    }

    public GraficoPizzaFactory fundoCentralizadoVazado(boolean furarGrafico){
        grafico.getPieChart().setDrawHoleEnabled(furarGrafico);
        return this;
    }

    /**
     * Usar valores de :
     * {@link com.github.mikephil.charting.animation.Easing.EasingFunction },
     *
     * Exempplo:
     * valor válido = {@literal Easing.EaseInOutCubic}
     **/
    public GraficoPizzaFactory comAnimacao(Easing.EasingFunction tipoAnimacao){
        this.tipoAnimacao = tipoAnimacao;
        return  this;
    }

    public  GraficoPizzaFactory tempoDaAnimacao(int tempoAnimacao){
        this.tempoAnimacao = tempoAnimacao;
        return this;
    }

    public GraficoPizzaFactory marginSuperior(float top){
        grafico.getPieChart().setExtraTopOffset(top);
        return this;
    }

    public GraficoPizzaFactory marginInferior(float top){
        grafico.getPieChart().setExtraBottomOffset(top);
        return this;
    }

    public GraficoPizzaFactory marginEsquerda(float top){
        grafico.getPieChart().setExtraLeftOffset(top);
        return this;
    }

    public GraficoPizzaFactory marginDireita(float top){
        grafico.getPieChart().setExtraRightOffset(top);
        return this;
    }


    /*  DADOS DE INSERÇÃO NO PieDataSet */
    public GraficoPizzaFactory valoresGrafico(List<PieEntry> listaDosValores){
        grafico.getDataSet().setValues(listaDosValores);
        return this;
    }

    /**
     * Minimo valor = 0
     * Máximo valor = 20
     * */
    public GraficoPizzaFactory larguraSeparacaoEntrePartes(float esppaco){
        grafico.getDataSet().setSliceSpace(esppaco);
        return this;
    }

    /**
     * usar :
     * {@literal com.github.mikephil.charting.utils.ColorTemplate.LIBERTY_COLORS},
     * {@literal com.github.mikephil.charting.utils.ColorTemplate.JOYFUL_COLORS},
     * {@literal com.github.mikephil.charting.utils.ColorTemplate.VORDIPLOM_COLORS},
     * {@literal com.github.mikephil.charting.utils.ColorTemplate.MATERIAL_COLORS},
     * {@literal com.github.mikephil.charting.utils.ColorTemplate.PASTEL_COLORS}, ou
     * {@literal com.github.mikephil.charting.utils.ColorTemplate.COLORFUL_COLORS}.
     **/
    public GraficoPizzaFactory coresTemplate(int[] template){
        grafico.getDataSet().setColors(template);
        return this;
    }


    /*  DADOS DE INSERÇÃO NO arquivo.getDados() */
    public GraficoPizzaFactory tamanhoFonteValores(float tamanhoFonte){
        grafico.getDados().setValueTextSize(tamanhoFonte);
        return this;
    }

    /**
     * Usar algum valor de:
     * {@literal Color}
     **/
    public GraficoPizzaFactory corFonteValores(int cor){
        grafico.getDados().setValueTextColor(cor);
        return this;
    }


    /** Cria o gráficos a partir dos valores setados */
    public GraficoPizza montar(){
        verificarDadosEValoresFinais();
        return grafico;
    }

    /*  Regras internas para construção do gráfico */
    private  final void verificarDadosEValoresFinais(){
        isExibirDescricaoGrafico();
        isGraficoAnimado();
        setarValoresPieData();
        desenharGrafico();
    }


    //Verifica se o usuário alterou o valor padrão da descrição do texto, se sim, então o exibe.
    private final void isExibirDescricaoGrafico(){
        if(grafico.getPieChart().getDescription().getText().equals("Description Label"))
            grafico.getPieChart().getDescription().setEnabled(false);
        else
            grafico.getPieChart().getDescription().setEnabled(true);
    }

    //Garante que a animação seja criada, independemente se só tiver o tempo da animação ou o tipo da animação
    private final void isGraficoAnimado(){
        if(tipoAnimacao != null || tempoAnimacao != null){
            if(tipoAnimacao == null && tempoAnimacao != null)
                grafico.getPieChart().animateY(tempoAnimacao);
            else if(tipoAnimacao != null && tempoAnimacao == null)
                grafico.getPieChart().animateY(1400, tipoAnimacao);
            else
                grafico.getPieChart().animateY(tempoAnimacao, tipoAnimacao);
        }
        //se não tiver setado o valor do tempo de animação ou o tipo da animação, então não faz nada.
    }


    //TODO: Tem que colocar uma exceção aqui caso não seja setado o valor no builder
    private final void setarValoresPieData(){
        grafico.getDados().setDataSet(grafico.getDataSet());
    }

    //Último método a ser invocado, fechando o ciclo e retornando o objeto responsável por poder desenhar o gráfico.
    private final void desenharGrafico(){
        grafico.getPieChart().setData(grafico.getDados());
    }

}
