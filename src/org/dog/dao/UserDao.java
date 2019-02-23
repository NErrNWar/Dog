package org.dog.dao;

import org.dog.Idao.IUserDao;
import org.dog.entity.User;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("userDao")
public class UserDao extends BaseDao<User> implements IUserDao {

    @Override
    public User getUserByName(String name) {
        List<User> users=find("from User us where us.username=?0",name);
        if(users.size()<=0||users.size()>1){
            return null;
        }
        return users.get(0);
    }

    @Override
    public User getUserByAccount(String account) {
        List<User> users=find("from User us where us.account=?0",account);
        if(users.size()<=0||users.size()>1){
            return null;
        }
        return users.get(0);
    }
}
