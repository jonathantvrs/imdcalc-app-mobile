package br.ufrn.imd.calculadora_imd.services;

import br.ufrn.imd.calculadora_imd.enums.ApprovedStatusEnum;

public class GradesService {
    public ApprovedStatusEnum getSituationByAverage(double average) {
        if(average >= 7) return ApprovedStatusEnum.APR;
        if(average >= 5) return ApprovedStatusEnum.APRN;

        return ApprovedStatusEnum.REP;
    }

    public double getMinimumGradeToApproved(double grade1) {
        return this.getMinimumGrade(grade1, 7);
    }

    public double getMinimumGradeToApprovedByGrade(double grade1) {
        return this.getMinimumGrade(grade1, 5);
    }

    public double getMinimumGradeToApproved(double grade1, double grade2) {
        return this.getMinimumGrade(grade1, grade2, 7);
    }

    public double getMinimumGradeToApprovedByGrade(double grade1, double grade2) {
        return this.getMinimumGrade(grade1, grade2, 5);
    }

    private double getMinimumGrade(double grade1, double average) {
        final double minimumGradesSum = (3 * average) - grade1;

        return minimumGradesSum / 2;
    }

    private double getMinimumGrade(double grade1, double grade2, double average) {
        return (3 * average) - grade1 - grade2;
    }

    public double calculateAverage(double grade1, double grade2, double grade3) {
        return (grade1 + grade2 + grade3) / 3;
    }
}
