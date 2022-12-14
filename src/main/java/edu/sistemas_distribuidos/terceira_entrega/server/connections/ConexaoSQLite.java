package edu.sistemas_distribuidos.terceira_entrega.server.connections;

import java.sql.*;

public class ConexaoSQLite {
    private static Connection conexao;

    public static Connection getConexao(){
        return conexao;
    }


     // Conecta ao banco de dados (cria um banco se não existir)
    public static boolean conectar(){
        try {
            String url = "jdbc:sqlite:dbs/imoveis.db";
            conexao = DriverManager.getConnection(url);
            System.out.println("Conecta DB");
        }catch (SQLException e){
            e.printStackTrace();
        }
        return true;
    }

    public static boolean desconectar(){
        try{
            if(!conexao.isClosed()){
                conexao.close();
                System.out.println("Desconecta DB");
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        return true;
    }

    public static Statement criarStatement(){
        try{
            return conexao.createStatement();
        }catch (SQLException e){
            e.printStackTrace();
            return null;
        }
    }

    public static PreparedStatement criarPreparedStatement(String sql){
        try{
            return conexao.prepareStatement(sql);
        }catch (SQLException e){
            e.printStackTrace();
            return null;
        }
    }

}
