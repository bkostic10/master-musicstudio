package org.branimirkostic.muzickistudio.model;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import org.hibernate.annotations.ForeignKey;
import org.hibernate.annotations.IndexColumn;
import org.hibernate.validator.constraints.NotEmpty;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;


@Entity
@Table(name = "STUDIO")
public class Studio implements Serializable{

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "ID")
    private Long id;
    @Column(name = "NAZIV", nullable = false)
    @NotEmpty(message="Morate uneti naziv studija.")
    private String naziv;
    @Column(name = "ULICA", nullable = false)
    @NotEmpty(message="Morate uneti naziv ulice.")
    private String ulica;
    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "STUDIO")
    @ForeignKey(name = "FK_STUDIO_STUDIO")
    @IndexColumn(name = "SOBE_INDEX")

    private List<Soba> sobe = new ArrayList<Soba>();

    public Studio() {
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

    public String getUlica() {
        return ulica;
    }

    public void setUlica(String ulica) {
        this.ulica = ulica;
    } 

    public List<Soba> getSobe() {
        return sobe;
    }
    public void setSobe(List<Soba> sobe){
    	this.sobe = sobe;
    }
    
    public int vratiSobeZaProbe(){
    	int no = 0;
    	for(Soba s : sobe ){
            if(s.getTip() == TipSobe.Probe)
                no++;
    	}
    	return no;
    }    
    public int vratiSobeZaSnimanje(){
    	int no = 0;
    	for(Soba s : sobe ){
            if(s.getTip() == TipSobe.Snimanje)
                no++;
    	}
    	return no;
    }
    
    public void sacuvajSobu(Soba soba) {
    	getSobe().add(soba);
    }

    public void obrisiSobu(Soba soba) {
    	getSobe().remove(soba);
    }

    public void obrisiSveSobe() {
    	getSobe().clear();
    }

}


