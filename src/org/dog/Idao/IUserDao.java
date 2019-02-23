package org.dog.Idao;

import org.dog.entity.User;

public interface IUserDao extends IBaseDao<User>{
    User getUserByName(String name);
    User getUserByAccount(String account);
}
