package org.fundaciobit.versioapp.back.controller;

import org.fundaciobit.versioapp.persistence.FitxerJPA;
import org.fundaciobit.versioapp.model.entity.Fitxer;

import org.fundaciobit.genapp.common.filesystem.IFileManager;
import org.fundaciobit.genapp.common.web.controller.FilesFormManager;


/**
 * Gestiona Multiples Fitxers d'un Form
 * 
 * @author anadal
 * 
 */
public class VersioAppFilesFormManager extends FilesFormManager<Fitxer> {

  public VersioAppFilesFormManager(IFileManager<Fitxer> fitxerEjb) {
    super(fitxerEjb);
  }

  @Override
  public FitxerJPA createEmptyFile() {    
    return new FitxerJPA();
  }

}
