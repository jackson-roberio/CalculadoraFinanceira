package br.com.jacksonroberio.calculadorafinanceira;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import br.com.jacksonroberio.calculadorafinanceira.negocio.entidade.ContraCheque;
import br.com.jacksonroberio.calculadorafinanceira.negocio.entidade.Trabalhador;
import br.com.jacksonroberio.calculadorafinanceira.util.listener.ButtonOnClick;
import br.com.jacksonroberio.calculadorafinanceira.util.listener.EditTextChangedValueDependentes;
import br.com.jacksonroberio.calculadorafinanceira.util.listener.EditTextChangedValueSalarioBruto;

/**
 * Activity principal, é a primeira tela que será mostrada ao usuário, nela é capturado o valor do salário juntamente
 * com a quantidade de dependentes que o usuário tem. Esses valores serão fundamental para geração do gráfico e calculo
 * dos impostos e salaário liquido.
 *
 * @author Jackson Roberio
 **/
public class MainActivity extends AppCompatActivity {

    private EditText edtSalarioBruto, edtSalarioLiquido, edtDependentes, edtAlqInss, edtAlqIrpf, edtBaseInss, edtBaseIrpf, edtVlInss, edtVlIrpf, edtDeducao;

    private Button buttonGrafico;

    private ContraCheque contraCheque;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        iniciarVariaveis();
        Trabalhador trb = new Trabalhador(0f, 0);
        contraCheque = new ContraCheque(trb);

        //Setar o valor do input de entrada do salaário na variável responsável pelos calculos (Trabalhador->Contracheque)
        edtSalarioBruto.addTextChangedListener(new EditTextChangedValueSalarioBruto(this));
        //Fim: setar valor do input de entrada no salário bruto.

        //Setar o valor do input dos dependentes, para poder calcular os valores finais de pagamento.
        edtDependentes.addTextChangedListener(new EditTextChangedValueDependentes(this));

        //Levar o usuário para a tela de gráficos;
        buttonGrafico.setOnClickListener(new ButtonOnClick(this));

        definirValores();
    }



    private void definirValores(){
        //Valores do INSS
        edtBaseInss.setText(contraCheque.getBaseInssString());
        edtAlqInss.setText(contraCheque.getAlicotaInssString());
        edtVlInss.setText(contraCheque.getInssString());

        //Valores do IRPF
        edtBaseIrpf.setText(contraCheque.getBaseIRPFString());
        edtAlqIrpf.setText(contraCheque.getAliquotaIRPFString());
        edtVlIrpf.setText(contraCheque.getValorIRPFString());
        edtDeducao.setText(contraCheque.getDeducaoIRPFString());

        //Valores finais
        edtSalarioLiquido.setText(contraCheque.getSalarioLiquidoString());
    }

    private void iniciarVariaveis(){
        edtSalarioBruto     =  findViewById(R.id.ap_edt_salario_bruto);
        edtSalarioLiquido   =  findViewById(R.id.ap_out_salario_liquido);
        edtDependentes      =  findViewById(R.id.ap_edt_qtd_dependentes);
        edtAlqInss          =  findViewById(R.id.ap_out_alq_inss);
        edtAlqIrpf          =  findViewById(R.id.ap_out_alq_irpf);
        edtBaseInss         =  findViewById(R.id.ap_out_base_inss);
        edtBaseIrpf         =  findViewById(R.id.ap_out_base_irpf);
        edtVlInss           =  findViewById(R.id.ap_out_vl_inss);
        edtVlIrpf           =  findViewById(R.id.ap_out_vl_irpf);
        edtDeducao          = findViewById(R.id.ap_out_deducao_irpf);
        buttonGrafico       = (Button) findViewById(R.id.ap_button_grafico);
    }

    //Método invocado ao clicar no botão para visualizar o gráfico.
    public void irActivityGrafico(){
        Intent i = new Intent(this, AGrafico.class);
        startActivity(i);
    }

    public void mudouValorInputDependentes(String novoValor){
        try {
            contraCheque.getTrabalhador().setDependentes(Integer.parseInt(novoValor.toString()));
        } catch (Exception e){
            contraCheque.getTrabalhador().setDependentes(0);
        }
        definirValores();
    }


    public void mudouValorInputSalarioBruto(String novoValor){
        try {
            contraCheque.getTrabalhador().setSalario(Float.parseFloat(novoValor.toString()));
        } catch (Exception e) {
            contraCheque.getTrabalhador().setSalario(0f);
        }
        definirValores();
    }
}
