package com.base.vo.dashboard;

import java.util.Objects;

/**
 *
 * @author ayslanms
 */
public class TabelaAuditoria {
    
    private String nome;
    private Class entity;

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Class getEntity() {
        return entity;
    }

    public void setEntity(Class entity) {
        this.entity = entity;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 31 * hash + Objects.hashCode(this.entity);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final TabelaAuditoria other = (TabelaAuditoria) obj;
        if (!Objects.equals(this.entity, other.entity)) {
            return false;
        }
        return true;
    }
    
    
    
}
