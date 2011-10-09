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
import javax.validation.constraints.Pattern;

import org.hibernate.annotations.ForeignKey;
import org.hibernate.annotations.IndexColumn;

@Entity
@Table(name = "KORISNIK")
public class Korisnik implements Serializable{

    private static final long serialVersionUID = 1L;
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "ID")
    private Long id;
    @Column(name = "KORIME", nullable = false, unique = true)
    @Pattern(regexp = "^[a-zA-Z0-9]{5,15}$", message="Korisnicko ime sadrzi izmedju 5 i 15 karaktera.")
    private String korIme;
    @Column(name = "IME", nullable = false)
    @Pattern(regexp = "^[A-Z][a-z]+$", message="Morate uneti ime u odgovarajucem formatu.")
    private String ime;
    @Column(name = "PREZIME", nullable = false)
    @Pattern(regexp = "^[A-Z][a-z]+$", message="Morate uneti prezime u odgovarajucem formatu.")
    private String prezime;
    @Column(name = "LICNIBROJ", nullable = false)
    @Pattern(regexp = "^[a-zA-Z0-9]{9,15}$", message="Licni broj sadrzi od 9 do 15 karaktera.")
    private String licniBroj;
    @Column(name = "LOZINKA", nullable = false)
    @Pattern(regexp = "^[a-zA-Z0-9]{5,15}$", message="Lozinka sadrzi izmedju 5 i 15 karaktera.")
    private String lozinka;
    private String lozinka2;
    @Column(name = "PRIVILEGIJA", nullable = false)
    private int privilegija;
    @OneToMany(cascade = CascadeType.ALL)
    @ForeignKey(name = "FK_KORISNIK_TERMINI_KORISNIK", inverseName = "FK_KORISNIK_TERMINI_TERMINI")
    @IndexColumn(name = "TERMINI_INDEX")
    private List<Termin> termini = new ArrayList<Termin>();
    
    public Korisnik() {
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
    
    public String getIme() {
        return ime;
    }

    public void setIme(String ime) {
        this.ime = ime;
    }

    public String getKorIme() {
        return korIme;
    }

    public void setKorIme(String korIme) {
        this.korIme = korIme;
    }

    public String getLicniBroj() {
        return licniBroj;
    }

    public void setLicniBroj(String licniBroj) {
        this.licniBroj = licniBroj;
    }

    public String getLozinka() {
        return lozinka;
    }

    public void setLozinka(String lozinka) {
        this.lozinka = lozinka;
    }
    
    public String getLozinka2() {
        return lozinka2;
    }

    public void setLozinka2(String lozinka2) {
        this.lozinka2 = lozinka2;
    }
    
    public String getPrezime() {
        return prezime;
    }

    public void setPrezime(String prezime) {
        this.prezime = prezime;
    }

    public int getPrivilegija() {
        return privilegija;
    }

    public void setPrivilegija(int privilegija) {
        this.privilegija = privilegija;
    }

    public List<Termin> getTermini() {
        return termini;
    }
    
    public void setTermini(List<Termin> termini){
    	this.termini = termini;
    }
}
