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
public class Livro {
    private String nome;
    private String autor;
    private int quantidade;
    private float preco;
    private String categoria;
    private Calendar dataCadastro;
    
    public Livro(String nome, String autor, int quantidade, float preco, String categoria, Calendar dataCadastro){
        this.nome = nome;
        this.autor = autor;
        this.quantidade = quantidade;
        this.preco = preco;
        this.categoria = categoria;
        this.dataCadastro = dataCadastro;
    }
    
    public String getNomeLivro(){
        return this.nome;
    };
    
    public String getNomeAutor(){
        return this.autor;
    };
    
    public int getQuantidade(){
        return this.quantidade;
    }
    
    public float getPreco(){
        return this.preco;
    }
    
    public String getCategoria(){
        return this.categoria;
    };
    
    public Calendar getDataCadastro(){
        return this.dataCadastro;
    }
    
    public void imprimir(){
        System.out.println("Nome do livro: " + this.nome);
        System.out.println("Nome do autor: " + this.autor);
        System.out.println("Quantidade: " + this.quantidade);
        System.out.println("Preço do livro: " + this.preco);
        System.out.println("Categoria do livro: " + this.categoria);
        System.out.println("Data do cadastro: " + this.dataCadastro);
    }
}
