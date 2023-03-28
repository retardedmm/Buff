package service;

import dao.BaseDao;
import entity.Commodity;
import entity.User;
import entity.myCommodity;

import java.util.List;

public class UserServiceImpl implements UserService{
    private BaseDao dao=new BaseDao();

    @Override
    public User login(User user) {
        return dao.login(user);
    }

    @Override
    public List<Commodity> showCommodity() {
        return dao.showCommodity();
    }

    @Override
    public List<myCommodity> myCommodity(String userName) {
        return dao.myCommodity(userName);
    }

    @Override
    public void addCommodity(String commodityId ,String userName) {
        dao.addCommodity(commodityId,userName);
    }

    @Override
    public void deleteCommodity(String id,String userName) {
        dao.deleteCommodity(id,userName);
    }

    @Override
    public int register(User user) {
         return dao.register(user);
    }



}
