package br.com.jacksonroberio.calculadorafinanceira.negocio.entidade.regras;

public class DefineAliquotaEDeducaoIRPF {

    private static final float ZERO_PORCENTO = 1903.98f;

    private static final float SETE_PORCENTO_MINIMO = ZERO_PORCENTO;

    private static final float SETE_PORCENTO_MAXIMO = 2826.05F;

    private static final float QUINZE_PORCENTO_MINIMO = SETE_PORCENTO_MAXIMO;

    private static final float QUINZE_PORCENTO_MAXIMO = 3751.05F;

    private static final float VINTEDOIS_PORCENTO_MINIMO = QUINZE_PORCENTO_MAXIMO;

    private static final float VINTEDOIS_PORCENTO_MAXIMO = 4664.08f;

    private static final float VINTESETE_PORCENTO = VINTEDOIS_PORCENTO_MAXIMO;


    public static float getAliquota(float baseIRPF){
        float resultado = 0f;

        if(baseIRPF < ZERO_PORCENTO)
            resultado = 0f;
        else if (baseIRPF > SETE_PORCENTO_MINIMO && baseIRPF <= SETE_PORCENTO_MAXIMO)
            resultado = 7.5f;
        else if (baseIRPF > QUINZE_PORCENTO_MINIMO && baseIRPF <= QUINZE_PORCENTO_MAXIMO)
            resultado = 15f;
        else if (baseIRPF > VINTEDOIS_PORCENTO_MINIMO && baseIRPF <= VINTEDOIS_PORCENTO_MAXIMO)
            resultado = 22.5f;
        else if(baseIRPF > VINTESETE_PORCENTO)
            resultado = 27.5f;

        return resultado;
    }

    public static float getDeducao(float baseIRPF){
        float resultado = 0f;

        if(baseIRPF < ZERO_PORCENTO)
            resultado = 0f;
        else if (baseIRPF > SETE_PORCENTO_MINIMO && baseIRPF <= SETE_PORCENTO_MAXIMO)
            resultado = 142.80f;
        else if (baseIRPF > QUINZE_PORCENTO_MINIMO && baseIRPF <= QUINZE_PORCENTO_MAXIMO)
            resultado = 354.80f;
        else if (baseIRPF > VINTEDOIS_PORCENTO_MINIMO && baseIRPF <= VINTEDOIS_PORCENTO_MAXIMO)
            resultado = 636.13f;
        else if(baseIRPF > VINTESETE_PORCENTO)
            resultado = 869.36f;

        return resultado;
    }

}
