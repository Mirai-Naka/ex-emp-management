package com.example.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.domain.Administrator;
import com.example.repository.AdministratorRepository;

@Service
@Transactional
public class AdministractorService {

	@Autowired
	private AdministratorRepository administratorRepository;

	/**
	 * @param administrator
	 * 管理者情報を挿入する
	 */
	public void insert(Administrator administrator) {

		administratorRepository.insert(administrator);
	}

	/**
	 * @param mailAddress 管理者のメールアドレス
	 * @param password 管理者のパスワード
	 * @return 入力した情報と合致する管理者の情報
	 */
	public Administrator login(String mailAddress, String password) {
		return administratorRepository.findByMailAddressAndPassword(mailAddress, password);
	}
	
}
