package br.com.jacksonroberio.calculadorafinanceira.util.listener;

import android.text.Editable;
import android.text.TextWatcher;

import br.com.jacksonroberio.calculadorafinanceira.MainActivity;

public class EditTextChangedValueDependentes implements TextWatcher {

    private MainActivity activity;

    public EditTextChangedValueDependentes(MainActivity activity) {
        this.activity = activity;
    }

    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {

    }

    @Override
    public void afterTextChanged(Editable s) {
        activity.mudouValorInputDependentes(s.toString());
    }
}
