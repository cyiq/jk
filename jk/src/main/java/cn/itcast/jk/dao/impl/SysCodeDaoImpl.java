package cn.itcast.jk.dao.impl;


import org.springframework.stereotype.Repository;
import cn.itcast.jk.dao.SysCodeDao;
import cn.itcast.jk.domain.SysCode;

@Repository
public class SysCodeDaoImpl extends BaseDaoImpl<SysCode> implements SysCodeDao {
	public SysCodeDaoImpl() {
		super.setNs("cn.itcast.jk.mapper.SysCodeMapper");
	}
}
