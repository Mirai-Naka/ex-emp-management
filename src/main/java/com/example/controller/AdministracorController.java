package com.example.controller;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.domain.Administrator;
import com.example.form.InsertAdministratorForm;
import com.example.form.LoginForm;
import com.example.service.AdministractorService;

import jakarta.servlet.http.HttpSession;

/**
 * 管理者情報を操作するコントローラ.
 * 
 * @author nakajimamirai
 *
 */
@Controller
@RequestMapping("/")
public class AdministracorController {

	@Autowired
	private AdministractorService administractorService;
	
	@Autowired
	private HttpSession session;

	/**
	 * 管理者情報登録画面に遷移する.
	 * 
	 * @param form フォーム
	 * @return 管理者登録画面
	 */
	@GetMapping("/toInsert")
	public String toInsert(InsertAdministratorForm form) {

		return "administrator/insert";

	}
	
	/**
	 * @param form 管理者登録用フォーム
	 * @return 管理者ログインページ
	 */
	@PostMapping("/insert")
	public String insert(InsertAdministratorForm form) {
		
		Administrator administrator = new Administrator();
		BeanUtils.copyProperties(form, administrator);
		administractorService.insert(administrator);
		
		return "redirect: /";
	}
	
	@GetMapping("/")
	public String toLogin(LoginForm form) {
		
		return "administrator/login";
		
	}

	@PostMapping("/login")
	public String login(LoginForm form, Model model) {
		Administrator administrator = administractorService.login(form.getMailAddress(), form.getPassword());
		if(administrator == null) {
			model.addAttribute("loginError","メールアドレスまたはパスワードが不正です");
			return "administrator/login";
		} else {
			session.setAttribute("administratorName", administrator.getName());
			return "redirect: /employee/showList";
		}
	}
	
}
