package com.base.modelo.cadastros;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import org.hibernate.validator.constraints.NotBlank;

/**
 *
 * @author ayslanms
 */
@Entity
@Table(
        uniqueConstraints = {
            @UniqueConstraint(columnNames = {"nome", "uf_id"}),
            @UniqueConstraint(columnNames = {"codigoIbge"})
        }
)
public class Municipio implements Serializable {

    @Id
    @SequenceGenerator(name = "Municipio", allocationSize = 1, sequenceName = "municipio_id_seq")
    @GeneratedValue(generator = "Municipio")
    private Long id;

    @Column(unique = true)
    @NotNull
    private Long codigoIbge;

    @Size(max = 200)
    @NotBlank
    private String nome;

    private boolean capital;

    @ManyToOne
    @NotNull
    private Uf uf;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getCodigoIbge() {
        return codigoIbge;
    }

    public void setCodigoIbge(Long codigoIbge) {
        this.codigoIbge = codigoIbge;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        if (nome != null) {
            nome = nome.toUpperCase().trim();
        }
        this.nome = nome;
    }

    public boolean isCapital() {
        return capital;
    }

    public void setCapital(boolean capital) {
        this.capital = capital;
    }

    public Uf getUf() {
        return uf;
    }

    public void setUf(Uf uf) {
        this.uf = uf;
    }

}
