package org.fundaciobit.versioapp.commons.utils;

/**
 *
 * @author anadal
 *
 */
public interface Constants {

  public static final String VERSIOAPP_PROPERTY_BASE="org.fundaciobit.versioapp.";

    public static final String MAIL_SERVICE = "java:/org.fundaciobit.versioapp.mail";

    // TRUE ROLES
    public static final String VER_ADMIN="VER_ADMIN";
    public static final String VER_USER="VER_USER";
    public static final String VER_WS="VER_WS";

    // VIRTUAL SECURITY ROLES
    public static final String ROLE_ADMIN = "ROLE_ADMIN";
    public static final String ROLE_USER = "ROLE_USER";
    
    // EJB HIGH LEVEL ROLES
    public static final String ROLE_EJB_FULL_ACCESS  = VER_ADMIN;
    public static final String ROLE_EJB_BASIC_ACCESS = VER_USER;
    public static final String ROLE_EJB_WS_ACCESS = VER_WS;
}
