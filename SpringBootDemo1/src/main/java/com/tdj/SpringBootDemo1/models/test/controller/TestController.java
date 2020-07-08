package com.tdj.SpringBootDemo1.models.test.controller;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.List;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.util.ResourceUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.tdj.SpringBootDemo1.config.ResourceConfigBean;
import com.tdj.SpringBootDemo1.models.test.entity.City;
import com.tdj.SpringBootDemo1.models.test.entity.Country;
import com.tdj.SpringBootDemo1.models.test.service.CityService;
import com.tdj.SpringBootDemo1.models.test.service.CountryService;
import com.tdj.SpringBootDemo1.models.test.vo.ApplicationTest;


@Controller
@RequestMapping("/test")
public class TestController {

	@Autowired
	private ApplicationTest applicationTest;
	
	@Autowired
	private CityService cityService;
	
	@Autowired
	private CountryService countryService;
	
	@Autowired
	private ResourceConfigBean resourceConfigBean;
	
	@RequestMapping("/download")
	@ResponseBody
	public ResponseEntity<Resource> download(@RequestParam String fileName) {

		try {
			String resourcePath = resourceConfigBean.getLocalPathForWindow() + fileName;
//			Resource resource = new UrlResource(Paths.get("D:\\upload\\" + fileName).toUri());
			Resource resource = new UrlResource(ResourceUtils.getURL(resourcePath));

			return ResponseEntity.ok()
					.header(HttpHeaders.CONTENT_TYPE, "application/octet-stream")
					.header(HttpHeaders.CONTENT_DISPOSITION, String.format("attachment; filename=%s", fileName))
					.body(resource);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}

		return null;
	}
	
	
	/**
	 * 将文件以BufferedInputStream的方式读取到byte[]里面，然后用OutputStream.write输出文件
	 */
	@RequestMapping("/download1")
	public void downloadFile1(HttpServletRequest request, 
			HttpServletResponse response, @RequestParam String fileName) {
		String filePath = "D:/upload" + File.separator + fileName;
		File downloadFile = new File(filePath);

		if (downloadFile.exists()) {
			response.setContentType("application/octet-stream");
			response.setContentLength((int)downloadFile.length());
			response.setHeader(HttpHeaders.CONTENT_DISPOSITION, 
					String.format("attachment; filename=\"%s\"", fileName));

			byte[] buffer = new byte[1024];
			FileInputStream fis = null;
			BufferedInputStream bis = null;
			try {
				fis = new FileInputStream(downloadFile);
				bis = new BufferedInputStream(fis);
				OutputStream os = response.getOutputStream();
				int i = bis.read(buffer);
				while (i != -1) {
					os.write(buffer, 0, i);
					i = bis.read(buffer);
				}
			} catch (Exception e) {
				LOGGER.debug(e.getMessage());
				e.printStackTrace();
			} finally {
				try {
					if (fis != null) {
						fis.close();
					}
					if (bis != null) {
						bis.close();
					}
				} catch (Exception e2) {
					LOGGER.debug(e2.getMessage());
					e2.printStackTrace();
				}
			}
		}
	}

	/**
	 * 以包装类 IOUtils 输出文件
	 */
	@RequestMapping("/download2")
	public void downloadFile2(HttpServletRequest request, 
			HttpServletResponse response, @RequestParam String fileName) {
		String filePath = "D:/upload" + File.separator + fileName;
		File downloadFile = new File(filePath);

		try {
			if (downloadFile.exists()) {
				response.setContentType("application/octet-stream");
				response.setContentLength((int)downloadFile.length());
				response.setHeader(HttpHeaders.CONTENT_DISPOSITION, 
						String.format("attachment; filename=\"%s\"", fileName));

				InputStream is = new FileInputStream(downloadFile);
				IOUtils.copy(is, response.getOutputStream());
				response.flushBuffer();
			}
		} catch (Exception e) {
			LOGGER.debug(e.getMessage());
			e.printStackTrace();
		}
	}
	
	
	private final static Logger LOGGER = LoggerFactory.getLogger(TestController.class); 
	
	@PostMapping(value = "/files", consumes = "multipart/form-data")
	public String uploadFiles (RedirectAttributes redirectAttributes, @RequestParam MultipartFile [] files) {
		
		boolean tag = true; 	//用来标记是否所有文件都为空

		for (MultipartFile file : files) {
			if (file.isEmpty()) continue;
			try {
				if (tag) tag = false;
				String destPath = "C:\\Users\\anai\\Desktop\\test1\\" + file.getOriginalFilename();
				File destFile = new File(destPath);
				file.transferTo(destFile);
			} catch (IllegalStateException | IOException e) {
				e.printStackTrace();
				redirectAttributes.addFlashAttribute("message", "Upload file fail !!!");
				break;
			}	
		}
		if (!tag) redirectAttributes.addFlashAttribute("message", "Upload file success !!!");
		else	redirectAttributes.addFlashAttribute("message", "Please select more than one file !!!");
		return "redirect:/test/index";	
	}
	
	
	@PostMapping(value = "/file", consumes = "multipart/form-data")
	public String uploadFile (RedirectAttributes redirectAttributes, @RequestParam MultipartFile file) {
		
		String resourcePath = resourceConfigBean.getResourcePath() + file.getOriginalFilename();
		if (file.isEmpty())	
			redirectAttributes.addFlashAttribute("message", "Please select file !!!");
		else {
				try {
					String resourceLocalPath = resourceConfigBean.getLocalPathForWindow() + file.getOriginalFilename();
					File destFile = new File(resourceLocalPath);
					//File destFile = new File(ResourceUtils.getURL(resourcePath).getPath());
					file.transferTo(destFile);
					redirectAttributes.addFlashAttribute("message", "Upload file success !!!");
				} catch (IllegalStateException | IOException e) {
					e.printStackTrace();
					redirectAttributes.addFlashAttribute("message", "Upload file fail !!!");
				}			
		}
		
		redirectAttributes.addFlashAttribute("resourcePath", resourcePath);
		return "redirect:/test/index";
	}
	
	@RequestMapping("/index")
	public String indexPage (ModelMap modelmap) {
		
		List<City> cities = cityService.getCitiesByCountryId(522);
		cities = cities.stream().limit(10).collect(Collectors.toList());	//将cities数据限制在十条
		Country country = countryService.getCountryByCountryId(522);
		
		modelmap.addAttribute("thymeleafTitle", "you are super man!!!!!");
		modelmap.addAttribute("checked", true);
		modelmap.addAttribute("currentNumber", 99);
		modelmap.addAttribute("changeType", "checkbox");
		modelmap.addAttribute("baiduUrl", "/test/log");
		modelmap.addAttribute("city", cities.get(0));
		modelmap.addAttribute("country", country);
		modelmap.addAttribute("cities", cities);
		modelmap.addAttribute("updateUrl", "/api/city");
//		modelmap.addAttribute("shopLogo", 
//				"http://cdn.duitang.com/uploads/item/201308/13/20130813115619_EJCWm.thumb.700_0.jpeg");
		modelmap.addAttribute("shopLogo", 
				"/images/head portrait.jpg");
//		modelmap.addAttribute("template", "test/index");
		
		return "index";
	}
	
	@Value("${server.port}")
	private int port;
	
	@Value("${com.name}")
	private String name;
	
	@Value("${com.age}")
	private int age;

	
	@RequestMapping("/log")
	@ResponseBody
	public String logTest () {
		
		LOGGER.trace("this is trace log");
		LOGGER.debug("this is debug log");
		LOGGER.info("this is info log");
		LOGGER.warn("this is warn log");
		LOGGER.error("this is error log");
		return "this is log test!";
	}
	
	@RequestMapping("/config")
	@ResponseBody
	public String configInfo () {
	
		StringBuffer sb =  new StringBuffer();
		sb.append(port).append("------")
		.append(name).append("------")
		.append(age).append("------").append("<br>");
		
		sb.append(applicationTest.getAge1()).append("------")
			.append(applicationTest.getName1()).append("------").append("<br>");
		return sb.toString();
	}
	
	/**
	 * 127.0.0.1/test/desc?key=fuck
	 */
	@RequestMapping("/desc")
	@ResponseBody
	public String testDesc (@RequestParam String key, HttpServletRequest request) {
		
		String key2 = request.getParameter("key");
		return "this is a test model desc!!!!" + key + "=======" + key2;
	}
}
