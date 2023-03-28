package service;

import entity.Commodity;
import entity.User;
import entity.myCommodity;

import java.util.List;

public interface UserService {
     User login(User user);
     List<Commodity> showCommodity();

     List<myCommodity> myCommodity(String userName);

     void addCommodity(String commodityId,String userName);

     void deleteCommodity(String id,String userName);

     int register(User user);


}
