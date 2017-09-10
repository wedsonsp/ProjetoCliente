/*
 * Globalcode - "The Developers Company"
 * 
 * Academia do Java
 * 
 */
package br.com.wedson.aj3.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import br.com.wedson.aj3.beans.Cliente;
import br.com.wedson.aj3.exception.WedsonException;
import br.com.wedson.aj3.util.ConnectionManager;

public class ClientesDAOImpl implements IClientesDAO {

    private final static String SALVAR_CLIENTE = "INSERT INTO tb_customer_account(cpf,nome,status,valor_total) VALUES (?,?,?,?)";
    private final static String CREATE_TABLE = "CREATE TABLE  IF NOT EXISTS tb_customer_account(id int(3) NOT NULL AUTO_INCREMENT PRIMARY KEY, cpf VARCHAR(20) NOT NULL, nome varchar(20) NOT NULL, status char(1) NOT NULL, valor_total decimal(9,2))";
    private final static String DELETE_CLIENTE = "DELETE FROM  tb_customer_account WHERE cpf = '";
    private final static String GET_ALL_CLIENTES = "SELECT * FROM  tb_customer_account";
    private final static String GET_ALL_CLIENTESRANGE = "SELECT * FROM  tb_customer_account WHERE valor_total BETWEEN 1500 AND 2700";
    private final static String GET_ALL_CLIENTESRANGEORDERBY = "SELECT * FROM  tb_customer_account WHERE valor_total BETWEEN 1500 AND 2700 order by valor_total desc";
    private final static String GET_ALL_CLIENTESMEDIA = "SELECT id, cpf, nome, status, ROUND(AVG(valor_total)) AS ValorTotal FROM  tb_customer_account WHERE valor_total BETWEEN 100 AND 400";
    private final static String GET_CLIENTE_BY_CPF = "SELECT * FROM  tb_customer_account WHERE cpf = ?";

    public void createTable() throws WedsonException {
        Connection conn = null;
        Statement stmt = null;
        try {
            conn = ConnectionManager.getConnection();
            stmt = conn.createStatement();
            stmt.executeUpdate(CREATE_TABLE);
        } catch (SQLException e) {
            throw new WedsonException(
                    "Erro ao criar a tabela de clientes : " + CREATE_TABLE, e);
        } finally {
            ConnectionManager.closeAll(conn, stmt);
        }
    }

    /*
	 * @see
	 * br.com.wedson.aj3.dao.ClientesDAO#excluir(br.com.wedson.beans
	 * .Cliente)
     */
    public void excluir(String cpf) throws WedsonException {
        Connection conn = null;
        Statement stmt = null;
        try {
            // Abertura da conexao
            conn = ConnectionManager.getConnection();
            // Criacao do Statement
            stmt = conn.createStatement();
            // Execucao da query
            int numeroLinhas = stmt.executeUpdate(DELETE_CLIENTE + cpf + "'");
            // Impressao do numero de linhas alteradas
            System.out.println("Numero de clientes excluidos: " + numeroLinhas);
        } catch (SQLException e) {
            throw new WedsonException("Erro ao excluir cliente: "
                    + DELETE_CLIENTE, e);
        } finally {
            // Fechamento da Connection e Statement
            ConnectionManager.closeAll(conn, stmt);
        }
    }

    /*
	 * @see
	 * br.com.wedson.aj3.dao.ClientesDAO#salvar(br.com.wedson.beans.
	 * Cliente)
     */
    public void salvar(Cliente cliente) throws WedsonException {
        Connection conn = null;
        PreparedStatement stmt = null;
        try {
            // Abertura da conexao
            conn = ConnectionManager.getConnection();
            // Criacao da PreparedStatement
            stmt = conn.prepareStatement(SALVAR_CLIENTE);
            // Atribuicao de uma String para a 1a. interrogacao (CPF ou CNPJ)
            stmt.setString(1, cliente.getCpf_cnpj());
            // Atribuicao de uma String para a 2a. interrogacao (Nome)
            stmt.setString(2, cliente.getNm_custumer());
            // Atribuicao de uma String para a 3a. interrogacao (Status)
            stmt.setString(3, String.valueOf(cliente.getIs_active()));
            // Atribuicao de uma String para a 4a. interrogacao (Valor Total)
            stmt.setDouble(4, cliente.getVl_total());
            // Executar a operacao de gravar os dados na base
            stmt.executeUpdate();

        } catch (SQLException e) {
            throw new WedsonException(
                    "Nao foi possivel salvar o cliente na base de dados.", e);
        } finally {
            // Fechamento da Connection e Statement
            ConnectionManager.closeAll(conn, stmt);
        }
    }

    /*
	 * @see br.com.wedson.aj3.dao.ClientesDAO#getAllClientes()
     */
    public List getAllClientes() throws WedsonException {
        // Criacao da lista para armazenar os clientes
        ArrayList clientes = new ArrayList();

        Connection conn = null;
        Statement stmt = null;
        ResultSet rs = null;
        try {
            // Abertura da conexao
            conn = ConnectionManager.getConnection();
            // Criacao do statement
            stmt = conn.createStatement();
            // Execucao da consulta
            rs = stmt.executeQuery(GET_ALL_CLIENTES);
            // Leitura do ResultSet
            while (rs.next()) {
                // leitura dos dados retornados
                int id = rs.getInt("id");
                String cpf = rs.getString("cpf");
                String nome = rs.getString("nome");
                char status = rs.getString("status").charAt(0);
                Double valor_total = rs.getDouble("valor_total");
                // criacao do cliente
                Cliente c = new Cliente(id, cpf, nome, status, valor_total);
                // adicao do cliente na lista
                clientes.add(c);
            }
            return clientes;
        } catch (SQLException e) {
            throw new WedsonException(
                    "Nao foi possivel recuperar os clientes da base de dados.",
                    e);
        } finally {
            // Fechamento da Connection, Statement e ResultSet
            ConnectionManager.closeAll(conn, stmt, rs);
        }
    }

    /*
	 * @see br.com.wedson.aj3.dao.ClientesDAO#getAllClientes()
     */
    public List getAllClientesRange() throws WedsonException {
        // Criacao da lista para armazenar os clientes
        ArrayList clientes = new ArrayList();

        Connection conn = null;
        Statement stmt = null;
        ResultSet rs = null;
        try {
            // Abertura da conexao
            conn = ConnectionManager.getConnection();
            // Criacao do statement
            stmt = conn.createStatement();
            // Execucao da consulta
            rs = stmt.executeQuery(GET_ALL_CLIENTESRANGE);
            // Leitura do ResultSet
            while (rs.next()) {
                // leitura dos dados retornados
                int id = rs.getInt("id");
                String cpf = rs.getString("cpf");
                String nome = rs.getString("nome");
                char status = rs.getString("status").charAt(0);
                Double valor_total = rs.getDouble("valor_total");
                // criacao do cliente
                Cliente c = new Cliente(id, cpf, nome, status, valor_total);
                // adicao do cliente na lista
                clientes.add(c);
            }
            return clientes;
        } catch (SQLException e) {
            throw new WedsonException(
                    "Nao foi possivel recuperar os clientes da base de dados.",
                    e);
        } finally {
            // Fechamento da Connection, Statement e ResultSet
            ConnectionManager.closeAll(conn, stmt, rs);
        }
    }

    /*
	 * @see br.com.wedson.aj3.dao.ClientesDAO#getAllClientes()
     */
    public List getAllClientesMedia() throws WedsonException {
        // Criacao da lista para armazenar os clientes
        ArrayList clientes = new ArrayList();

        Connection conn = null;
        Statement stmt = null;
        ResultSet rs = null;
        try {
            // Abertura da conexao
            conn = ConnectionManager.getConnection();
            // Criacao do statement
            stmt = conn.createStatement();
            // Execucao da consulta
            rs = stmt.executeQuery(GET_ALL_CLIENTESMEDIA);
            // Leitura do ResultSet
            while (rs.next()) {
                // leitura dos dados retornados
                Double valor_total = rs.getDouble("ValorTotal");
                // criacao do cliente
                Cliente c = new Cliente(valor_total);

                // adicao do cliente na lista
                clientes.add(c);
            }
            return clientes;
        } catch (SQLException e) {
            throw new WedsonException(
                    "Nao foi possivel recuperar os clientes da base de dados.",
                    e);
        } finally {
            // Fechamento da Connection, Statement e ResultSet
            ConnectionManager.closeAll(conn, stmt, rs);
        }
    }

    /*
	 * @see br.com.wedson.aj3.dao.ClientesDAO#getAllClientes()
     */
    public List getAllClientesRangeOrderBy() throws WedsonException {
        // Criacao da lista para armazenar os clientes
        ArrayList clientes = new ArrayList();

        Connection conn = null;
        Statement stmt = null;
        ResultSet rs = null;
        try {
            // Abertura da conexao
            conn = ConnectionManager.getConnection();
            // Criacao do statement
            stmt = conn.createStatement();
            // Execucao da consulta
            rs = stmt.executeQuery(GET_ALL_CLIENTESRANGEORDERBY);
            // Leitura do ResultSet
            while (rs.next()) {
                // leitura dos dados retornados
                int id = rs.getInt("id");
                String cpf = rs.getString("cpf");
                String nome = rs.getString("nome");
                char status = rs.getString("status").charAt(0);
                Double valor_total = rs.getDouble("valor_total");
                // criacao do cliente
                Cliente c = new Cliente(id, cpf, nome, status, valor_total);
                // adicao do cliente na lista
                clientes.add(c);
            }
            return clientes;
        } catch (SQLException e) {
            throw new WedsonException(
                    "Nao foi possivel recuperar os clientes da base de dados.",
                    e);
        } finally {
            // Fechamento da Connection, Statement e ResultSet
            ConnectionManager.closeAll(conn, stmt, rs);
        }
    }


    /*
	 * (non-Javadoc)
	 * 
	 * @see br.com.wedson.aj3.dao.ClientesDAO#getClienteByID(int)
     */
    public Cliente getClienteByCPF(String cpf) throws WedsonException {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        Cliente cliente = null;
        try {
            // Abertura da conexao
            conn = ConnectionManager.getConnection();
            // Criacao do PreparedStatement
            stmt = conn.prepareStatement(GET_CLIENTE_BY_CPF);
            // Atribuicao de uma String para a 1a. interrogacao (cpf)
            stmt.setString(1, cpf);
            // Execucao do PreparedStatement
            rs = stmt.executeQuery();
            // Leitura do ResultSet com geracao de um objeto Cliente
            while (rs.next()) {
                cliente = new Cliente(rs
                        .getInt("id"), rs.getString("nome"), rs
                        .getString("cpf"), rs.getString("status").charAt(0), rs
                        .getDouble("valor_total"));
            }
        } catch (SQLException e) {
            throw new WedsonException(
                    "Nao foi possivel encontrar o cliente na base de dados.", e);
        } finally {
            // Fechamento da Connection, Statement e ResultSet
            ConnectionManager.closeAll(conn, stmt, rs);
        }
        // Retorna os valores para o metodo
        return cliente;
    }
}
