package br.ufrn.imd.calculadora_imd.fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import br.ufrn.imd.calculadora_imd.utils.AppAlertDialog;
import br.ufrn.imd.calculadora_imd.R;
import net.objecthunter.exp4j.Expression;
import net.objecthunter.exp4j.ExpressionBuilder;

public class CalculatorFragment extends Fragment {
    private TextView display;

    public CalculatorFragment() { }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_calculator, container, false);

        this.display = (TextView) view.findViewById(R.id.resultadoCalculadora);
        Button buttonOne = (Button) view.findViewById(R.id.buttonOne);
        Button buttonTwo = (Button) view.findViewById(R.id.buttonTwo);
        Button buttonThree = (Button) view.findViewById(R.id.buttonThree);
        Button buttonFour = (Button) view.findViewById(R.id.buttonFour);
        Button buttonFive = (Button) view.findViewById(R.id.buttonFive);
        Button buttonSix = (Button) view.findViewById(R.id.buttonSix);
        Button buttonSeven = (Button) view.findViewById(R.id.buttonSeven);
        Button buttonEight = (Button) view.findViewById(R.id.buttonEight);
        Button buttonNine = (Button) view.findViewById(R.id.buttonNine);
        Button buttonZero = (Button) view.findViewById(R.id.buttonZero);
        Button buttonAddition = (Button) view.findViewById(R.id.buttonAddition);
        Button buttonSubtraction = (Button) view.findViewById(R.id.buttonSubtraction);
        Button buttonMultiplication = (Button) view.findViewById(R.id.buttonMultiplication);
        Button buttonDivision = (Button) view.findViewById(R.id.buttonDivision);
        Button buttonDelete = (Button) view.findViewById(R.id.buttonDelete);
        Button buttonEqual = (Button) view.findViewById(R.id.buttonEqual);

        buttonOne.setOnClickListener(getCalculatorClickListener("1"));
        buttonTwo.setOnClickListener(getCalculatorClickListener("2"));
        buttonThree.setOnClickListener(getCalculatorClickListener("3"));
        buttonFour.setOnClickListener(getCalculatorClickListener("4"));
        buttonFive.setOnClickListener(getCalculatorClickListener("5"));
        buttonSix.setOnClickListener(getCalculatorClickListener("6"));
        buttonSeven.setOnClickListener(getCalculatorClickListener("7"));
        buttonEight.setOnClickListener(getCalculatorClickListener("8"));
        buttonNine.setOnClickListener(getCalculatorClickListener("9"));
        buttonZero.setOnClickListener(getCalculatorClickListener("0"));
        buttonAddition.setOnClickListener(getCalculatorClickListener("+"));
        buttonSubtraction.setOnClickListener(getCalculatorClickListener("-"));
        buttonMultiplication.setOnClickListener(getCalculatorClickListener("*"));
        buttonDivision.setOnClickListener(getCalculatorClickListener("/"));
        buttonDelete.setOnClickListener(deleteLastValue());
        buttonEqual.setOnClickListener(calculate());

        return view;
    }

    private View.OnClickListener getCalculatorClickListener(final String buttonText) {
        return (View v) -> {
            this.display.setText(String.format("%s%s", display.getText().toString(), buttonText));
        };
    }

    private View.OnClickListener deleteLastValue() {
        return (View v) -> {
            if (this.display.length() > 0) {
                display.setText(display.getText().subSequence(0, this.display.length() - 1));
            }
        };
    }

    private View.OnClickListener calculate() {
        return (View v) -> {
            if (this.display.length() > 0) {
                try {
                    Expression expression = new ExpressionBuilder(this.display.getText().toString()).build();
                    Double result = expression.evaluate();
                    display.setText(result.toString());
                } catch (ArithmeticException | IllegalArgumentException e) {
                    AppAlertDialog.showAlertDialog("Expressão inválida", "Informe uma expressão válida", getContext());
                }
            }
        };
    }

}