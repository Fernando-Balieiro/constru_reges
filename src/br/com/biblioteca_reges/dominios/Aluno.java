/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.com.biblioteca_reges.dominios;

import java.util.Calendar;

/**
 *
 * @author Alex
 */
public class Aluno extends Pessoa {
    private int alunoRa;
    private String senha;
    
    public Aluno(int id, int alunoRa, String senha, String nomeCompleto, Endereco endereco, String telefone, Calendar dataNascFund, String documento, String rgIe, String email) {
        super(id,nomeCompleto, endereco, telefone, dataNascFund, documento, rgIe,email);
        
        this.alunoRa = alunoRa;
        this.senha = senha;
    }
    
    
    public void imprimir(){
    
         System.out.println("Nome: " + this.getNomeCompleto());
         System.out.println("Documento: " + this.getDocumento());
         System.out.println("Rg: " + this.getRgIe() );
         System.out.println("Telefone: " + this.getTelefone());
         System.out.println("Email: " + this.getEmail());
    }
    
    public int getAlunoRa(){
        return this.alunoRa;
    }
    
    public String getAlunoSenha(){
        return this.senha;
    }
}
