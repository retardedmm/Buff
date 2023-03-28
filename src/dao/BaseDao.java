package dao;

import entity.Commodity;
import entity.User;
import entity.myCommodity;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import util.JdbcUtils;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class BaseDao<T> {
    private QueryRunner queryRunner = new QueryRunner();

    public User login(User user) {
        Connection con = JdbcUtils.getConnection();
        String sql = "select `userName`,`password` from user where userName = ? and password = ?";
        try {
            return queryRunner.query(con, sql, new BeanHandler<>(User.class), user.getUserName(), user.getPassword());
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            JdbcUtils.close(con);
        }
        return null;
    }


    

    public List<Commodity> showCommodity() {
        Connection con = JdbcUtils.getConnection();
        String sql = "select commodityId,commodityName,commodityPrice from commodity";
        try {
            return queryRunner.query(con, sql, new BeanListHandler<>(Commodity.class));
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            JdbcUtils.close(con);
        }
        return null;
    }

    public List<myCommodity> myCommodity(String userName) {
        Connection con = JdbcUtils.getConnection();
        String sql = "select commodityId,commodityName,commodityPrice ,Id from "+userName;
        try {
            return queryRunner.query(con, sql, new BeanListHandler<>(myCommodity.class));
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            JdbcUtils.close(con);
        }
        return null;
    }

    public void addCommodity(String commodityId,String userName) {
        Connection con = JdbcUtils.getConnection();
        String sql = "select commodityId,commodityName,commodityPrice from commodity where commodityId="+commodityId;
        try {
            Commodity commodity = queryRunner.query(con, sql, new BeanHandler<>(Commodity.class));
            sql="insert into "+userName+"(commodityId,commodityName,commodityPrice) values('"+commodity.getCommodityId()+"','"+commodity.getCommodityName()+"','"+commodity.getCommodityPrice()+"')";
            queryRunner.update(con,sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            JdbcUtils.close(con);
        }
    }

    public void deleteCommodity(String id,String userName) {
        Connection con = JdbcUtils.getConnection();
        String sql="delete from "+userName+" where Id='"+id+"'";
        try {
            queryRunner.update(con,sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            JdbcUtils.close(con);
        }
    }

    public int register(User user) {
        int update=0;
        BaseDao<Object> dao = new BaseDao<>();
        int existence = dao.isExistence(user.getUserName());
        if(existence==1){
            return 0;
        }
        if(existence==2){
            return 2;
        }

        Connection con = JdbcUtils.getConnection();
        String sql="insert into user values('"+user.getUserName()+"','"+user.getPassword()+"')";

        try {
             update = queryRunner.update(con, sql);
             sql="create table "+user.getUserName()+"(commodityId int(11) not null,commodityName varchar(50) not null,commodityPrice varchar(30) not null,Id int primary key auto_increment)";
             update=queryRunner.update(con,sql);
             return 1;
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            JdbcUtils.close(con);
        }
        return update;


    }
    public int isExistence(String userName){
        Connection con = JdbcUtils.getConnection();
        if(userName==null){
            return 2;
        }
        String sql = "select * from user where userName='"+userName+"'";
        try {
            User query = queryRunner.query(con, sql, new BeanHandler<>(User.class));
            if (query==null){
                return 0;
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            JdbcUtils.close(con);
        }
        return 1;
    }


}







