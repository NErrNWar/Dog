package org.dog.dao;

import org.dog.Idao.IFeedBackDao;
import org.dog.entity.FeedBack;
import org.springframework.stereotype.Repository;

@Repository("feedBackDao")
public class FeedBackDao extends BaseDao<FeedBack> implements IFeedBackDao {
}
