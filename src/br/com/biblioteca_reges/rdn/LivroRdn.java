/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.com.biblioteca_reges.rdn;
import br.com.biblioteca_reges.dominios.Livro;
import java.sql.*;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
/**
 *
 * @author doniz
 */
public class LivroRdn {
    public int cadastrarLivro(Livro liv){
        try {
            int linhasAfetadas = 0;
            StringBuilder str = new StringBuilder();

            str.append("INSERT INTO livro       ");
            str.append("(nome                   ");
            str.append(",autor                  ");
            str.append(",quantidade             ");
            str.append(",preco                  ");
            str.append(",categoria              ");
            str.append(",dataCadastro)          ");
            str.append("VALUES(                 ");
            str.append(" ?                      ");
            str.append(",?                      ");
            str.append(",?                      ");
            str.append(",?                      ");
            str.append(",?                      ");
            str.append(",?                      ");
            str.append(")                       ");

            ConnectionFactory factory = new ConnectionFactory();
            Connection conn = factory.getConnection();

            PreparedStatement stmt = conn.prepareStatement(str.toString(), Statement.RETURN_GENERATED_KEYS);

            stmt.setString(1, liv.getNomeLivro());
            stmt.setString(2, liv.getNomeAutor());
            stmt.setInt(3, liv.getQuantidade());
            stmt.setFloat(4, liv.getPreco());
            stmt.setString(5, liv.getCategoria());
            stmt.setTimestamp(6, new java.sql.Timestamp(liv.getDataCadastro().getTimeInMillis()));
            
            linhasAfetadas = stmt.executeUpdate();       

            stmt.close();
            conn.close();

            return linhasAfetadas;

        } catch (SQLException ex) {
            System.out.println("ERRO: " + ex.getMessage());
            return 0;
        }
    }
    
    public List<String> obterTodosLivros(){
        try {
            
            List<String> lstLivro = new ArrayList<>();
            StringBuilder str = new StringBuilder();
            str.append("SELECT  *               ");           
            str.append("FROM livro              ");
            

            //ABRE A CONEXÃO
            Connection conn = new ConnectionFactory().getConnection();

            //CRIAR NOSSO STATEMENT            
            Statement stmt = conn.createStatement();

            //RECEBER OS DADOS NO RESULTSET
            ResultSet rs = stmt.executeQuery(str.toString());

            //INSTANCIA DA CLASSE ENDERECO RDN
             //EnderecoRdn endRdn = new EnderecoRdn();
             
            while (rs.next()) {
                String liv = rs.getString("nome");
                lstLivro.add(liv);
                
            }

            return lstLivro;

        } catch (SQLException ex) {

            System.out.println("ERRO:" + ex.getMessage());
            return null;
        }
    }
    
    public int obterLivroIdPorNome(String nomeLivro){
        try {
            int model = 0;
            
            int linhasAfetadas = 0;
            StringBuilder str = new StringBuilder();

            str.append("SELECT * FROM livro       ");
            str.append("WHERE                   ");
            str.append("nome = ?                  ");
            

            ConnectionFactory factory = new ConnectionFactory();
            Connection conn = factory.getConnection();
            
            //CRIAR NOSSO STATEMENT            
            PreparedStatement stmt = conn.prepareStatement(str.toString());
          
            stmt.setString(1, nomeLivro);

            //RECEBER OS DADOS NO RESULTSET
            ResultSet result = stmt.executeQuery();
            
            if(result.next()){
                Calendar calendar = Calendar.getInstance();
            calendar.setTime(result.getDate("dataCadastro"));
            
            model = result.getInt("id");
            }
            
            return model;
        } catch(SQLException ex){
            System.out.println("ERRO: " + ex.getMessage());
            return 0;
        }
    }
    
    public Livro obterLivroPorNome(String nomeLivro){
        try {
            Livro model = null;
            
            int linhasAfetadas = 0;
            StringBuilder str = new StringBuilder();

            str.append("SELECT * FROM livro       ");
            str.append("WHERE                   ");
            str.append("nome = ?                  ");
            

            ConnectionFactory factory = new ConnectionFactory();
            Connection conn = factory.getConnection();
            
            //CRIAR NOSSO STATEMENT            
            PreparedStatement stmt = conn.prepareStatement(str.toString());
          
            stmt.setString(1, nomeLivro);

            //RECEBER OS DADOS NO RESULTSET
            ResultSet result = stmt.executeQuery();
            
            if(result.next()){
                Calendar calendar = Calendar.getInstance();
            calendar.setTime(result.getDate("dataCadastro"));
            
            model = new Livro(result.getString("nome"),
            result.getString("autor"),
            result.getInt("quantidade"),
            result.getFloat("preco"),
            result.getString("categoria"),
            calendar
            );
            }
            
            return model;
        } catch(SQLException ex){
            System.out.println("ERRO: " + ex.getMessage());
            return null;
        }
    }
    
    public boolean atualizarPorId(int id, Livro liv){
        try{
            
            int linhasAfetadas = 0;
            StringBuilder str = new StringBuilder();
        
            str.append("UPDATE livro SET      ");
            str.append(" nome          =?     ");
            str.append(",autor         =?     ");
            str.append(",quantidade    =?     ");
            str.append(",preco         =?     ");
            str.append(",categoria     =?     ");
            str.append("WHERE id       =?     ");
            
            ConnectionFactory factory = new ConnectionFactory();
            Connection conn = factory.getConnection();
            
            //CRIAR NOSSO STATEMENT            
            PreparedStatement stmt = conn.prepareStatement(str.toString());
          
            stmt.setString(1, liv.getNomeLivro());
            stmt.setString(2, liv.getNomeAutor());
            stmt.setInt(3, liv.getQuantidade());
            stmt.setFloat(4, liv.getPreco());
            stmt.setString(5, liv.getCategoria());
            stmt.setInt(6, id);
            
            stmt.executeUpdate();
            
            return true;
        }catch(SQLException ex){
            System.out.println("ERRO: " + ex.getMessage());
            return false;
        }
    }
    
    public boolean deletarPorId(int id){
            try{
               int linhasAfetadas = 0;
                StringBuilder str = new StringBuilder();

                str.append("DELETE FROM livro     ");
                str.append("WHERE                 ");
                str.append("id = ?                ");

                ConnectionFactory factory = new ConnectionFactory();
            Connection conn = factory.getConnection();
            
            //CRIAR NOSSO STATEMENT            
            PreparedStatement stmt = conn.prepareStatement(str.toString());
          
            stmt.setInt(1, id);
            
            stmt.executeUpdate();
            
            return true;
            }catch(SQLException ex){
            System.out.println("ERRO: " + ex.getMessage());
            return false;
        }          
    }
}
