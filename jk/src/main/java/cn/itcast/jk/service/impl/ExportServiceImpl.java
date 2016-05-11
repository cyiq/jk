package cn.itcast.jk.service.impl;

import java.io.Serializable;	
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.annotation.Resource;
import javax.jws.WebMethod;
import javax.jws.WebService;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import cn.itcast.jk.dao.ContractDao;
import cn.itcast.jk.dao.ExportDao;
import cn.itcast.jk.dao.ExportProductDao;
import cn.itcast.jk.dao.ExtEproductDao;
import cn.itcast.jk.domain.Contract;
import cn.itcast.jk.domain.Export;
import cn.itcast.jk.domain.ExportProduct;
import cn.itcast.jk.domain.ExtEproduct;
import cn.itcast.jk.pagination.Page;
import cn.itcast.jk.service.ExportService;
import cn.itcast.jk.vo.ContractProductVO;
import cn.itcast.jk.vo.ContractVO;
import cn.itcast.jk.vo.ExtCproductVO;
import cn.itcast.util.UtilFuns;


@Service
@WebService
public class ExportServiceImpl implements ExportService{
	@Resource
	ExportDao exportDao;
	@Resource
	ContractDao contractDao;
	@Resource
	ExportProductDao exportProductDao;
	@Resource
	ExtEproductDao extEproductDao;
	
	@WebMethod(exclude=true)
	public void setExportDao(ExportDao exportDao) {
		this.exportDao = exportDao;
	}

	@Override
	@WebMethod(exclude=true)
 	public List<Export> findPage(Page page) {
		return exportDao.findPage(page);
	}

	@Override
	@WebMethod(exclude=true)
	public List<Export> find(Map paraMap) {
		return exportDao.find(paraMap);
	}

	@Override
	public Export get(String id) {
		return exportDao.get(id);
	}

	@Override
	@WebMethod(exclude=true)
	public void insert(String[] contractIds) {
		String contractNos = "";
		for(int i = 0 ; i < contractIds.length; i++){
			ContractVO contract = contractDao.view(contractIds[i]);
			contractNos += contract.getContractNo() + " ";			//' '空格作为分隔符
		}
		contractNos = UtilFuns.delLastChar(contractNos);
		
		Export export = new Export();
		
		export.setId(UUID.randomUUID().toString());
		export.setContractIds(UtilFuns.joinStr(contractIds,","));  //数组拼串
		export.setCustomerContract(contractNos);
		export.setState(0);
		exportDao.insert(export);
		//处理货物信息
		for(int i = 0 ; i < contractIds.length; i++){
			ContractVO contract = contractDao.view(contractIds[i]);
			for(ContractProductVO cp: contract.getContractProducts()){
				ExportProduct ep = new ExportProduct();
				ep.setId(UUID.randomUUID().toString());
				ep.setExportId(export.getId());
				ep.setFactoryId(cp.getFactory().getId());
				ep.setFactoryName(cp.getFactory().getFactoryName());
				ep.setProductNo(cp.getProductNo());
				ep.setPackingUnit(cp.getPackingUnit());
				ep.setCnumber(cp.getCnumber());
				ep.setBoxNum(cp.getBoxNum());
				ep.setPrice(cp.getPrice());
				exportProductDao.insert(ep);
				//处理附件信息
				for(ExtCproductVO extcp :cp.getExtCproducts()){
					ExtEproduct extep = new ExtEproduct();
					
					BeanUtils.copyProperties(extcp, extep);
					
					extep.setId(UUID.randomUUID().toString());
					
					extep.setExportProductId(ep.getId());
					extep.setFactoryId(extcp.getFactory().getId());
					extep.setFactoryName(extcp.getFactory().getFactoryName());
					
					extEproductDao.insert(extep);
				}
			}
		}
		
	}

	@Override
	@WebMethod(exclude=true)
	public void update(Export export) {
		exportDao.update(export);
	}

	@Override
	@WebMethod(exclude=true)
	public void deleteById(Serializable id) {
		exportDao.deleteById(id);
	}

	@Override
	@WebMethod(exclude=true)
	public void delete(Serializable[] ids) {
		exportDao.delete(ids);
	}

	@Override
	@WebMethod(exclude=true)
	public void submit(Serializable[] ids) {
		Map map = new HashMap();
		map.put("state", 1);
		map.put("ids", ids);
		
		exportDao.updateState(map);
	}

	@Override
	@WebMethod(exclude=true)
	public void cancle(Serializable[] ids) {
		Map map = new HashMap();
		map.put("state", 0);
		map.put("ids", ids);
		
		exportDao.updateState(map);		
	}

	@Override
	@WebMethod(exclude=true)
	public List<Contract> getContractList() {
		Map paraMap = new HashMap();
		paraMap.put("state", 1);	//已上报
		
		return contractDao.find(paraMap);
	}
	
	
	//拼接JS串
	//function addTRRecord(objId, id, productNo, cnumber, grossWeight, netWeight, sizeLength, sizeWidth, sizeHeight, exPrice, tax)

	@WebMethod(exclude=true)
	public String getMrecordData(String exportId){
		Map paraMap = new HashMap();
		paraMap.put("exportId", exportId);
		
		List<ExportProduct> oList = exportProductDao.find(paraMap );
		
		StringBuffer sBuf = new StringBuffer();
		for(ExportProduct ep : oList){
			sBuf.append("addTRRecord(\"mRecordTable\", \"").append(ep.getId())
			.append("\", \"").append(ep.getProductNo()).append("\", \"").
			append(ep.getCnumber()).append("\", \"")
			.append(UtilFuns.convertNull(ep.getGrossWeight()))
			.append("\", \"").append(UtilFuns.convertNull(ep.getNetWeight()))
			.append("\", \"").append(UtilFuns.convertNull(ep.getSizeLength()))
			.append("\", \"").append(UtilFuns.convertNull(ep.getSizeWidth()))
			.append("\", \"").append(UtilFuns.convertNull(ep.getSizeHeight()))
			.append("\", \"").append(UtilFuns.convertNull(ep.getExPrice()))
			.append("\", \"").append(UtilFuns.convertNull(ep.getTax())).append("\");");
		}
		
		return sBuf.toString();
	}

	@Override
	@WebMethod(exclude=true)
	public void update(Export export, String[] mr_id, Integer[] mr_orderNo,
			Integer[] mr_cnumber, Double[] mr_grossWeight,
			Double[] mr_netWeight, Double[] mr_sizeLength,
			Double[] mr_sizeWidth, Double[] mr_sizeHeight, Double[] mr_exPrice,
			Double[] mr_tax, Integer[] mr_changed) {
		exportDao.update(export);
		for(int i = 0; i < mr_id.length;i++){
			if(mr_changed[i]!=null && mr_changed[i]==1){
				ExportProduct ep = exportProductDao.get(mr_id[i]);
				ep.setOrderNo(mr_orderNo[i]);
				ep.setCnumber(mr_cnumber[i]);
				ep.setGrossWeight(mr_grossWeight[i]);
				ep.setNetWeight(mr_netWeight[i]);
				ep.setSizeLength(mr_sizeLength[i]);
				ep.setSizeWidth(mr_sizeWidth[i]);
				ep.setSizeHeight(mr_sizeHeight[i]);
				ep.setExPrice(mr_exPrice[i]);
				ep.setTax(mr_tax[i]);
				exportProductDao.update(ep);
			}
		}
	}
}

