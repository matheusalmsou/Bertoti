package autoweb;

public class Produto {
    
    private String descript;
    private double valor;

    public Produto(String descript, double valor) {
        this.descript = descript;
        this.valor = valor;
    }

    public void setDescript(String descript) {
        this.descript = descript;
    }

    public String getDescript() {
        return descript;
    }

    public void setValor(double valor) {
        this.valor = valor;
    }

    public double getValor() {
        return valor;
    }
}
