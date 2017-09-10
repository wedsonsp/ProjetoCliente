/*
 * Wedson - "The Developers Company"
 * 
 * Esta e uma interface que especifica metodos para obten�ao e armazenamento de clientes,
 * nao importando onde estes objetos serao persistidos, ex: arquivo, collections, 
 * banco de dados, etc...
 * 
 */
package br.com.wedson.aj3.dao;

import java.util.List;

import br.com.wedson.aj3.beans.Cliente;
import br.com.wedson.aj3.exception.WedsonException;

public interface IClientesDAO {

    /**
     * @param cliente Cliente a ser inserido na fonte de dados em questao
     * @throws WedsonException
     */
    public abstract void salvar(Cliente cliente) throws WedsonException;

    /**
     * @param CPF do Cliente a ser excluido da fonte de dados em questao
     * @throws WedsonException
     */
    public abstract void excluir(String cpf) throws WedsonException;

    /**
     * @return java.util.List contendo todos os clientes contidos na fonte da
     * dados em questao
     * @throws WedsonException
     */
    public abstract List getAllClientes() throws WedsonException;

    /**
     * @return java.util.List contendo todos os clientes contidos na fonte da
     * dados em questao
     * @throws WedsonException
     */
    public abstract List getAllClientesRange() throws WedsonException;

    /**
     * @return java.util.List contendo todos os clientes contidos na fonte da
     * dados em questao
     * @throws WedsonException
     */
    public abstract List getAllClientesMedia() throws WedsonException;

    /**
     * @return java.util.List contendo todos os clientes contidos na fonte da
     * dados em questao
     * @throws WedsonException
     */
    public abstract List getAllClientesRangeOrderBy() throws WedsonException;

    /**
     * @param String CPF ou CNPJ do cliente a ser pesquisado no "banco de dados"
     * @return Cliente
     * @throws WedsonException
     */
    public abstract Cliente getClienteByCPF(String cpf) throws WedsonException;
}
