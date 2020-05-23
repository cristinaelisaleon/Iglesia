/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Validar;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.FacesValidator;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;
import org.primefaces.context.RequestContext;

/**
 *
 * @author crist
 */
@FacesValidator("ValidacionNum")
public class ValidacionNum implements Validator {

    @Override
    public void validate(FacesContext context, UIComponent component, Object value) throws ValidatorException {

        if (((String) value).length() != 10) {
            FacesMessage facesMessage = new FacesMessage("Se necesita 10 Digitos");
            
            RequestContext.getCurrentInstance().execute("alert('Digitos incorrecta');");
            throw new ValidatorException(facesMessage);
        }

    }
}
