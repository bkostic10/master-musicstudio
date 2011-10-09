package org.branimirkostic.muzickistudio.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.ManyToOne;
import javax.validation.constraints.Pattern;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

@Embeddable
public class TerminId implements Serializable{

    private static final long serialVersionUID = 1L;
		
    @Column(name = "DATUM", nullable = false)
    @Pattern(regexp="^(0[1-9]|[12][0-9]|3[01])[. /.](0[1-9]|1[012])[. /.](19|20)\\d\\d$",
        message= "Morate uneti datum u odgovarajucem formatu: dd.MM.yyyy")
    private String datum;
    @Column(name = "SATNICA", nullable = false)
    private Vreme satnica;
    @ManyToOne(optional=false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Soba soba;
    
    public TerminId(){}

    public String getDatum() {
        return datum;
    }

    public void setDatum(String datum) {
        this.datum = datum;
    }

    public Vreme getSatnica() {
        return satnica;
    }

    public void setSatnica(Vreme satnica) {
        this.satnica = satnica;
    }
    public Soba getSoba() {
        return soba;
    }

    public void setSoba(Soba soba) {
        this.soba = soba;
    }
    public boolean equals(Object o) {
        if (o != null && o instanceof TerminId) {
            TerminId that = (TerminId)o;
            return this.getDatum().equals(that.getDatum()) &&
            this.getSatnica() == that.getSatnica()&&
            this.getSoba().getId() == that.getSoba().getId();
        }
        else {
            return false;
        }
    }
    public int hashCode() {
        return datum.hashCode() + satnica.hashCode() + soba.hashCode();
    }
    
}
