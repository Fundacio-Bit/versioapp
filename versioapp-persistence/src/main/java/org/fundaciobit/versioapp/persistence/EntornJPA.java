
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


@Entity(name = "EntornJPA")
@Table(name = "ver_entorn" , indexes = { 
        @Index(name="ver_entorn_pk_i", columnList = "entornid")})
@SequenceGenerator(name="ENTORN_SEQ", sequenceName="ver_entorn_seq", allocationSize=1, initialValue=1000)
@javax.xml.bind.annotation.XmlRootElement
public class EntornJPA implements Entorn {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator="ENTORN_SEQ")
    @Column(name="entornid",nullable = false,length = 19)
    long entornID;

    @Column(name="nom",nullable = false,length = 100)
    java.lang.String nom;

    @Column(name="domini",nullable = false,length = 255)
    java.lang.String domini;

    @org.hibernate.annotations.ColumnDefault("0")
    @Column(name="ordre",nullable = false,length = 10)
    int ordre = 0;



  /** Constructor Buit */
  public EntornJPA() {
  }

  /** Constructor amb tots els camps  */
  public EntornJPA(long entornID , java.lang.String nom , java.lang.String domini , int ordre) {
    this.entornID=entornID;
    this.nom=nom;
    this.domini=domini;
    this.ordre=ordre;
}
  /** Constructor sense valors autoincrementals */
  public EntornJPA(java.lang.String nom , java.lang.String domini , int ordre) {
    this.nom=nom;
    this.domini=domini;
    this.ordre=ordre;
}
  public EntornJPA(Entorn __bean) {
    this.setEntornID(__bean.getEntornID());
    this.setNom(__bean.getNom());
    this.setDomini(__bean.getDomini());
    this.setOrdre(__bean.getOrdre());
	}

	public long getEntornID() {
		return(entornID);
	};
	public void setEntornID(long _entornID_) {
		this.entornID = _entornID_;
	};

	public java.lang.String getNom() {
		return(nom);
	};
	public void setNom(java.lang.String _nom_) {
		this.nom = _nom_;
	};

	public java.lang.String getDomini() {
		return(domini);
	};
	public void setDomini(java.lang.String _domini_) {
		this.domini = _domini_;
	};

	public int getOrdre() {
		return(ordre);
	};
	public void setOrdre(int _ordre_) {
		this.ordre = _ordre_;
	};



  @Override
  public boolean equals(Object __obj) {
  boolean __result;
    if (__obj != null && __obj instanceof Entorn) {
      Entorn __instance = (Entorn)__obj;
      __result = true;
      __result = __result && (this.getEntornID() == __instance.getEntornID()) ;
    } else {
      __result = false;
    }
    return __result;
  }

// EXP  Field:entornid | Table: ver_entornaplicacio | Type: 0  

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "entorn")
    private Set<EntornAplicacioJPA> entornAplicacios = new HashSet<EntornAplicacioJPA>(0);
    public  Set<EntornAplicacioJPA> getEntornAplicacios() {
    return this.entornAplicacios;
  }

    public void setEntornAplicacios(Set<EntornAplicacioJPA> entornAplicacios) {
      this.entornAplicacios = entornAplicacios;
    }



 // ---------------  STATIC METHODS ------------------
  public static EntornJPA toJPA(Entorn __bean) {
    if (__bean == null) { return null;}
    EntornJPA __tmp = new EntornJPA();
    __tmp.setEntornID(__bean.getEntornID());
    __tmp.setNom(__bean.getNom());
    __tmp.setDomini(__bean.getDomini());
    __tmp.setOrdre(__bean.getOrdre());
		return __tmp;
	}


  public static EntornJPA copyJPA(EntornJPA __jpa) {
    return copyJPA(__jpa,new java.util.HashMap<Object,Object>(), null);
  }

  static java.util.Set<EntornJPA> copyJPA(java.util.Set<EntornJPA> __jpaSet,
    java.util.Map<Object,Object> __alreadyCopied, String origenJPA) {
    if (__jpaSet == null) { return null; }
    java.util.Set<EntornJPA> __tmpSet = (java.util.Set<EntornJPA>) __alreadyCopied.get(__jpaSet);
    if (__tmpSet != null) { return __tmpSet; };
    __tmpSet = new java.util.HashSet<EntornJPA>(__jpaSet.size());
    __alreadyCopied.put(__jpaSet, __tmpSet);
    for (EntornJPA __jpa : __jpaSet) {
      __tmpSet.add(copyJPA(__jpa, __alreadyCopied, origenJPA));
    }
    return __tmpSet;
  }

  static EntornJPA copyJPA(EntornJPA __jpa,
    java.util.Map<Object,Object> __alreadyCopied, String origenJPA) {
    if (__jpa == null) { return null; }
    EntornJPA __tmp = (EntornJPA) __alreadyCopied.get(__jpa);
    if (__tmp != null) { return __tmp; };
    __tmp = toJPA(__jpa);
    __alreadyCopied.put(__jpa, __tmp);
    // Copia de beans complexes (EXP)
    if(!"EntornAplicacioJPA".equals(origenJPA) 
       && ( !org.fundaciobit.genapp.common.utils.Utils.isEmpty(__jpa.entornAplicacios) || org.hibernate.Hibernate.isInitialized(__jpa.getEntornAplicacios())) ) {
      __tmp.setEntornAplicacios(EntornAplicacioJPA.copyJPA(__jpa.getEntornAplicacios(), __alreadyCopied,"EntornJPA"));
    }
    // Copia de beans complexes (IMP)

    return __tmp;
  }




}
