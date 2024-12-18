package org.example.appjsf.converter;

import org.example.appjsf.entities.Assistido;
import org.example.appjsf.services.AssistidoService;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

@FacesConverter("assistidoConverter")
public class AssistidoConverter implements Converter<Assistido> {

    private AssistidoService assistidoService = new AssistidoService();

    @Override
    public Assistido getAsObject(FacesContext facesContext, UIComponent uiComponent, String s) {
        if (s == null || s.trim().isEmpty()) {
            return null;
        }
        try {
            return (Assistido) assistidoService.pesquisar(Assistido.class, s);
        } catch (NumberFormatException e) {
            throw new RuntimeException("Erro ao converter Assistido: " + e.getMessage(), e);
        }
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Assistido assistido) {
        if (assistido == null || assistido.getId() == null) {
            return "";
        }
        return String.valueOf(assistido.getId());
    }
}
