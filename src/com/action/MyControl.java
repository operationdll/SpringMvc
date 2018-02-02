package com.action;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.service.TestService;

@Controller
@RequestMapping(value = "/test")
public class MyControl {
	@Autowired
	private TestService testService;

	@Autowired
	@Qualifier("mailSender")
	private JavaMailSender mailSender;

	@RequestMapping(value = "/init.do", method = { RequestMethod.GET,
			RequestMethod.POST })
	public String init(HttpServletRequest req, HttpServletResponse response,
			Model model) {
		model.addAttribute("datas", testService.init("SurveyCheckLoss"));
		this.sendMail();
		return "test";
	}

	@RequestMapping(value = "/testImport.do", method = { RequestMethod.POST,
			RequestMethod.GET })
	public void testImport(MultipartHttpServletRequest request,
			HttpServletResponse response, String taskCde) throws Exception {
		PrintWriter out = null;
		try {
			MultipartFile mpf = request.getFile("file");
			System.out.println(mpf.getOriginalFilename());
			JSONObject json = new JSONObject();
			json.put("retStr", "导入成功!");
			response.setCharacterEncoding("UTF-8");
			out = response.getWriter();
			out.print(json.toString());
			out.flush();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (null != out) {
				out.close();
			}
		}
	}

	@RequestMapping(value = "/exportFile.do", method = { RequestMethod.POST,
			RequestMethod.GET })
	public void exportFile(String url, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		FileInputStream fis = null;
		BufferedInputStream bis = null;
		OutputStream os = null;
		try {
			File file = new File(url);
			fis = new FileInputStream(file);
			bis = new BufferedInputStream(fis);
			byte[] bytes = new byte[1024];
			int len = 0;
			response.reset();
			response.setContentType("application/x-msdownload");
			response.setHeader("Content-Disposition", "attachment; filename="
					+ file.getName());
			os = response.getOutputStream();
			while ((len = bis.read(bytes)) > 0) {
				os.write(bytes, 0, len);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (null != os)
					os.close();
				if (null != bis)
					bis.close();
				if (null != fis)
					fis.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	/**
	 * 发送邮件
	 */
	private void sendMail() {
		try {
			MimeMessage message = mailSender.createMimeMessage();
			MimeMessageHelper helper;
			helper = new MimeMessageHelper(message, true);
			// 收件人
			helper.setTo("co-duanlanling001@ejintai.com");
			// 抄送人
			helper.setCc("co-liuzhaoqiang001@ejintai.com");
			// 发件人(默认为mail.from)
			helper.setFrom("co-tanzhiyuan001@ejintai.com");
			helper.setText("测试数据!");
			// 添加附件
			FileSystemResource file = new FileSystemResource(new File(
					"D:/a.jpg"));
			helper.addAttachment("c.jpg", file);
			mailSender.send(message);
		} catch (MessagingException e) {
			e.printStackTrace();
		}
	}

	public TestService getTestService() {
		return testService;
	}

	public void setTestService(TestService testService) {
		this.testService = testService;
	}

}
