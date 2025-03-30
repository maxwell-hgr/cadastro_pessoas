package org.sinerji.controller;


import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;

import javax.faces.convert.FacesConverter;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@FacesConverter(value = "dataNascimentoConverter")
public class DataNascimentoConverter implements Converter {

    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("dd/MM/yyyy");

    // getAsObject traz o objeto da String que o input recebe para o atributo específico da classe
    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String value) {
        if (value == null || value.isEmpty()) {
            return null;
        }
        return LocalDate.parse(value, FORMATTER);
    }

    // faz o caminho inverso, traduz o objeto em string para exibição no jsf
    @Override
    public String getAsString(FacesContext context, UIComponent component, Object value) {
        if (value == null) {
            return "";
        }

        if (!(value instanceof LocalDate)) {
            throw new IllegalArgumentException("O valor deve ser do tipo LocalDate");
        }

        return FORMATTER.format((LocalDate) value);
    }
}