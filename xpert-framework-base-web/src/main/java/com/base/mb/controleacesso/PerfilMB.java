package com.base.mb.controleacesso;

import com.base.bo.controleacesso.PerfilBO;
import com.base.bo.controleacesso.PermissaoBO;
import com.base.modelo.controleacesso.Perfil;
import com.base.modelo.controleacesso.Permissao;
import java.io.Serializable;
import com.xpert.core.crud.AbstractBaseBean;
import com.xpert.core.crud.AbstractBusinessObject;
import javax.ejb.EJB;
import com.xpert.core.exception.BusinessException;
import com.xpert.faces.utils.FacesMessageUtils;
import java.util.ArrayList;
import java.util.List;
import javax.faces.view.ViewScoped;
import javax.inject.Named;
import org.primefaces.model.TreeNode;

/**
 *
 * @author Ayslan
 */
@Named
@ViewScoped
public class PerfilMB extends AbstractBaseBean<Perfil> implements Serializable {

    @EJB
    private PerfilBO perfilBO;
    @EJB
    private PermissaoBO permissaoBO;
    private TreeNode permissoes;
    private TreeNode[] permissoesSelecionadas;
    private TreeNode permissoesMenu;
    private TreeNode[] permissoesSelecionadasMenu;

    @Override
    public void init() {
        carregarTree();
    }

    public void carregarTree() {
        permissoes = permissaoBO.getTreeNode(getEntity());
        if (getEntity().getId() != null) {
            permissoesMenu = permissaoBO.getTreeNodeMenu(getEntity());
        }
    }

    public void detail() {
        carregarTree();
    }

    public void setarPermissoes() {
        List<Permissao> permissoesList = new ArrayList<Permissao>();
        for (TreeNode node : permissoesSelecionadas) {
            permissoesList.add((Permissao) node.getData());
        }
        getEntity().setPermissoes(permissoesList);
        List<Permissao> permissoesMenu = new ArrayList<Permissao>();
        if (permissoesSelecionadasMenu != null) {
            for (TreeNode node : permissoesSelecionadasMenu) {
                permissoesMenu.add((Permissao) node.getData());
            }
        }
        getEntity().setPermissoesAtalho(permissoesMenu);
    }

    @Override
    public void save() {
        try {
            setarPermissoes();
            perfilBO.save(getEntity());
            carregarTree();
            FacesMessageUtils.sucess();
        } catch (BusinessException ex) {
            FacesMessageUtils.error(ex);
        }
    }

    @Override
    public AbstractBusinessObject getBO() {
        return perfilBO;
    }

    @Override
    public String getDataModelOrder() {
        return "descricao";
    }

    public TreeNode getPermissoes() {
        return permissoes;
    }

    public void setPermissoes(TreeNode permissoes) {
        this.permissoes = permissoes;
    }

    public TreeNode[] getPermissoesSelecionadas() {
        return permissoesSelecionadas;
    }

    public void setPermissoesSelecionadas(TreeNode[] permissoesSelecionadas) {
        this.permissoesSelecionadas = permissoesSelecionadas;
    }

    public TreeNode getPermissoesMenu() {
        return permissoesMenu;
    }

    public void setPermissoesMenu(TreeNode permissoesMenu) {
        this.permissoesMenu = permissoesMenu;
    }

    public TreeNode[] getPermissoesSelecionadasMenu() {
        return permissoesSelecionadasMenu;
    }

    public void setPermissoesSelecionadasMenu(TreeNode[] permissoesSelecionadasMenu) {
        this.permissoesSelecionadasMenu = permissoesSelecionadasMenu;
    }
}
