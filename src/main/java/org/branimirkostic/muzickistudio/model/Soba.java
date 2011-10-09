package org.branimirkostic.muzickistudio.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.ForeignKey;
import org.hibernate.annotations.IndexColumn;
import org.hibernate.validator.constraints.NotEmpty;

@Entity
@Table(name = "SOBA")
public class Soba implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "ID")
    private Long id;
    @Column(name = "NAZIV", nullable = false, length = 50)
    @NotEmpty(message="Morate uneti naziv sobe.")
    private String naziv;
    @Column(name = "OPIS", nullable = false)
    @NotEmpty(message="Morate uneti opis sobe.")
    private String opis;
    @Column(name = "TIP", nullable = false)
    private TipSobe tip;
    @OneToMany(cascade = CascadeType.ALL)
    @ForeignKey(name = "FK_SOBA_TERMINI_SOBA", inverseName = "FK_SOBA_TERMINI_TERMINI")
    @IndexColumn(name = "TERMINI_INDEX")
    private List<Termin> termini = new ArrayList<Termin>();

    public Soba() {
    }

    public Long getId() {
        return id;
    }

    protected void setId(Long id) {
        if ((this.id != null) && !this.id.equals(id)) {
            throw new IllegalArgumentException(
                "Not allowed to change the id property.");
        }
        this.id = id;
    }

    public String getNaziv() {
        return naziv;
    }

    public void setNaziv(String naziv) {
        this.naziv = naziv;
    }

    public String getOpis() {
        return opis;
    }

    public void setOpis(String opis) {
        this.opis = opis;
    }

    public TipSobe getTip() {
        return tip;
    }

    public void setTip(TipSobe tip) {
        this.tip = tip;
    }

    public List<Termin> getTermini() {
        return termini;
    }
    
    public void setTermini(List<Termin> termini){
    	this.termini = termini;
    }

}
