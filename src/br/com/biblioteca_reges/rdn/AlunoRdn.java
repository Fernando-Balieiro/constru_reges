/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.com.biblioteca_reges.rdn;

import java.sql.*;
import br.com.biblioteca_reges.dominios.Aluno;
import br.com.biblioteca_reges.dominios.Endereco;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

/**
 *
 * @author anton
 */
public class AlunoRdn {

    public int inserirAluno(Aluno cli) {

        try {

            int linhasAfetadas = 0;
            StringBuilder str = new StringBuilder();

            str.append("INSERT INTO aluno    ");
            str.append("(alunoRa               ");
            str.append(",nomeCompleto           ");
            str.append(",senha                  ");
            str.append(",email                  ");
            str.append(",telefone               ");
            str.append(",dataNascFund           ");
            str.append(",documento              ");
            str.append(",rgIe)                   ");
            str.append("VALUES(                 ");
            str.append(" ?                      ");
            str.append(",?                      ");
            str.append(",?                      ");
            str.append(",?                      ");
            str.append(",?                      ");
            str.append(",?                      ");
            str.append(",?                      ");
            str.append(",?                      ");
            str.append(")                       ");

            ConnectionFactory factory = new ConnectionFactory();
            Connection conn = factory.getConnection();

            //CRIA O STATMENT JÁ PREPARADO PARA OBTER O ID CLIENTE GERADO
            PreparedStatement stmt = conn.prepareStatement(str.toString(), Statement.RETURN_GENERATED_KEYS);

            stmt.setInt(1, cli.getAlunoRa());
            stmt.setString(2, cli.getNomeCompleto());
            stmt.setString(3, cli.getAlunoSenha());
            stmt.setString(4, cli.getEmail());
            stmt.setString(5, cli.getTelefone());
            stmt.setDate(6, new java.sql.Date(cli.getDataNascFund().getTimeInMillis()));
            stmt.setString(7, cli.getDocumento());
            stmt.setString(8, cli.getRgIe());

            int id = 0;
            
            linhasAfetadas = stmt.executeUpdate();      
            
            ResultSet rs = stmt.getGeneratedKeys();            
            if (rs.next()) {
                //RECUPERA O IDCLIENTE
                
               id = rs.getInt(1); //recuperar o id               
               
               EnderecoRdn endRdn = new EnderecoRdn();           
               Endereco end = cli.getEndereco();
               end.setIdAluno(id);
               
               endRdn.inserirEndereco(end);
               
            }          

            stmt.close();
            conn.close();

            return linhasAfetadas;

        } catch (SQLException ex) {
            System.out.println("ERRO: " + ex.getMessage());
            return 0;
        }
    }

    public void alterarAluno(Aluno cli) {

        try {

            int linhasAfetadas = 0;
            StringBuilder str = new StringBuilder();

            str.append("UPDATE aluno SET      ");
            str.append(" nomeCompleto    = ?    ");
            str.append(",alunoRa          = ?    ");
            str.append(",senha          = ?    ");
            str.append(",email            =?    ");
            str.append(",telefone         =?    ");
            str.append(",dataNascFund     =?    ");
            str.append(",documento        =?    ");
            str.append(",rgIe             =?     ");
            str.append("WHERE id = ?      ");

            ConnectionFactory factory = new ConnectionFactory();
            Connection conn = factory.getConnection();

            //CRIA O STATMENT JÁ PREPARADO PARA OBTER O ID CLIENTE GERADO
            PreparedStatement stmt = conn.prepareStatement(str.toString());

            stmt.setString(1, cli.getNomeCompleto());
            stmt.setInt(2, cli.getAlunoRa());
            stmt.setString(3, cli.getAlunoSenha());
            stmt.setString(4, cli.getEmail());
            stmt.setString(5, cli.getTelefone());
            stmt.setDate(6, new java.sql.Date(cli.getDataNascFund().getTimeInMillis()));
            stmt.setString(7, cli.getDocumento());
            stmt.setString(8, cli.getRgIe());
            stmt.setInt(9, cli.getId());

            linhasAfetadas = stmt.executeUpdate();

            stmt.close();
            conn.close();

        } catch (SQLException ex) {
            System.out.println("ERRO: " + ex.getMessage());

        }
    }
    
    public int deletarAluno(int idAluno) {
        try {

            int linhasAfetadas = 0;

            String str = "DELETE FROM CLIENTE WHERE ID = ?";
            ConnectionFactory factory = new ConnectionFactory();
            Connection conn = factory.getConnection();

            PreparedStatement stmt = conn.prepareStatement(str.toString());
            stmt.setInt(1, idAluno);

            linhasAfetadas = stmt.executeUpdate();

            stmt.close();
            conn.close();

            return linhasAfetadas;

        } catch (Exception ex) {
            System.out.println("Erro: " + ex.getMessage());
            return 0;
        }

    }

    public List<Aluno> obterTodos() {
        try {

            EnderecoRdn endRdn = new EnderecoRdn();
            
            List<Aluno> lstCli = new ArrayList<Aluno>();
            StringBuilder str = new StringBuilder();
            str.append("SELECT  *               ");           
            str.append("FROM aluno              ");
            

            //ABRE A CONEXÃO
            Connection conn = new ConnectionFactory().getConnection();

            //CRIAR NOSSO STATEMENT            
            Statement stmt = conn.createStatement();

            //RECEBER OS DADOS NO RESULTSET
            ResultSet rs = stmt.executeQuery(str.toString());

            //INSTANCIA DA CLASSE ENDERECO RDN
             //EnderecoRdn endRdn = new EnderecoRdn();
             
            while (rs.next()) {

                //CONVERTER SQL DATE TO CALENDAR
                Calendar calendar = Calendar.getInstance();
              
               
               Endereco end =   endRdn.obterPorIdAluno(rs.getInt("ID"));
                
               
                Aluno cli = new Aluno(rs.getInt("ID"),
                        rs.getInt("alunoRa"),
                        rs.getString("senha"),
                        rs.getString("nomecompleto"),
                  
                        end,
                          rs.getString("TELEFONE"),
                        calendar,
                        rs.getString("DOCUMENTO"),
                        rs.getString("RGIE"),
                        rs.getString("EMAIL")
                );

                lstCli.add(cli);

            }
            return lstCli;

        } catch (SQLException ex) {

            System.out.println("ERRO:" + ex.getMessage());
            return null;
        }
    }
    
    public Aluno obterPorId(int id) {
        try {

            Aluno ret = null;

            StringBuilder str = new StringBuilder();

                str.append("SELECT *                        ");
                str.append("FROM aluno WHERE ID = ?     " );


           

            //ABRE A CONEXÃO
            Connection conn = new ConnectionFactory().getConnection();

            //CRIAR NOSSO STATEMENT            
            PreparedStatement stmt = conn.prepareStatement(str.toString());

          
            stmt.setInt(1, id);
            
            //RECEBER OS DADOS NO RESULTSET
            ResultSet rs = stmt.executeQuery();

            //INSTANCIA DA CLASSE ENDERECO RDN
            EnderecoRdn endRdn = new EnderecoRdn();
            
            if (rs.next()) {

                //CONVERTER SQL DATE TO CALENDAR
                Calendar calendar = Calendar.getInstance();
                calendar.setTime(rs.getDate("dataNascFund"));

                /*              
                public Aluno(int id, String nome, Calendar dataNascimento, String documento, 
                String telefone, String email, Endereco endereco, String cartaoFidelidade)
                 */
                Endereco end = endRdn.obterPorIdAluno(rs.getInt("ID"));
                
                ret = new Aluno(rs.getInt("ID"),
                        rs.getInt("alunoRa"),
                        rs.getString("senha"),
                        rs.getString("nomecompleto"),
                  
                        end,
                          rs.getString("TELEFONE"),
                        calendar,
                        rs.getString("DOCUMENTO"),
                        rs.getString("rgIe"),
                        rs.getString("EMAIL")
                );
                

            }
            return ret;

        } catch (SQLException ex) {

            System.out.println("ERRO:" + ex.getMessage());
            return null;
        }
    }
        
    public int verificarPorEmailESenha(String email, String senha){
            try{
                int model = 0;
            
                int linhasAfetadas = 0;
                StringBuilder str = new StringBuilder();

                str.append("SELECT * FROM aluno        ");
                str.append("WHERE                      ");
                str.append("email = ?  AND senha = ?   ");


                ConnectionFactory factory = new ConnectionFactory();
                Connection conn = factory.getConnection();

                //CRIAR NOSSO STATEMENT            
                PreparedStatement stmt = conn.prepareStatement(str.toString());

                stmt.setString(1, email);
                stmt.setString(2, senha);

                //RECEBER OS DADOS NO RESULTSET
                ResultSet result = stmt.executeQuery();

                if(result.next()){

                    model = result.getInt("id");
                }

                return model;
            }catch(SQLException ex){
                System.out.println("ERRO:" + ex.getMessage());
                return 0;
            }
        }
    }
