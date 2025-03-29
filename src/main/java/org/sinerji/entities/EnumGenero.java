package org.sinerji.entities;

public enum EnumGenero {
    M("Masculino"),
    F("Feminino");

    private String genero;

    EnumGenero(String genero) {
        this.genero = genero;
    }

    public String getGenero() {
        return genero;
    }
}
