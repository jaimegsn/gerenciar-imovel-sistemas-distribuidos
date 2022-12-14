package edu.sistemas_distribuidos.terceira_entrega.server.services;

import com.google.gson.Gson;
import edu.sistemas_distribuidos.terceira_entrega.local.entities.Imovel;
import edu.sistemas_distribuidos.terceira_entrega.local.entities.Parametro;
import edu.sistemas_distribuidos.terceira_entrega.server.connections.ConexaoSQLite;
import edu.sistemas_distribuidos.terceira_entrega.server.connections.CriarBancoSQLite;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class GerenciarImovel {
    static {
        CriarBancoSQLite.criarTabelaImoveis();
    }

    public static String cadastrarImovel(Imovel imovel) {
        ConexaoSQLite.conectar();
        String sql = "INSERT INTO tbl_imovel (nomeProprietario,endereco,preco) VALUES (?,?,?);";
        try (PreparedStatement psmt = ConexaoSQLite.criarPreparedStatement(sql);) {
            assert psmt != null;
            psmt.setString(1, imovel.getNomeProprietario());
            psmt.setString(2, imovel.getEndereco());
            psmt.setDouble(3, imovel.getPreco());
            psmt.execute();
            return "Servidor: Imovel Cadastrado";
        } catch (SQLException e) {
            e.printStackTrace();
            return "ERRO";
        } finally {
            ConexaoSQLite.desconectar();
        }
    }

    public static String buscarImovel(Parametro parametro) {
        ConexaoSQLite.conectar();
        String sql = "SELECT * FROM tbl_imovel WHERE preco <= ?;";
        List<Imovel> imoveis = new ArrayList<>();
        try (PreparedStatement pst = preparedStatementBusca(sql, parametro.getPreco());
             ResultSet rs = pst.executeQuery();) {

            while (rs.next()) {
                String nomeProprietario = rs.getString("nomeProprietario");
                System.out.println(nomeProprietario);
                String endereco = rs.getString("endereco");
                double preco = rs.getDouble("preco");
                Imovel imovel = new Imovel(nomeProprietario, endereco, preco);
                imovel.setId(rs.getInt("id"));
                imoveis.add(imovel);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            ConexaoSQLite.desconectar();
        }
        return new Gson().toJson(imoveis);
    }

    private static PreparedStatement preparedStatementBusca(String sql, double preco) throws SQLException {
        PreparedStatement pst = ConexaoSQLite.criarPreparedStatement(sql);
        assert pst != null;
        pst.setDouble(1, preco);
        return pst;
    }

    public static String removerImovel(Parametro parametro) {
        ConexaoSQLite.conectar();
        String sql = "DELETE FROM tbl_imovel WHERE id=?;";
        try (PreparedStatement pst = ConexaoSQLite.criarPreparedStatement(sql)) {
            assert pst != null;
            pst.setInt(1, parametro.getId());
            pst.execute();
            return "Servidor: Imovel Deletado";
        } catch (SQLException e) {
            e.printStackTrace();
            return "Erro";
        } finally {
            ConexaoSQLite.desconectar();
        }
    }

    public static String editarImovel(Parametro parametro) {
        ConexaoSQLite.conectar();
        String sql = "UPDATE tbl_imovel SET nomeProprietario=?, endereco=?, preco=? WHERE id=?";
        try(PreparedStatement pst = ConexaoSQLite.criarPreparedStatement(sql)){

            assert pst != null;
            pst.setString(1,parametro.getImovel().getNomeProprietario());
            pst.setString(2,parametro.getImovel().getEndereco());
            pst.setDouble(3,parametro.getImovel().getPreco());
            pst.setInt(4,parametro.getId());
            pst.execute();
            return "Servidor: Imovel atualizado";
        }catch (SQLException e){
            e.printStackTrace();
            return "Erro";
        }finally {
            ConexaoSQLite.desconectar();
        }
    }
}
