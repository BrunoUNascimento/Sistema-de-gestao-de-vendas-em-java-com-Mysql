package br.com.compulog.dao;
/**
 * 20/01/2024 15:58h
 * @author Bruno Ullmanna do Nascimento
 */
import java.sql.*;
import javax.swing.JOptionPane;

public class Conexao {
    
    public static Connection conector(){
    java.sql.Connection conexao = null;
    
    String driver = "com.mysql.cj.jdbc.Driver";
    
    String url = "jdbc:mysql://localhost:3306/compulog";
    String user = "root";
    String password = "root";
    
    
        try {
            conexao.setAutoCommit(true);
            Class.forName(driver);
            conexao = DriverManager.getConnection(url,user,password);
            System.out.println(conexao);
            return conexao;
            
        } catch (Exception e) {
            e.printStackTrace(); // Adicionando informações detalhadas da exceção
            System.out.println("Falha na conexão");
            return null;
        }
    
    }








    
    
    
    
    
    
    
    
    
    
    
}
