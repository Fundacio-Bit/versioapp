package org.fundaciobit.versioapp.back.controller.admin;

import org.fundaciobit.versioapp.back.controller.all.VersionsAplicacioPublicController;
import org.fundaciobit.versioapp.back.form.webdb.AplicacioFilterForm;
import org.fundaciobit.versioapp.back.form.webdb.AplicacioForm;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

/**
 * 
 * @author anadal
 *
 */
@Controller
@RequestMapping(value = AplicacioAdminController.CONTEXTWEB)
@SessionAttributes(types = { AplicacioForm.class, AplicacioFilterForm.class })
public class AplicacioAdminController extends VersionsAplicacioPublicController {

    public static final String CONTEXTWEB = "/admin/aplicacio";

    @Override
    public boolean isAdmin() {
        return true;
    }

}
