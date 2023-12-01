/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.com.biblioteca_reges.rdn;

import java.sql.*;

public class ConnectionFactory {
    
    private String USER = "root";
    private String PASSWORD = "sql1234";
    private String URL = "jdbc:mysql://localhost:3306/db_biblioteca_reges";
    
    public Connection getConnection(){
    
        try{
            Connection conn = DriverManager.getConnection(URL,USER,PASSWORD);
            System.out.println("Conexão bem-sucedida!");
            return conn;
        }
        
        catch(SQLException ex){
            System.out.println("Falha na conexão: " + ex.getMessage());
            throw new RuntimeException(ex);
        
        }
    }
    
}
