package com.spring.evalutaion;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class ProduitRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    private RowMapper<Produit> rowMapper = new RowMapper<Produit>() {
        @Override
        public Produit mapRow(ResultSet rs, int rowNum) throws SQLException {
            Produit produit = new Produit(
                rs.getInt("id"),
                rs.getString("nom"),
                rs.getDouble("prix"),
                rs.getInt("quantite")
            );
            return produit;
        }
    };

    public List<Produit> findAll() {
        String sql = "SELECT * FROM produit";
        return jdbcTemplate.query(sql, rowMapper);
    }


    public int save(Produit produit) {
        String sql = "INSERT INTO produit (nom, prix, quantite) VALUES (?, ?, ?)";
        jdbcTemplate.update(sql, produit.getNom(), produit.getPrix(), produit.getQuantite());
        return 201;
    }

    public Integer getValeurStock() {
        String sql = "SELECT SUM(prix * quantite) AS total_value FROM produit";
        Integer totalValue = jdbcTemplate.queryForObject(sql,Integer.class);     
            return totalValue != null ? totalValue : 0;
    }
}
