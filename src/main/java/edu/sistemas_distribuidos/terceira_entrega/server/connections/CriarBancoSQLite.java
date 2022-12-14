package edu.sistemas_distribuidos.terceira_entrega.server.connections;

import java.sql.SQLException;
import java.sql.Statement;

public class CriarBancoSQLite {

    private static final boolean conectou = ConexaoSQLite.conectar();

    // Cria a tabela se ela não existir
    public static void criarTabelaImoveis(){
        String sql = "CREATE TABLE IF NOT EXISTS tbl_imovel (" +
                "id INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL," +
                "nomeProprietario VARCHAR," +
                "endereco VARCHAR," +
                "preco REAL" +
                ")";

        // Executando SQL:
        try (Statement smt = ConexaoSQLite.criarStatement();) {
            smt.execute(sql);
        }catch (SQLException e){
            e.printStackTrace();
        }finally {
            if(conectou)
                ConexaoSQLite.desconectar();
        }
    }

    public static void main(String[] args) {
        CriarBancoSQLite.criarTabelaImoveis();
    }
}
