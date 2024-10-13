/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dades;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import java.sql.Connection;
import java.sql.SQLException;

/**
 * descripciÓ: Classe que gestiona el pool de connexions a la base de dades.
 * Aquesta classe utilitza HikariCP per gestionar eficientment les connexions 
 * a una base de dades MySQL.
 * @author ngall
 * @version 10/2024.1
 */
public class MyDataSource {
    private static final HikariConfig config = new HikariConfig(); // objecte que ens permetrà configurar el pool
    private static HikariDataSource dataSource; //és el pool de connexions

    static {  //per poder inicialitzar els dos objectes privats ABANS que es cridi el únic mètode públic getConnection
        config.setJdbcUrl("jdbc:mysql://localhost/projecte1?useUnicode=true&serverTimezone=Europe/Madrid");
        config.setUsername("root");
        config.setPassword("123456");
        config.addDataSourceProperty("maximumPoolSize", 1); //al pool només tindrem 1 connexió
        //altres propietats indicades a https://github.com/brettwooldridge/HikariCP#rocket-initialization
        config.addDataSourceProperty("cachePrepStmts", "true");
        config.addDataSourceProperty("prepStmtCacheSize", "250");
        config.addDataSourceProperty("prepStmtCacheSqlLimit", "2048");
        dataSource = new HikariDataSource(config); //ja tenim el pool de connexions
    }
    //constructor privat perquè no el puguem cridar i no es puguin crear instancies d'aquesta classe.
    private MyDataSource() {
    }
    
    /**
     * Retorna una connexió disponible del pool de connexions.
     * @return Una connexió a la base de dades.
     * @throws SQLException Si hi ha un error a l'obtenir la connexió.
     */
    public static Connection getConnection() throws SQLException {
        //retornarà una connexió disponible del pool de connexions
        return dataSource.getConnection();
    }
}
