package cn.itcast.jk.dao;

import java.util.Map;	

import cn.itcast.jk.domain.PackingList;

public interface PackingListDao extends BaseDao<PackingList>{
	public void updateState(Map map);
}
