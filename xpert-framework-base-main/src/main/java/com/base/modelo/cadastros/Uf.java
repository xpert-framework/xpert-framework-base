package com.base.modelo.cadastros;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;
import javax.persistence.SequenceGenerator;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import org.hibernate.validator.constraints.NotBlank;

/**
 *
 * @author ayslanms
 */
@Entity
public class Uf implements Serializable {

    @Id
    @SequenceGenerator(name = "Uf", allocationSize = 1, sequenceName = "uf_id_seq")
    @GeneratedValue(generator = "Uf")
    private Long id;

    @Column(unique = true)
    @NotNull
    private Long codigoIbge;

    @Size(max = 200)
    @NotBlank
    private String nome;

    @Column(unique = true)
    @Size(max = 2)
    @NotBlank
    private String sigla;

    @NotNull
    private Long codigoIbgeRegiao;

    @Size(max = 200)
    @NotBlank
    private String nomeRegiao;

    @OrderBy("nome")
    @OneToMany(mappedBy = "uf")
    private List<Municipio> municipios;

    public Uf() {
    }

    public Uf(Long codigoIbge, String nome, String sigla, Long codigoIbgeRegiao, String nomeRegiao) {
        this.codigoIbge = codigoIbge;
        this.nome = nome;
        this.sigla = sigla;
        this.codigoIbgeRegiao = codigoIbgeRegiao;
        this.nomeRegiao = nomeRegiao;
    }

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

    public String getSigla() {
        return sigla;
    }

    public void setSigla(String sigla) {
        if (sigla != null) {
            sigla = sigla.toUpperCase().trim();
        }
        this.sigla = sigla;
    }

    public List<Municipio> getMunicipios() {
        return municipios;
    }

    public void setMunicipios(List<Municipio> municipios) {
        this.municipios = municipios;
    }

    public Long getCodigoIbgeRegiao() {
        return codigoIbgeRegiao;
    }

    public void setCodigoIbgeRegiao(Long codigoIbgeRegiao) {
        this.codigoIbgeRegiao = codigoIbgeRegiao;
    }

    public String getNomeRegiao() {
        return nomeRegiao;
    }

    public void setNomeRegiao(String nomeRegiao) {
        if (nomeRegiao != null) {
            nomeRegiao = nomeRegiao.toUpperCase().trim();
        }
        this.nomeRegiao = nomeRegiao;
    }

}
