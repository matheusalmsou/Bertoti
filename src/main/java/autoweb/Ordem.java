package autoweb;

import com.google.protobuf.CodedOutputStream;

public class Ordem {
    
    private int codProd;
    private int codUsuario;
    private int qtd;
    private double valor;
    private double total;

    public Ordem(int codProd, int codUsuario, int qtd, double valor, double total) {
        this.codProd = codProd;
        this.codUsuario = codUsuario;
        this.qtd = qtd;
        this.valor = valor;
        this.total = total;
    }

    public void setCodProd(int codProd) {
        this.codProd = codProd;
    }

    public int getCodProd() {
        return codProd;
    }

    public void setCodUsuario(int codUsuario) {
        this.codUsuario = codUsuario;
    }

    public int getCodUsuario() {
        return codUsuario;
    }

    public void qtd(int qtd) {
        this.qtd = qtd;
    }

    public int getQtd() {
        return qtd;
    }

    public void setValor(double valor) {
        this.valor = valor;
    }

    public double getValor() {
        return valor;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    public double getTotal() {
        return total;
    }
}

