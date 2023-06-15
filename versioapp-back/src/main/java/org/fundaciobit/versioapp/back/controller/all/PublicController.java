package org.fundaciobit.versioapp.back.controller.all;

import org.apache.log4j.Logger;

import org.fundaciobit.genapp.common.web.HtmlUtils;

import org.fundaciobit.versioapp.commons.utils.Version;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * 
 * @autor anadal
 * 
 */
@Controller
public class PublicController {

	protected final Logger log = Logger.getLogger(getClass());

	@RequestMapping(value = "/public/index.html")
	public ModelAndView principal(HttpSession session, HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		Boolean initialized = (Boolean) session.getAttribute("inicialitzat");

		if (initialized == null) {
			HtmlUtils.saveMessageInfo(request, "MessageInfo : Benvingut a VersioApp");
			session.setAttribute("inicialitzat", true);
		}

		return new ModelAndView("homepublic");

	}
	
	@RequestMapping(value = "/public/versio")
    public void versio(HttpServletResponse response) throws Exception {
        Version versio = new Version();
        response.getWriter().write(versio.getVersion() + "|" + versio.getBuildTime());
        response.getWriter().flush();
        response.getWriter().close();

    }

}
