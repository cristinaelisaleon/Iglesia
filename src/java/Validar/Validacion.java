/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Validar;

import java.util.regex.Pattern;
import java.util.regex.Matcher;
import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.component.html.HtmlInputText;
import javax.faces.context.FacesContext;
import javax.faces.validator.FacesValidator;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;
/**
 *
 * @author crist
 */
@FacesValidator("Validacion")
public class Validacion implements Validator{

    @Override
    public void validate(FacesContext context, UIComponent component, Object value) throws ValidatorException {
        Pattern pattern = Pattern.compile("\\w+@\\w+\\.\\w+");
        Matcher matcher = pattern.matcher((CharSequence) value);
        HtmlInputText htmlInputText = (HtmlInputText) component;
        String label;
        if (htmlInputText.getLabel()==null || htmlInputText.getLabel().trim().equals("")){
            label = htmlInputText.getId();
        }else{
            label=htmlInputText.getLabel();
        }
        if (!matcher.matches()){
            FacesMessage facesMessage = new FacesMessage(label + "El correo no es valido");
            throw  new ValidatorException(facesMessage);
        }
    }
    
    
}
