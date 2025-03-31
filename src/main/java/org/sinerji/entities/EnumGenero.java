package org.sinerji.entities;

import lombok.Getter;

@Getter
public enum EnumGenero {
    M("Masculino"),
    F("Feminino");

    private String genero;

    EnumGenero(String genero) {
        this.genero = genero;
    }

}
