    package br.ufrn.imd.calculadora_imd.fragments;

    import android.os.Bundle;

    import androidx.fragment.app.Fragment;

    import android.view.LayoutInflater;
    import android.view.View;
    import android.view.ViewGroup;
    import android.widget.Button;
    import android.widget.EditText;
    import android.widget.TextView;
    import br.ufrn.imd.calculadora_imd.utils.AppAlertDialog;
    import br.ufrn.imd.calculadora_imd.enums.ApprovedStatusEnum;
    import br.ufrn.imd.calculadora_imd.services.GradesService;
    import br.ufrn.imd.calculadora_imd.R;


    public class GradesCalculatorFragment extends Fragment {
        GradesService service;
        private EditText grade1Text;
        private EditText grade2Text;
        private EditText grade3Text;
        private TextView situationText;

        public GradesCalculatorFragment() {
            this.service = new GradesService();
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
            View view = inflater.inflate(R.layout.fragment_grades, container, false);

            grade1Text = view.findViewById(R.id.editTextGrade1);
            grade2Text = (EditText) view.findViewById(R.id.editTextGrade2);
            grade3Text = (EditText) view.findViewById(R.id.editTextGrade3);
            situationText = (TextView) view.findViewById(R.id.situationTextView);

            Button calculateButton = (Button) view.findViewById(R.id.calculateAverageButton);
            calculateButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    processCalculateButton(v);
                }
            });

            return view;
        }

        public void processCalculateButton(View v) {
            if (grade1Text.getText().toString().isEmpty()) {
                AppAlertDialog.showAlertDialog("Entradas inválidas", "Por favor, informe, pelo menos, a primeira nota!", getContext());
            }
            else if (grade2Text.getText().toString().isEmpty()) {
                this.processOneGrade();
            } else if (grade3Text.getText().toString().isEmpty()) {
                this.processTwoGrades();
            } else {
                this.processThreeGrades();
            }
        }

        private void processOneGrade() {
            double grade1 = Double.parseDouble(grade1Text.getText().toString());
            double gradeToBeApproved = this.service.getMinimumGradeToApproved(grade1);
            double  gradeToBeApprovedByGrade = this.service.getMinimumGradeToApprovedByGrade(grade1);

            situationText.setText("");

            String alertDialogText = "Com " + gradeToBeApproved + " na 2ª e na 3ª unidades você será aprovado por média\n" +
                    "Com " + gradeToBeApprovedByGrade + " na 2ª e na 3ª unidades você será aprovado por nota";
            AppAlertDialog.showAlertDialog("Atenção",  alertDialogText, getContext());
        }

        private void processTwoGrades() {
            double grade1 = Double.parseDouble(grade1Text.getText().toString());
            double grade2 = Double.parseDouble(grade2Text.getText().toString());
            double gradeToBeApproved = this.service.getMinimumGradeToApproved(grade1, grade2);
            double gradeToBeApprovedByGrade = this.service.getMinimumGradeToApprovedByGrade(grade1, grade2);

            situationText.setText("");

            String alertDialogText = "Com " + gradeToBeApproved + " na 3ª unidade você será aprovado por média\n" +
                    " e com " + gradeToBeApprovedByGrade + " na 3ª unidade você será aprovado por nota";
            AppAlertDialog.showAlertDialog("Atenção", alertDialogText, getContext());
        }

        private void processThreeGrades() {
            double grade1 = Double.parseDouble(grade1Text.getText().toString());
            double grade2 = Double.parseDouble(grade2Text.getText().toString());
            double grade3 = Double.parseDouble(grade3Text.getText().toString());
            double average = this.service.calculateAverage(grade1, grade2, grade3);
            ApprovedStatusEnum situation = this.service.getSituationByAverage(average);

            situationText.setText(situation.getDescription());

            AppAlertDialog.showAlertDialog("Média", "Sua média é " + average, getContext());
        }
    }