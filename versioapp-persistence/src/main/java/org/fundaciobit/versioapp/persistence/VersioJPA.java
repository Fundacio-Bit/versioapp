
package org.fundaciobit.versioapp.persistence;
import org.fundaciobit.versioapp.model.entity.*;
import javax.persistence.Table;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GenerationType;
import javax.persistence.ManyToOne;
import javax.persistence.Index;
import javax.persistence.GeneratedValue;
import javax.persistence.SequenceGenerator;
import javax.persistence.ForeignKey;
import javax.persistence.JoinColumn;
import javax.persistence.FetchType;
import javax.persistence.Id;


@Entity(name = "VersioJPA")
@Table(name = "ver_versio" , indexes = { 
        @Index(name="ver_versio_pk_i", columnList = "versioid"),
        @Index(name="ver_versio_entornapli_fk_i", columnList = "entornaplicacioid")})
@SequenceGenerator(name="VERSIO_SEQ", sequenceName="ver_versio_seq", allocationSize=1, initialValue=1000)
@javax.xml.bind.annotation.XmlRootElement
public class VersioJPA implements Versio {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator="VERSIO_SEQ")
    @Column(name="versioid",nullable = false,length = 19)
    long versioID;

    @Column(name="entornaplicacioid",nullable = false,length = 19)
    long entornAplicacioID;

    @Column(name="versio",nullable = false,length = 100)
    java.lang.String versio;

    @Column(name="build",length = 100)
    java.lang.String build;

    @Column(name="data",nullable = false,length = 29,precision = 6)
    java.sql.Timestamp data;

    @Column(name="altresdades",length = 4096)
    java.lang.String altresDades;



  /** Constructor Buit */
  public VersioJPA() {
  }

  /** Constructor amb tots els camps  */
  public VersioJPA(long versioID , long entornAplicacioID , java.lang.String versio , java.lang.String build , java.sql.Timestamp data , java.lang.String altresDades) {
    this.versioID=versioID;
    this.entornAplicacioID=entornAplicacioID;
    this.versio=versio;
    this.build=build;
    this.data=data;
    this.altresDades=altresDades;
}
  /** Constructor sense valors autoincrementals */
  public VersioJPA(long entornAplicacioID , java.lang.String versio , java.lang.String build , java.sql.Timestamp data , java.lang.String altresDades) {
    this.entornAplicacioID=entornAplicacioID;
    this.versio=versio;
    this.build=build;
    this.data=data;
    this.altresDades=altresDades;
}
  /** Constructor dels valors Not Null */
  public VersioJPA(long versioID , long entornAplicacioID , java.lang.String versio , java.sql.Timestamp data) {
    this.versioID=versioID;
    this.entornAplicacioID=entornAplicacioID;
    this.versio=versio;
    this.data=data;
}
  public VersioJPA(Versio __bean) {
    this.setVersioID(__bean.getVersioID());
    this.setEntornAplicacioID(__bean.getEntornAplicacioID());
    this.setVersio(__bean.getVersio());
    this.setBuild(__bean.getBuild());
    this.setData(__bean.getData());
    this.setAltresDades(__bean.getAltresDades());
	}

	public long getVersioID() {
		return(versioID);
	};
	public void setVersioID(long _versioID_) {
		this.versioID = _versioID_;
	};

	public long getEntornAplicacioID() {
		return(entornAplicacioID);
	};
	public void setEntornAplicacioID(long _entornAplicacioID_) {
		this.entornAplicacioID = _entornAplicacioID_;
	};

	public java.lang.String getVersio() {
		return(versio);
	};
	public void setVersio(java.lang.String _versio_) {
		this.versio = _versio_;
	};

	public java.lang.String getBuild() {
		return(build);
	};
	public void setBuild(java.lang.String _build_) {
		this.build = _build_;
	};

	public java.sql.Timestamp getData() {
		return(data);
	};
	public void setData(java.sql.Timestamp _data_) {
		this.data = _data_;
	};

	public java.lang.String getAltresDades() {
		return(altresDades);
	};
	public void setAltresDades(java.lang.String _altresDades_) {
		this.altresDades = _altresDades_;
	};



  @Override
  public boolean equals(Object __obj) {
  boolean __result;
    if (__obj != null && __obj instanceof Versio) {
      Versio __instance = (Versio)__obj;
      __result = true;
      __result = __result && (this.getVersioID() == __instance.getVersioID()) ;
    } else {
      __result = false;
    }
    return __result;
  }

// IMP Field:entornaplicacioid | Table: ver_entornaplicacio | Type: 1  

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "entornaplicacioid", referencedColumnName ="entornAplicacioID", nullable = false, insertable=false, updatable=false, foreignKey=@ForeignKey(name="ver_versio_entornapli_entor_fk"))
    private EntornAplicacioJPA entornAplicacio;

    public EntornAplicacioJPA getEntornAplicacio() {
    return this.entornAplicacio;
  }

    public  void setEntornAplicacio(EntornAplicacioJPA entornAplicacio) {
    this.entornAplicacio = entornAplicacio;
  }


 // ---------------  STATIC METHODS ------------------
  public static VersioJPA toJPA(Versio __bean) {
    if (__bean == null) { return null;}
    VersioJPA __tmp = new VersioJPA();
    __tmp.setVersioID(__bean.getVersioID());
    __tmp.setEntornAplicacioID(__bean.getEntornAplicacioID());
    __tmp.setVersio(__bean.getVersio());
    __tmp.setBuild(__bean.getBuild());
    __tmp.setData(__bean.getData());
    __tmp.setAltresDades(__bean.getAltresDades());
		return __tmp;
	}


  public static VersioJPA copyJPA(VersioJPA __jpa) {
    return copyJPA(__jpa,new java.util.HashMap<Object,Object>(), null);
  }

  static java.util.Set<VersioJPA> copyJPA(java.util.Set<VersioJPA> __jpaSet,
    java.util.Map<Object,Object> __alreadyCopied, String origenJPA) {
    if (__jpaSet == null) { return null; }
    java.util.Set<VersioJPA> __tmpSet = (java.util.Set<VersioJPA>) __alreadyCopied.get(__jpaSet);
    if (__tmpSet != null) { return __tmpSet; };
    __tmpSet = new java.util.HashSet<VersioJPA>(__jpaSet.size());
    __alreadyCopied.put(__jpaSet, __tmpSet);
    for (VersioJPA __jpa : __jpaSet) {
      __tmpSet.add(copyJPA(__jpa, __alreadyCopied, origenJPA));
    }
    return __tmpSet;
  }

  static VersioJPA copyJPA(VersioJPA __jpa,
    java.util.Map<Object,Object> __alreadyCopied, String origenJPA) {
    if (__jpa == null) { return null; }
    VersioJPA __tmp = (VersioJPA) __alreadyCopied.get(__jpa);
    if (__tmp != null) { return __tmp; };
    __tmp = toJPA(__jpa);
    __alreadyCopied.put(__jpa, __tmp);
    // Copia de beans complexes (EXP)
    // Copia de beans complexes (IMP)
    if(!"EntornAplicacioJPA".equals(origenJPA) && 
       (!org.fundaciobit.genapp.common.utils.Utils.isEmpty(__jpa.entornAplicacio) || org.hibernate.Hibernate.isInitialized(__jpa.getEntornAplicacio()) ) ) {
      __tmp.setEntornAplicacio(EntornAplicacioJPA.copyJPA(__jpa.getEntornAplicacio(), __alreadyCopied,"VersioJPA"));
    }

    return __tmp;
  }




}
