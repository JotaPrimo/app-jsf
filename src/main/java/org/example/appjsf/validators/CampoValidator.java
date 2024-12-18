package org.example.appjsf.validators;

import org.example.appjsf.enuns.EnumSexo;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;
import javax.faces.validator.FacesValidator;

@FacesValidator("campoValidator")
public class CampoValidator  implements Validator {

    @Override
    public void validate(FacesContext context, UIComponent component, Object value) throws ValidatorException {
        // Obtém o atributo customizado do campo (se necessário)
        String tipoValidacao = (String) component.getAttributes().get("tipoValidacao");

        if ("email".equals(tipoValidacao)) {
            // Validação de email simples
            String email = (String) value;
            if (!email.matches("^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}$")) {
                FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Erro", "Email inválido.");
                throw new ValidatorException(message);
            }
        } else if ("cpf".equals(tipoValidacao)) {
            // Validação de CPF simples
            String cpf = (String) value;
            if (!cpf.matches("\\d{3}\\.\\d{3}\\.\\d{3}-\\d{2}")) {
                FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Erro", "CPF inválido.");
                throw new ValidatorException(message);
            }
        } else if ("telefone".equals(tipoValidacao)) {
            // Validação de telefone
            String telefone = (String) value;
            if (!telefone.matches("\\(\\d{2}\\) \\d{5}-\\d{4}")) {
                FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Erro", "Telefone inválido.");
                throw new ValidatorException(message);
            }
        }
        else if ("nome".equals(tipoValidacao)) {
            //Validação de nome
            String nome =  (String) value;
            if( nome == null || nome.isEmpty()){
                FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Erro", "Campo nome é obrigatório.");
                throw new ValidatorException(message);
            }
        }
        else if ("sexo".equals(tipoValidacao)) {
            //Validação de sexo
            if( value != EnumSexo.FEMININO && value != EnumSexo.MASCULINO){
                FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Erro", "Campo sexo é obrigatório.");
                throw new ValidatorException(message);
            }
        }

    }
}
