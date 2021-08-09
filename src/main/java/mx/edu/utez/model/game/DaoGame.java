package mx.edu.utez.model.game;

import jdk.internal.util.xml.impl.Input;
import mx.edu.utez.model.category.BeanCategory;
import mx.edu.utez.service.ConnectionMySQL;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.InputStream;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;

public class DaoGame {
    private Connection con;
    private CallableStatement cstm;
    private ResultSet rs;
    final private Logger CONSOLE = LoggerFactory.getLogger(DaoGame.class);

    public List<BeanGame> findGame(){
        List<BeanGame> listGame = new ArrayList<>();
        try {

            con = ConnectionMySQL.getConnection();
            cstm = con.prepareCall("{call findgame}");
            rs = cstm.executeQuery();

            while(rs.next()){
                BeanGame beanGame = new BeanGame();
                BeanCategory beanCategory = new BeanCategory();

                beanGame.setImg_game(Base64.getEncoder().encodeToString(rs.getBytes("imgGame")));

                beanGame.setIdGame(rs.getInt("idGame"));
                beanGame.setNameGame(rs.getString("nameGame"));
                beanGame.setImg_game(rs.getString("img_game"));
                beanCategory.setIdCategory(rs.getInt("idCategory"));
                beanGame.setDatePremiere(rs.getString("datePremiere"));
                beanGame.setStatus(rs.getString("status"));
                beanGame.setCategory_idCategory(beanCategory);

                listGame.add(beanGame);
            }
        }catch (SQLException e){
            CONSOLE.error("Ha ocurrido un error: " + e.getMessage());
        } finally {
            ConnectionMySQL.closeConnections(con, cstm, rs);
        }
        return listGame;
    }

    public BeanGame findById(long id){
        BeanGame game = null;
        try {

            con = ConnectionMySQL.getConnection();
            cstm = con.prepareCall("SELECT * FROM game  WHERE idGame = ?");
            cstm.setLong(1, id);
            rs = cstm.executeQuery();

            if(rs.next()){
                BeanGame beanGame = new BeanGame();
                BeanCategory beanCategory = new BeanCategory();

                beanGame.setIdGame(rs.getInt("idGame"));
                beanGame.setNameGame(rs.getString("nameGame"));
                beanGame.setImg_game(rs.getString("img_game"));
                beanCategory.setIdCategory(rs.getInt("idCategory"));
                beanGame.setDatePremiere(rs.getString("datePremiere"));
                beanGame.setStatus(rs.getString("status"));
                beanGame.setCategory_idCategory(beanCategory);

            }
        }catch (SQLException e){
            CONSOLE.error("Ha ocurrido un error: " + e.getMessage());
        } finally {
            ConnectionMySQL.closeConnections(con, cstm, rs);
        }
        return game;
    }

    public boolean create(BeanGame game, InputStream image){
        boolean flag = false;
        try{
            con = ConnectionMySQL.getConnection();
            cstm = con.prepareCall("{call registergame(?,?,?,?,?)}");
            cstm.setString(1, game.getNameGame());
            cstm.setBlob(2,image);
            cstm.setInt(3, game.getCategory_idCategory().getIdCategory());
            cstm.setString(4, game.getDatePremiere());
            cstm.setString(5, game.getStatus());
            cstm.execute();

            flag = true;
        }catch(SQLException e){
            CONSOLE.error("Ha ocurrido un error: " + e.getMessage());
        } finally {
            ConnectionMySQL.closeConnections(con, cstm);
        }
        return flag;
    }

    public boolean update(BeanGame game){
        boolean flag = false;
        try{
            con = ConnectionMySQL.getConnection();
            cstm = con.prepareCall("{call modifygame(?,?,?,?,?,?)}");
            cstm.setInt(1, game.getIdGame());
            cstm.setString(2, game.getNameGame());
            cstm.setString(3, game.getImg_game());
            cstm.setInt(4, game.getCategory_idCategory().getIdCategory());
            cstm.setString(5, game.getDatePremiere());
            cstm.setString(6, game.getStatus());

            flag = cstm.execute();
        }catch(SQLException e){
            CONSOLE.error("Ha ocurrido un error: " + e.getMessage());
        }finally{
            ConnectionMySQL.closeConnections(con, cstm);
        }
        return flag;
    }

    public boolean delete(long idGame){
        boolean flag = false;
        try{
            con = ConnectionMySQL.getConnection();
            cstm = con.prepareCall("{call deletegame(?)}");
            cstm.setLong(1, idGame);

            flag = cstm.execute();
        }catch(SQLException e){
            CONSOLE.error("Ha ocurrido un error: " + e.getMessage());
        }finally{
            ConnectionMySQL.closeConnections(con, cstm);
        }
        return flag;
    }

}
