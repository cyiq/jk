package cn.itcast.jk.dao.impl;


import org.springframework.stereotype.Repository;
import cn.itcast.jk.dao.OutProductDao;
import cn.itcast.jk.vo.OutProductVO;


@Repository
public class OutProductDaoImpl extends BaseDaoImpl<OutProductVO> implements OutProductDao {
	public OutProductDaoImpl() {
		super.setNs("cn.itcast.jk.mapper.OutProductMapper");
	}
}
