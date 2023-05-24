package autoweb;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.ResultSet;

public final class App {
    
    public void operation() {

        final int op = 4;

        App app = new App();

        switch (op) {
            case 1:
                app.cadastrarUsuario();
                break;
            
            case 2:
                app.cadastrarProduto();
                break;

            case 3:
                app.adicionarProduto();
                break;

            case 4:
                app.comprarProduto();
        }
    }

    public void cadastrarUsuario() {
        
        final String nome = "Matheus";
        final String cpf = "32983475623";

        Usuario usuario = new Usuario(nome, cpf);

        try {

            Connection con = DBConnection.getConnection();

            String sql = "INSERT INTO usuario (NOME, CPF) VALUES (?, ?)";

            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setString(1, usuario.getNome());
            stmt.setString(2, usuario.getCpf());

            stmt.executeUpdate();
            
            stmt.close();
            con.close();

        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    public void cadastrarProduto() {

        final String descript = "Escapamento";
        final double valor = 50;

        Produto produto = new Produto(descript, valor);

        try {

            Connection con = DBConnection.getConnection();

            String sql = "INSERT INTO produto (DESCRIPT, VALOR) VALUES (?, ?)";

            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setString(1, produto.getDescript());
            stmt.setDouble(2, produto.getValor());

            stmt.executeUpdate();
            
            stmt.close();
            con.close();

        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    public void adicionarProduto() {

        final int codProd = 1;
        final int qtd = 5;

        try {

            Connection con = DBConnection.getConnection();

            String sql = "SELECT QTD, VALOR, TOTAL FROM estoque WHERE COD_PRODUTO = ?";

            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setInt(1, codProd);

            ResultSet rset = stmt.executeQuery();
            
            if(rset.next()) {

                int qtddb = rset.getInt("QTD");
                double valor = rset.getDouble("VALOR");

                int newqtd = qtd + qtddb;
                double newtotal = newqtd * valor;
                
                sql = "UPDATE estoque SET QTD = ?, VALOR = ?, TOTAL = ? WHERE COD_PRODUTO = ?";

                stmt = con.prepareStatement(sql);
                stmt.setInt(1, newqtd);
                stmt.setDouble(2, valor);
                stmt.setDouble(3, newtotal);
                stmt.setInt(4, codProd);

                stmt.executeUpdate();

            } else {

                sql = "SELECT VALOR FROM produto WHERE COD_PRODUTO = ?";

                PreparedStatement stmt2 = con.prepareStatement(sql);
                stmt2.setInt(1, codProd);

                ResultSet rset2 = stmt2.executeQuery();

                if(rset2.next()) {
                    
                    double valor = rset2.getDouble("VALOR");
                    double total = qtd * valor;

                    String sqlInsert = "INSERT INTO estoque (COD_PRODUTO, QTD, VALOR, TOTAL) VALUES (?, ?, ?, ?)";
                
                    stmt = con.prepareStatement(sqlInsert);
                    stmt.setInt(1, codProd);
                    stmt.setInt(2, qtd);
                    stmt.setDouble(3, valor);
                    stmt.setDouble(4, total);

                    stmt.executeUpdate();
                }
            }

            stmt.close();
            con.close();
            
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    public void comprarProduto() {

        final int codUsuario = 1;
        final int codProd = 1;
        final int qtd = 2;

        try {

            Connection con = DBConnection.getConnection();

            String sqlEstoque = "SELECT QTD FROM estoque WHERE COD_PRODUTO = ?";

            PreparedStatement stmtEstoque = con.prepareStatement(sqlEstoque);
            stmtEstoque.setInt(1, codProd);

            ResultSet rsetEstoque = stmtEstoque.executeQuery();

            if(rsetEstoque.next()) {

                final double qtdEstoque = rsetEstoque.getInt("QTD");

                if(qtd > qtdEstoque) {
                    System.out.println("Estoque insuficiente. Quantidade disponível: " + qtdEstoque);
                } else {

                    String sql = "SELECT VALOR FROM produto WHERE COD_PRODUTO = ?";

                    PreparedStatement stmt = con.prepareStatement(sql);
                    stmt.setInt(1, codProd);

                    ResultSet rset = stmt.executeQuery();

                    if(rset.next()) {

                        final double valor = rset.getDouble("VALOR");
                        final double total = valor * qtd;

                        sql = "INSERT INTO ordem (COD_USUARIO, COD_PRODUTO, QTD, VALOR, TOTAL) VALUES (?, ?, ?, ?, ?)";
                        
                        stmt = con.prepareStatement(sql);
                        stmt.setInt(1, codUsuario);
                        stmt.setInt(2, codProd);
                        stmt.setDouble(3, qtd);
                        stmt.setDouble(4, valor);
                        stmt.setDouble(5, total);

                        stmt.executeUpdate();

                        final double newqtd = qtdEstoque - qtd;
                        final double newtotal = newqtd * valor;
                        
                        sqlEstoque = "UPDATE estoque SET QTD = ?, TOTAL = ? WHERE COD_PRODUTO = ?";

                        stmt = con.prepareStatement(sqlEstoque);
                        stmt.setDouble(1, newqtd);
                        stmt.setDouble(2, newtotal);
                        stmt.setDouble(3, codProd);

                        stmt.executeUpdate();
                    }

                    stmt.close();
                    con.close();
                }
            } else {
                System.out.println("Produto indisponível no estoque.");
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        
    }

    public static void main(String[] args) {

        App app = new App();     
        
        app.operation();
    }
}
