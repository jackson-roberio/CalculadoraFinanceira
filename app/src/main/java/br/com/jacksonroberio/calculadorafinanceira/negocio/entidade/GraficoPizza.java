package br.com.jacksonroberio.calculadorafinanceira.negocio.entidade;

import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;

import java.util.ArrayList;
import java.util.List;

/**
 * Classse responsável por trabalhar com as propriedades necessárias
 * para a biblioteca https://github.com/PhilJay/MPAndroidChart
 *
 * @author Jackson  Roberio
 */
public class GraficoPizza {

    private PieChart pieChart;
    List<PieEntry> valores;
    PieDataSet dataSet;
    PieData dados;


    /**
     * Construtor padrão já da inicio na lista {@link #getValores()}.
     **/
    public GraficoPizza() {
        valores = new ArrayList<>();
    }


    /**
     *  Getters & Setteres
     **/
    public PieChart getPieChart() {
        return pieChart;
    }

    public void setPieChart(PieChart pieChart) {
        this.pieChart = pieChart;
    }

    public List<PieEntry> getValores() {
        return valores;
    }

    public void setValores(List<PieEntry> valores) {
        this.valores = valores;
    }

    public PieDataSet getDataSet() {
        return dataSet;
    }

    public void setDataSet(PieDataSet dataSet) {
        this.dataSet = dataSet;
    }

    public PieData getDados() {
        return dados;
    }

    public void setDados(PieData dados) {
        this.dados = dados;
    }
}
