/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.com.biblioteca_reges.dominios;

import java.util.Calendar;

/**
 *
 * @author doniz
 */
public class Emprestimo {
    private String nomeLivro;
    private int codigoEmp;
    private int alunoRa;
    private Calendar dataEmp;
    private Calendar dataDev;
    
    public Emprestimo(String nomeLivro, int codigoEmp, int alunoRa, Calendar dataEmp, Calendar dataDev) {
        this.nomeLivro = nomeLivro;
        this.codigoEmp = codigoEmp;
        this.alunoRa = alunoRa;
        this.dataEmp = dataEmp;
        this.dataDev = dataDev;
    }
    
    public void imprimir(){
        System.out.println("Livro: " + this.nomeLivro);
        System.out.println("Código do empréstimo: " + this.codigoEmp);
        System.out.println("RA do aluno: " + this.alunoRa);
        System.out.println("Data de empréstimo: " + this.dataEmp);
        System.out.println("Data de devolução: " + this.dataDev);
    }
    
    public String getNomeLivro(){
        return this.nomeLivro;
    }
    
    public int getCodigoEmp(){
        return this.codigoEmp;
    }
    
    public int getAlunoRa(){
        return this.alunoRa;
    }
    
    public Calendar getDataEmp(){
        return this.dataEmp;
    }
    
     public Calendar getDataDev(){
        return this.dataDev;
    }
}
