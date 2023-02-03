package com.example.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.form.InsertAdministracorForm;
import com.example.service.AdministractorService;

@Controller
@RequestMapping("/")
public class AdministracorController {

	@Autowired
	private AdministractorService administractorService;

	@GetMapping("/toInsert")
	public String toInsert(InsertAdministracorForm form) {

		return "administrator/insert";

	}

}
