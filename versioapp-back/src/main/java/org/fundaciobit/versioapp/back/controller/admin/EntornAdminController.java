package org.fundaciobit.versioapp.back.controller.admin;

import javax.servlet.http.HttpServletRequest;

import org.fundaciobit.versioapp.back.controller.webdb.EntornController;
import org.fundaciobit.versioapp.back.form.webdb.EntornFilterForm;
import org.fundaciobit.versioapp.back.form.webdb.EntornForm;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

@Controller
@RequestMapping(value = EntornAdminController.CONTEXTWEB )
@SessionAttributes(types = { EntornForm.class, EntornFilterForm.class })
public class EntornAdminController extends EntornController{
    
    public static final String CONTEXTWEB = "/admin/entorn";

    @Override
    public String getTileForm() {
        return "entornFormAdmin";
      }

    @Override
      public String getTileList() {
        return "entornListAdmin";
      }
    
    
    @Override
    public String getRedirectWhenModified(HttpServletRequest request, EntornForm entornForm, Throwable __e) {
        if (__e == null) {
            // XYZ ZZZ 
          return "redirect:" + AplicacioAdminController.CONTEXTWEB  + "/list"; // getContextWeb()
        } else {
          return  getTileForm();
        }
      }
    
    
    
}
