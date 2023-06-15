
package org.fundaciobit.versioapp.persistence;
import org.fundaciobit.versioapp.model.entity.*;
import javax.persistence.Table;
import javax.persistence.Column;
import java.util.HashSet;
import javax.persistence.Entity;
import javax.persistence.GenerationType;
import javax.persistence.Index;
import javax.persistence.GeneratedValue;
import javax.persistence.SequenceGenerator;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import java.util.Set;
import javax.persistence.Id;


@Entity(name = "AplicacioJPA")
@Table(name = "ver_aplicacio" , indexes = { 
        @Index(name="ver_aplicacio_pk_i", columnList = "aplicacioid")})
@SequenceGenerator(name="APLICACIO_SEQ", sequenceName="ver_aplicacio_seq", allocationSize=1, initialValue=1000)
@javax.xml.bind.annotation.XmlRootElement
public class AplicacioJPA implements Aplicacio {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator="APLICACIO_SEQ")
    @Column(name="aplicacioid",nullable = false,length = 19)
    long aplicacioID;

    @Column(name="nom",nullable = false,length = 255)
    java.lang.String nom;

    @Column(name="contextpath",nullable = false,length = 255)
    java.lang.String contextPath;



  /** Constructor Buit */
  public AplicacioJPA() {
  }

  /** Constructor amb tots els camps  */
  public AplicacioJPA(long aplicacioID , java.lang.String nom , java.lang.String contextPath) {
    this.aplicacioID=aplicacioID;
    this.nom=nom;
    this.contextPath=contextPath;
}
  /** Constructor sense valors autoincrementals */
  public AplicacioJPA(java.lang.String nom , java.lang.String contextPath) {
    this.nom=nom;
    this.contextPath=contextPath;
}
  public AplicacioJPA(Aplicacio __bean) {
    this.setAplicacioID(__bean.getAplicacioID());
    this.setNom(__bean.getNom());
    this.setContextPath(__bean.getContextPath());
	}

	public long getAplicacioID() {
		return(aplicacioID);
	};
	public void setAplicacioID(long _aplicacioID_) {
		this.aplicacioID = _aplicacioID_;
	};

	public java.lang.String getNom() {
		return(nom);
	};
	public void setNom(java.lang.String _nom_) {
		this.nom = _nom_;
	};

	public java.lang.String getContextPath() {
		return(contextPath);
	};
	public void setContextPath(java.lang.String _contextPath_) {
		this.contextPath = _contextPath_;
	};



  @Override
  public boolean equals(Object __obj) {
  boolean __result;
    if (__obj != null && __obj instanceof Aplicacio) {
      Aplicacio __instance = (Aplicacio)__obj;
      __result = true;
      __result = __result && (this.getAplicacioID() == __instance.getAplicacioID()) ;
    } else {
      __result = false;
    }
    return __result;
  }

// EXP  Field:aplicacioid | Table: ver_entornaplicacio | Type: 0  

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "aplicacio")
    private Set<EntornAplicacioJPA> entornAplicacios = new HashSet<EntornAplicacioJPA>(0);
    public  Set<EntornAplicacioJPA> getEntornAplicacios() {
    return this.entornAplicacios;
  }

    public void setEntornAplicacios(Set<EntornAplicacioJPA> entornAplicacios) {
      this.entornAplicacios = entornAplicacios;
    }



 // ---------------  STATIC METHODS ------------------
  public static AplicacioJPA toJPA(Aplicacio __bean) {
    if (__bean == null) { return null;}
    AplicacioJPA __tmp = new AplicacioJPA();
    __tmp.setAplicacioID(__bean.getAplicacioID());
    __tmp.setNom(__bean.getNom());
    __tmp.setContextPath(__bean.getContextPath());
		return __tmp;
	}


  public static AplicacioJPA copyJPA(AplicacioJPA __jpa) {
    return copyJPA(__jpa,new java.util.HashMap<Object,Object>(), null);
  }

  static java.util.Set<AplicacioJPA> copyJPA(java.util.Set<AplicacioJPA> __jpaSet,
    java.util.Map<Object,Object> __alreadyCopied, String origenJPA) {
    if (__jpaSet == null) { return null; }
    java.util.Set<AplicacioJPA> __tmpSet = (java.util.Set<AplicacioJPA>) __alreadyCopied.get(__jpaSet);
    if (__tmpSet != null) { return __tmpSet; };
    __tmpSet = new java.util.HashSet<AplicacioJPA>(__jpaSet.size());
    __alreadyCopied.put(__jpaSet, __tmpSet);
    for (AplicacioJPA __jpa : __jpaSet) {
      __tmpSet.add(copyJPA(__jpa, __alreadyCopied, origenJPA));
    }
    return __tmpSet;
  }

  static AplicacioJPA copyJPA(AplicacioJPA __jpa,
    java.util.Map<Object,Object> __alreadyCopied, String origenJPA) {
    if (__jpa == null) { return null; }
    AplicacioJPA __tmp = (AplicacioJPA) __alreadyCopied.get(__jpa);
    if (__tmp != null) { return __tmp; };
    __tmp = toJPA(__jpa);
    __alreadyCopied.put(__jpa, __tmp);
    // Copia de beans complexes (EXP)
    if(!"EntornAplicacioJPA".equals(origenJPA) 
       && ( !org.fundaciobit.genapp.common.utils.Utils.isEmpty(__jpa.entornAplicacios) || org.hibernate.Hibernate.isInitialized(__jpa.getEntornAplicacios())) ) {
      __tmp.setEntornAplicacios(EntornAplicacioJPA.copyJPA(__jpa.getEntornAplicacios(), __alreadyCopied,"AplicacioJPA"));
    }
    // Copia de beans complexes (IMP)

    return __tmp;
  }




}
