package org.branimirkostic.muzickistudio.model;


import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.Pattern;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

@Entity
@Table(name = "TERMIN")
public class Termin implements Serializable{

    private static final long serialVersionUID = 1L;
    
    @OneToOne(cascade = CascadeType.ALL)
    private TerminSequenceNum id;
    
    @EmbeddedId
    private TerminId pk = new TerminId();
    @SuppressWarnings("unused")
    @Column(name = "DATUM", nullable = false, insertable = false, updatable = false)
    private String datum;
    @SuppressWarnings("unused")
    @Column(name = "SATNICA", nullable = false, insertable = false, updatable = false)
    private Vreme satnica;
    @Column(name = "IZDAT", nullable = false)
    private boolean izdat;
    @SuppressWarnings("unused")
    @ManyToOne(optional=false)
    @JoinColumn(name="SOBA_ID", insertable = false, updatable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Soba soba;
    @ManyToOne(optional=false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Korisnik korisnik;

	public Termin() {
    }
	
    public Termin(TerminSequenceNum id, TerminId pk, String datum, Vreme satnica, boolean izdat, Soba soba,
			Korisnik korisnik) {
    	this.id = id;
		this.pk = pk;
		this.datum = datum;
		this.satnica = satnica;
                this.izdat = izdat;
		this.soba = soba;
		this.korisnik = korisnik;
	}
    
    
    
    public TerminSequenceNum getId() {
        return id;
    }

    public void setId(TerminSequenceNum id) {
        this.id = id;
    }

    @Pattern(regexp="^(0[1-9]|[12][0-9]|3[01])[. /.](0[1-9]|1[012])[. /.](19|20)\\d\\d$",
        message= "Morate uneti datum u odgovarajucem formatu: dd.MM.yyyy")
    public String getDatum() {
        return pk.getDatum();
    }

    public void setDatum(String datum) {
        pk.setDatum(datum);
    }

    public Vreme getSatnica() {
        return pk.getSatnica();
    }

    public void setSatnica(Vreme satnica) {
        pk.setSatnica(satnica);
    }
    
    public Korisnik getKorisnik() {
        return korisnik;
    }

    public void setKorisnik(Korisnik korisnik) {
        this.korisnik = korisnik;
    }
    public Soba getSoba() {
        return pk.getSoba();
    }

    public void setSoba(Soba soba) {
        pk.setSoba(soba);
    }

    public boolean isIzdat() {
        return izdat;
    }

    public void setIzdat(boolean izdat) {
        this.izdat = izdat;
    }
}

