package br.ufrn.imd.calculadora_imd.enums;

public enum ApprovedStatusEnum {
    APR("Aprovado"),
    APRN("Aprovado por nota"),
    REP ("Reprovado");

    private String status;

    ApprovedStatusEnum(String description) {
        this.status = description;
    }

    public String getDescription() {
        return this.status;
    }
}
