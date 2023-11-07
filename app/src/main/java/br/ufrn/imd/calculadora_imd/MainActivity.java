package br.ufrn.imd.calculadora_imd;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.view.View;
import br.ufrn.imd.calculadora_imd.fragments.CalculatorFragment;
import br.ufrn.imd.calculadora_imd.fragments.GradesCalculatorFragment;

public class MainActivity extends AppCompatActivity {
    private CalculatorFragment calculatorFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        calculatorFragment = new CalculatorFragment();
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.contentFrame, calculatorFragment);
        transaction.commit();
    }

    public void navToCalculator(View v) {
        calculatorFragment = new CalculatorFragment();
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.contentFrame, calculatorFragment);
        transaction.commit();
    }

    public void navToGrades(View v) {
        GradesCalculatorFragment gradesCalculatorFragment = new GradesCalculatorFragment();
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.contentFrame, gradesCalculatorFragment);
        transaction.commit();
    }
}