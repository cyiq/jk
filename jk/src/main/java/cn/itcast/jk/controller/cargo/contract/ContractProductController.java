package cn.itcast.jk.controller.cargo.contract;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import cn.itcast.jk.controller.BaseController;
import cn.itcast.jk.domain.Contract;
import cn.itcast.jk.domain.ContractProduct;
import cn.itcast.jk.domain.Factory;
import cn.itcast.jk.service.ContractProductService;
import cn.itcast.jk.service.ContractService;
import cn.itcast.jk.service.FactoryService;

@Controller
public class ContractProductController extends BaseController{
	@Resource
	ContractProductService contractProductService;
	@Resource
	FactoryService factoryService;
	
	@RequestMapping("/cargo/contractproduct/tocreate.action")
	public String toCreate(String contractId,Model model){
		model.addAttribute("contractId", contractId);
		
		
		//准备厂家信息
		List<Factory> factoryList = factoryService.getFactoryList();
		model.addAttribute("factoryList", factoryList);
		//准备货物信息
		Map paraMap = new HashMap();
		paraMap.put("contractId", contractId);
		List<ContractProduct> dateList = contractProductService.find(paraMap);
		model.addAttribute("dataList", dateList);
		return "/cargo/contract/jContractProductCreate.jsp";
	}
	
	@RequestMapping("/cargo/contractproduct/insert.action")
	public String insert(ContractProduct contractProduct,Model model){
		contractProductService.insert(contractProduct);
		model.addAttribute("contractId", contractProduct.getContractId());
		return "redirect:/cargo/contractproduct/tocreate.action";
	}
	
	@RequestMapping("/cargo/contractproduct/toupdate.action")
	public String toupdate(String id,Model model){
		//货物信息回显
		ContractProduct obj = contractProductService.get(id);
		model.addAttribute("obj", obj);
		//准备厂家信息
		List<Factory> factoryList = factoryService.getFactoryList();
		model.addAttribute("factoryList", factoryList);
		return "/cargo/contract/jContractProductUpdate.jsp";
	}
	@RequestMapping("/cargo/contractproduct/update.action")
	public String update(ContractProduct contractProduct,Model model){
		contractProductService.update(contractProduct);
		model.addAttribute("contractId", contractProduct.getContractId());
		return "redirect:/cargo/contractproduct/tocreate.action";
	}
	
	@RequestMapping("/cargo/contractproduct/delete.action")
	public String deleteById(String id,String contractId, Model model){
		contractProductService.deleteById(id);
		model.addAttribute("contractId", contractId);
		return "redirect:/cargo/contractproduct/tocreate.action";
	}
	
}
