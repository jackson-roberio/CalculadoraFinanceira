package br.com.jacksonroberio.calculadorafinanceira.util.listener;

import android.content.Intent;
import android.view.View;

import br.com.jacksonroberio.calculadorafinanceira.MainActivity;

public class ButtonOnClick implements View.OnClickListener {

    private MainActivity activity;

    public ButtonOnClick(MainActivity activity) {
        this.activity = activity;
    }

    @Override
    public void onClick(View v) {
        activity.irActivityGrafico();
    }
}
