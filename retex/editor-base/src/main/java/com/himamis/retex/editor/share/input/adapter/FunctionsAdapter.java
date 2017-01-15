package com.himamis.retex.editor.share.input.adapter;

import java.util.Arrays;
import java.util.List;

import com.himamis.retex.editor.share.editor.MathFieldInternal;

public class FunctionsAdapter extends StringInput {

    private static final List<String> fnList = Arrays.asList("sin", "cos", "tan", "ln", "log",
            "sinh", "cosh", "tanh", "arcsin", "arccos", "arctan");

    @Override
    public void commit(MathFieldInternal mfi, String input) {
        commitFunction(mfi, input);
    }

    @Override
    public boolean test(String input) {
        return fnList.contains(input);
    }
}
