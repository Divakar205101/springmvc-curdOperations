package com.customer.controller;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.OneToMany;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.transaction.Transactional;

import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Row;
import org.springframework.http.MediaType;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.tomcat.util.http.fileupload.ByteArrayOutputStream;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.customer.enity.BankDetails;
import com.customer.enity.ContactDetails;
import com.customer.enity.CreateBank;
import com.customer.enity.CreateBankMo;
import com.customer.enity.CustomerEntity;
import com.customer.enity.Transaction;
import com.customer.repository.BankDetailsRepository;
import com.customer.repository.ContactDetailsRepository;
import com.customer.repository.CustomerRepository;
import com.customer.service.CustomerService;
import com.google.gson.Gson;






@Controller
public class CustomerController {
	
	
	@Autowired
	private CustomerService customerService;
	
	
	@RequestMapping("/")
	public ModelAndView getAllEmployeeDetails(@ModelAttribute("bean") CustomerEntity e) {
		ModelAndView model = new ModelAndView("index");
		List<CustomerEntity> list = customerService.getAllEmployeeDetails();
		System.out.print(list);
		model.addObject("list", list);
		return model;
	}
	
	@RequestMapping(value = "/add", method = RequestMethod.GET)
	public ModelAndView add() {
		ModelAndView model = new ModelAndView("RegistrationForm");
        CustomerEntity customerEntity = new CustomerEntity();
		model.addObject("customerForm", customerEntity);
		model.addObject("bankdetails", new ArrayList<BankDetails>());
		model.addObject("contactdetails", new ArrayList<ContactDetails>());
		return model;
	}
	
	
	@RequestMapping(value = "/save", method = RequestMethod.POST)
	public ModelAndView save(@ModelAttribute("customerForm")CustomerEntity  customerEntity) {
		System.out.println(customerEntity);
		customerService.saveorupdate(customerEntity);	
		return new ModelAndView("redirect:/");
	}
	
	@RequestMapping(value = "delete/{id}")
	public ModelAndView delete(@PathVariable("id") Integer id) {
		customerService.deleteCustomerbyId(id);
		return new ModelAndView("redirect:/");
	}
	

	@RequestMapping(value = "/search", method = RequestMethod.GET)
	public ModelAndView search(@ModelAttribute("bean") CustomerEntity e) {
		ModelAndView model = new ModelAndView("index");
		 List<CustomerEntity> list = customerService.searchData(e);
		if (!list.isEmpty()) {
			model.addObject("list", list);
		} else {

			return new ModelAndView("popupPage");
		}

		return model;
	}
	
	@RequestMapping(value = "/update/{id}")
	public ModelAndView update(@PathVariable("id") Integer id) {
		ModelAndView model = new ModelAndView("RegistrationForm");
		 CustomerEntity customerEntity = customerService.findById(id);
		 List<BankDetails> bankdetails = customerEntity.getBankdetails();
		 customerEntity.setBankdetails(bankdetails);
		 List<ContactDetails> contactdetails = customerEntity.getContactdetails();
		 customerEntity.setContactdetails(contactdetails);
		model.addObject("customerForm", customerEntity);
		model.addObject("contactdetails", contactdetails);
		model.addObject("bankdetails", bankdetails);
		return model;
	}
   
	@ResponseBody
	@RequestMapping(value = "/getBankDetails/{id}", method = RequestMethod.GET)
    public void getBankdetails(@PathVariable("id") Integer id, HttpServletResponse res) throws IOException {
		 CustomerEntity customerEntity = customerService.findById(id);
		 List<BankDetails> bankdetails = customerEntity.getBankdetails();
		 customerEntity.setBankdetails(bankdetails);
		 bankdetails.forEach(x->{
			 x.setCustomerEntity(null);
		 });
		
		 res.getWriter().write(new Gson().toJson(bankdetails));
    }
	
	@RequestMapping(value = "/downloadBankdetails/{id}", method=RequestMethod.GET)
	public ModelAndView downloadBankdetails(@PathVariable("id") Integer id, HttpServletRequest request, HttpServletResponse response) {
		
		 CustomerEntity customerEntity = customerService.findById(id);
		 List<BankDetails> bankdetails = customerEntity.getBankdetails();
		 customerEntity.setBankdetails(bankdetails);
		 
		 return new ModelAndView(new ExcelReportView(), "bankdetails", bankdetails);
		
		
	}
	
	@GetMapping("/download/bankdetails/{id}")
    public ResponseEntity<byte[]> downloadBankDetails(@PathVariable("id") Integer id) {
       
    	 CustomerEntity customerEntity = customerService.findById(id);
		 List<BankDetails> bankdetails = customerEntity.getBankdetails();
		 customerEntity.setBankdetails(bankdetails);

        // Create workbook and sheet
        Workbook workbook = new XSSFWorkbook();
        Sheet sheet = workbook.createSheet("BankDetails List");

        // Create header row
        Row headerRow = sheet.createRow(0);
        headerRow.createCell(0).setCellValue("ID");
        headerRow.createCell(1).setCellValue("Bank Name");
        headerRow.createCell(2).setCellValue("Account Number");
        headerRow.createCell(3).setCellValue("Branch");
        headerRow.createCell(4).setCellValue("Amount");

        // Create data rows
        int rowNum = 1;
        for (BankDetails bankDetail : bankdetails) {
            Row dataRow = sheet.createRow(rowNum++);
            dataRow.createCell(0).setCellValue(bankDetail.getId());
            dataRow.createCell(1).setCellValue(bankDetail.getBankname());
            dataRow.createCell(2).setCellValue(bankDetail.getAccNo());
            dataRow.createCell(3).setCellValue(bankDetail.getBranch());
            dataRow.createCell(4).setCellValue(bankDetail.getAmount());
        }

        // Convert workbook to byte array
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        try {
            workbook.write(outputStream);
            workbook.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        
        

        // Set response headers for file download
       // HttpHeaders headers = new HttpHeaders();
//        headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
//        headers.setContentDispositionFormData("attachment", "bank_details.xlsx");

        return ResponseEntity
                .ok()
               
                .body(outputStream.toByteArray());
    }
    
    @RequestMapping(value = "/moneytransfer/{id}", method = RequestMethod.GET)
    public ModelAndView moneytransfer(@ModelAttribute("transaction") Transaction transaction,  @PathVariable("id") Integer id) {      
    	ModelAndView modelAndView = new ModelAndView("TransferAmont");
    	
    	CustomerEntity customerEntity = customerService.findById(id);
    	
    	
    	transaction.setFromid(customerEntity.getId());
    	transaction.setFromname(customerEntity.getName());
    	//transaction.setFromamount(customerEntity.getTotal());
    	
    	
    	
    	List<CustomerEntity> DBlist = customerService.getAllEmployeeDetails();
    	List<CustomerEntity>  list = new ArrayList<CustomerEntity>();
    	
    	ArrayList<BankDetails> arrayList = new ArrayList<BankDetails>();
    	
    	for(CustomerEntity c: DBlist) {
    		if(!c.getName().equals(customerEntity.getName())) {
    			list.add(c);
    		}
    	}
    	
    	for(BankDetails b : customerEntity.getBankdetails()) {
    		arrayList.add(b);
    	}

    	modelAndView.addObject("b",arrayList);
    	 modelAndView.addObject("c",list);
    	return modelAndView;
    }
    
	
	/*
	 * @ResponseBody
	 * 
	 * @RequestMapping(value = "/getAll", method = RequestMethod.GET) public void
	 * getEmployeeDetails(HttpServletResponse res) throws IOException {
	 * 
	 * List<CustomerEntity> list = customerService.getAllEmployeeDetails();
	 * list.forEach(x->{ x.setBankdetails(null); }); list.forEach(x->{
	 * x.setContactdetails(null); }); res.getWriter().write(new
	 * Gson().toJson(list)); }
	 */
	 
   
    @RequestMapping(value = "/savetransaction", method = RequestMethod.POST)
	public ModelAndView savetransaction(@ModelAttribute("transaction")Transaction transaction) {
		
    	customerService.savetransaction(transaction);
		
		return new ModelAndView("redirect:/");
	}
    
    @ResponseBody
    @RequestMapping(value = "/getBankById/{id}")
    public void getBankById(@PathVariable("id") Integer id, HttpServletResponse res) throws IOException {
       BankDetails bankById = customerService.getBankById(id);
       bankById.setCustomerEntity(null);
       res.getWriter().write(new Gson().toJson(bankById));
    }
    
    @ResponseBody
    @RequestMapping(value = "/getBankToId/{id}")
    public void getBankToId(@PathVariable("id") Integer id, HttpServletResponse res) throws IOException {
    	CustomerEntity customerEntity = customerService.findById(id);
    	List<BankDetails> bankdetails = customerEntity.getBankdetails();
    	bankdetails.forEach(x->{
			 x.setCustomerEntity(null);
		 });
        res.getWriter().write(new Gson().toJson(bankdetails));
    }
    
    @RequestMapping(value = "/addbank", method = RequestMethod.GET)
	public ModelAndView addbank() {
		ModelAndView model = new ModelAndView("createbank");
		CreateBankMo createBankMo = new CreateBankMo();
		model.addObject("createBankMo", createBankMo);
		model.addObject("createBanks", createBankMo.getCreateBanks());
		return model;
	}
    
    @RequestMapping(value = "/savebank", method = RequestMethod.POST)
	public ModelAndView savebanks(@ModelAttribute("createbankMo") CreateBankMo createBankMo) {
	
//			(createBank);
    	for(CreateBank c: createBankMo.getCreateBanks()) {
    		customerService.saveCreatebank(c);
    	}
		
			
		return new ModelAndView("redirect:/");
	}
    
    
    @RequestMapping(value = "/getAllCreatedBanks", method= RequestMethod.GET)
	public void getAllCreatedBanks(HttpServletResponse res) throws IOException {
		ModelAndView model = new ModelAndView("index");
		List<CreateBank> allCreatedBanks = customerService.getAllCreatedBanks();
		System.out.print(allCreatedBanks);		
		res.getWriter().write(new Gson().toJson(allCreatedBanks));
	}
}
