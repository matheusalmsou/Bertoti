package autoweb;

public class Estoque {

    private int codProd;
    private int qtd;
    private double valor;
    private double total;

    public Estoque(int codProd, int qtd, double valor, double total) {
        this.codProd = codProd;
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

    public void setQtd(int qtd) {
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