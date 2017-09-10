/*
 * Wedson - "The Developers Company"
 * 
 * 
 */
package br.com.wedson.aj3.beans;


public class Cliente {
    //Declarando os atributos
    private int id_custumer;
    private String cpf_cnpj;
    private String nm_custumer;
    private char is_active;
    private double vl_total;

    /**
     * Este construtor podera ser utilizado quando o id do cliente precisar ser
     * atribuido depois, como por exemplo quando e necessario criar o cliente
     * para salvar no banco de dados, onde o id e gerado automaticamente.
     *
     * @param id_custumer
     * @param cpf_cnpj
     * @param nm_custumer
     * @param is_active
     * @param vl_total
     *
     */
    public Cliente(int id_custumer, String cpf_cnpj, String nm_custumer, char is_active, double vl_total) {
        this.id_custumer = id_custumer;
        this.cpf_cnpj = cpf_cnpj;
        this.nm_custumer = nm_custumer;
        this.is_active = is_active;
        this.vl_total = vl_total;
    }

    //Contrutor utilizado no Resultset da queri que vai mostrar apenas o valor total na consulta.
    public Cliente(double vl_total) {
        this.vl_total = vl_total;
    }

    /**
     * @return the id_custumer
     */
    public int getId_custumer() {
        return id_custumer;
    }

    /**
     * @param id_custumer the id_custumer to set
     */
    public void setId_custumer(int id_custumer) {
        this.id_custumer = id_custumer;
    }

    /**
     * @return the cpf_cnpj
     */
    public String getCpf_cnpj() {
        return cpf_cnpj;
    }

    /**
     * @param cpf_cnpj the cpf_cnpj to set
     */
    public void setCpf_cnpj(String cpf_cnpj) {
        this.cpf_cnpj = cpf_cnpj;
    }

    /**
     * @return the nm_custumer
     */
    public String getNm_custumer() {
        return nm_custumer;
    }

    /**
     * @param nm_custumer the nm_custumer to set
     */
    public void setNm_custumer(String nm_custumer) {
        this.nm_custumer = nm_custumer;
    }

    /**
     * @return the is_active
     */
    public char getIs_active() {
        return is_active;
    }

    /**
     * @param is_active the is_active to set
     */
    public void setIs_active(char is_active) {
        this.is_active = is_active;
    }

    /**
     * @return the vl_total
     */
    public double getVl_total() {
        return vl_total;
    }

    /**
     * @param vl_total the vl_total to set
     */
    public void setVl_total(double vl_total) {
        this.vl_total = vl_total;
    }

    //Método para converter os valores do objeto para String.
    public String toString() {
        /*Os valores da queri para pegar o valor total tem somente o campo valor total
        então, para mostrar somente o campo do valor total utilizei esse if e no else mostra todos
        os campos para o caso de querie que mostra todos os campos na listagem.
        */
        if (getId_custumer() == 0 || getCpf_cnpj() == null) {

            String cliente = " Média Final: {" + this.vl_total + "}";

            return cliente;
        } else {
            String cliente = " ID [" + this.id_custumer + "]";
            cliente += " CPF (" + this.cpf_cnpj + " ) \t" + this.nm_custumer + "\t";
            cliente += "- Ativo: {" + this.getIs_active() + "}";
            cliente += "- Valor: {" + this.vl_total + "}";
            return cliente;
        }
    }

    //Verificando se existem CPF ou CNPJ iguais.
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((cpf_cnpj == null) ? 0 : cpf_cnpj.hashCode());
        return result;
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
        Cliente other = (Cliente) obj;
        if (cpf_cnpj == null) {
            if (other.cpf_cnpj != null) {
                return false;
            }
        } else if (!cpf_cnpj.equals(other.cpf_cnpj)) {
            return false;
        }
        return true;
    }

}
