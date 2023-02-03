package com.example.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.SqlParameter;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

import com.example.domain.Administrator;
import com.example.domain.Employee;

/**
 * @author nakajimamirai
 *
 */
@Repository
public class AdministratorRepository {

	private String adminTable = "administrators";
	private String employeeTable = "employees";

	@Autowired
	private NamedParameterJdbcTemplate template;

	/**
	 * 管理者テーブルのラムダ式
	 */
	private static final RowMapper<Administrator> ADMINISTRATOR_ROW_MAPPER = (rs, i) -> {

		Administrator administrator = new Administrator();
		administrator.setId(rs.getInt("id"));
		administrator.setName(rs.getString("name"));
		administrator.setMailAddress(rs.getString("mail_address"));
		administrator.setPassword(rs.getString("password"));

		return administrator;
	};

	/**
	 * @param administrator 管理者情報 管理者情報の登録
	 */
	public void insert(Administrator administrator) {

		SqlParameterSource param = new BeanPropertySqlParameterSource(administrator);

		String sql = "INSERT INTO " + adminTable
				+ " (name,mail_address,password) VALUES (:name,:mailAddress,:password);";

		template.update(sql, param);
	}

	public Administrator findByMailAddressAndPassword(String mailAddress, String password) {

		String sql = "SELECT id,name.mail_address,password FROM " + adminTable
				+ " WHERE mail_address=:mailAddress AND password = :password;";

		SqlParameterSource param = new MapSqlParameterSource().addValue("mailAddress", mailAddress).addValue("password",
				password);

		Administrator administrator = template.queryForObject(sql, param, ADMINISTRATOR_ROW_MAPPER);
		if (administrator == null) {
			return null;
		}
		return administrator;
	}

}

//(name,image,gender,hire_date,mail_address,zip_code,address,telephone,saraly,characteristics,dependentsCount VALUES (:name,:image,:gender,:hireDate,:mail_:address,:zipCode,:address,:telephone,:saraly,:characteristics,:dependentsCount;";