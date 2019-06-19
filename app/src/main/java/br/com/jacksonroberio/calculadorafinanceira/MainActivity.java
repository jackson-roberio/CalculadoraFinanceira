package br.com.jacksonroberio.calculadorafinanceira;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.Button;
import android.widget.EditText;

import br.com.jacksonroberio.calculadorafinanceira.negocio.entidade.ContraCheque;
import br.com.jacksonroberio.calculadorafinanceira.negocio.entidade.Trabalhador;

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
        edtSalarioBruto.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
               try {
                    contraCheque.getTrabalhador().setSalario(Float.parseFloat(s.toString()));
               } catch (Exception e) {
                   contraCheque.getTrabalhador().setSalario(0f);
               }

               definirValores();
            }
        });
        //Fim: setar valor do input de entrada no salário bruto.

        //Setar o valor do input dos dependentes, para poder calcular os valores finais de pagamento.
        edtDependentes.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                try {
                    contraCheque.getTrabalhador().setDependentes(Integer.parseInt(s.toString()));
                } catch (Exception e){
                    contraCheque.getTrabalhador().setDependentes(0);
                }
                definirValores();
            }
        });
        //FIM: setar valores dos dependentes.


        definirValores();
    }



    private void definirValores(){
        edtBaseInss.setText(contraCheque.getBaseInssString());
        edtAlqInss.setText(contraCheque.getAlicotaInssString());
        edtVlInss.setText(contraCheque.getInssString());
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
}
