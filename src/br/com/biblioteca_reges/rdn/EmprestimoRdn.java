/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.com.biblioteca_reges.rdn;

import br.com.biblioteca_reges.dominios.Aluno;
import java.sql.*;
import br.com.biblioteca_reges.dominios.Emprestimo;
import java.text.SimpleDateFormat;
import java.util.Calendar;

/**
 *
 * @author doniz
 */
public class EmprestimoRdn {
    public int inserirEmprestimo(Emprestimo emp) {

        try {

            int linhasAfetadas = 0;
            StringBuilder str = new StringBuilder();

            str.append("INSERT INTO emprestimo_livro");
            str.append("(nomeLivro                  ");
            str.append(",codigoEmp                  ");
            str.append(",alunoRa                    ");
            str.append(",dataEmp                    ");
            str.append(",dataDev)                   ");
            str.append("VALUES(                     ");
            str.append(" ?                          ");
            str.append(",?                          ");
            str.append(",?                          ");
            str.append(",?                          ");
            str.append(",?                          ");
            str.append(")                           ");

            ConnectionFactory factory = new ConnectionFactory();
            Connection conn = factory.getConnection();

            //CRIA O STATMENT JÁ PREPARADO PARA OBTER O ID CLIENTE GERADO
            PreparedStatement stmt = conn.prepareStatement(str.toString(), Statement.RETURN_GENERATED_KEYS);

            stmt.setString(1, emp.getNomeLivro());
            stmt.setInt(2, emp.getCodigoEmp());
            stmt.setInt(3, emp.getAlunoRa());
            stmt.setDate(4, new java.sql.Date(emp.getDataEmp().getTimeInMillis()));
            stmt.setDate(5, new java.sql.Date(emp.getDataDev().getTimeInMillis()));
            
            linhasAfetadas = stmt.executeUpdate();      

            stmt.close();
            conn.close();

            return linhasAfetadas;

        } catch (SQLException ex) {
            System.out.println("ERRO: " + ex.getMessage());
            return 0;
        }
    }
    
    public boolean atualizarEmprestimo(Emprestimo emp){
        try {

            int linhasAfetadas = 0;
            StringBuilder str = new StringBuilder();

            str.append("UPDATE emprestimo_livro  SET");
            str.append(" nomeLivro     =?        ");
            str.append(",alunoRa            =?   ");
            str.append(",dataEmp         =?      ");
            str.append(",dataDev     =?          ");
            str.append("WHERE codigoEmp =?              ");

            ConnectionFactory factory = new ConnectionFactory();
            Connection conn = factory.getConnection();

            PreparedStatement stmt = conn.prepareStatement(str.toString(), Statement.RETURN_GENERATED_KEYS);

            stmt.setString(1, emp.getNomeLivro());
            stmt.setInt(2, emp.getAlunoRa());
            stmt.setDate(3, new java.sql.Date(emp.getDataEmp().getTimeInMillis()));
            stmt.setDate(4, new java.sql.Date(emp.getDataDev().getTimeInMillis()));
            stmt.setInt(5, emp.getCodigoEmp());
            
            stmt.executeUpdate();      

            stmt.close();
            conn.close();

            return true;

        } catch (SQLException ex) {
            System.out.println("ERRO: " + ex.getMessage());
            return false;
        }
    }
    
    public boolean deletarPorId(int empId){
        try {

            int linhasAfetadas = 0;

            String str = "DELETE FROM emprestimo_livro WHERE codigoEmp = ?";
            ConnectionFactory factory = new ConnectionFactory();
            Connection conn = factory.getConnection();

            PreparedStatement stmt = conn.prepareStatement(str.toString());
            stmt.setInt(1, empId);

            linhasAfetadas = stmt.executeUpdate();

            stmt.close();
            conn.close();

            return true;

        } catch (Exception ex) {
            System.out.println("Erro: " + ex.getMessage());
            return false;
        }
    }
    
    public int verificarAlunoPorRa(int raAluno){
        try {
            
            int model = 0;

            StringBuilder str = new StringBuilder();

                str.append("SELECT id                        ");
                str.append("FROM aluno WHERE alunoRa = ?     " );
           
            //ABRE A CONEXÃO
            Connection conn = new ConnectionFactory().getConnection();

            //CRIAR NOSSO STATEMENT            
            PreparedStatement stmt = conn.prepareStatement(str.toString());

          
            stmt.setInt(1, raAluno);
            
            //RECEBER OS DADOS NO RESULTSET
            ResultSet rs = stmt.executeQuery();
            
            if (rs.next()) {
                model = rs.getInt("id");
                
            }
            return model;

        } catch (SQLException ex) {
            System.out.println("RA do aluno não encontrado no sistema!");
            System.out.println("ERRO:" + ex.getMessage());
            return 0;
        }
    }
    
    public Emprestimo obterEmprestimoPorCodigo(int codigo){
        try{
            Emprestimo model = null;
            
            int linhasAfetadas = 0;
            StringBuilder str = new StringBuilder();

            str.append("SELECT * FROM emprestimo_livro       ");
            str.append("WHERE                          ");
            str.append("codigoEmp =?                      ");
            
            ConnectionFactory factory = new ConnectionFactory();
            Connection conn = factory.getConnection();
            
            //CRIAR NOSSO STATEMENT            
            PreparedStatement stmt = conn.prepareStatement(str.toString());
          
            stmt.setInt(1, codigo);

            //RECEBER OS DADOS NO RESULTSET
            ResultSet result = stmt.executeQuery();
            
            if(result.next()){
                Calendar calendarEmp = Calendar.getInstance();
                Calendar calendarDev = Calendar.getInstance();
                
                calendarEmp.setTime(result.getDate("dataEmp"));
                calendarDev.setTime(result.getDate("dataDev"));

                model = new Emprestimo(result.getString("nomeLivro"),
                result.getInt("codigoEmp"),
                result.getInt("alunoRa"),
                calendarEmp,
                calendarDev
                );
            }
            
            return model;
        }catch(SQLException ex){
            System.out.println("ERRO: " + ex.getMessage());
            return null;
        }
    }
}
