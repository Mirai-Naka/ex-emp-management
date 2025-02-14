package com.example.repository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

import com.example.domain.Employee;

@Repository
public class EmployeeRepository {

	private String employeeTable = "employees";

	@Autowired
	private NamedParameterJdbcTemplate template;

	/**
	 * 従業員テーブルのラムダ式
	 */
	private static final RowMapper<Employee> EMPLOYEE_ROW_MAPPER = (rs, i) -> {

		Employee employee = new Employee();
		employee.setId(rs.getInt("id"));
		employee.setName(rs.getString("name"));
		employee.setImage(rs.getString("image"));
		employee.setGender(rs.getString("gender"));
		employee.setHireDate(rs.getDate("hire_date"));
		employee.setMailAddress(rs.getString("mail_address"));
		employee.setZipCode(rs.getString("zip_code"));
		employee.setAddress(rs.getString("address"));
		employee.setTelephone(rs.getString("telephone"));
		employee.setSalary(rs.getInt("salary"));
		employee.setCharacteristics(rs.getString("characteristics"));
		employee.setDependentsCount(rs.getInt("dependentsCount"));

		return employee;
	};

	/**
	 * @return 従業員情報を全件取得（入社日の降順でソート）
	 */
	public List<Employee> findAll() {

		String sql = "SELECT name,image,gender,hire_date,mail_address,zip_code,address,telephone,saraly,characteristics,dependentsCount FROM "
				+ employeeTable + " ORDER BY hire_date desc;";

		List<Employee> employeeList = template.query(sql, EMPLOYEE_ROW_MAPPER);
		return employeeList;
	}

	/**
	 * @param id
	 * @return
	 * 一人分の従業員情報取得
	 */
	public Employee load(Integer id) {

		String sql = "SELECT name,image,gender,hire_date,mail_address,zip_code,address,telephone,saraly,characteristics,dependentsCount FROM "
				+ employeeTable + " WHERE id=:id;";

		SqlParameterSource param = new MapSqlParameterSource().addValue("id", id);

		Employee employee = template.queryForObject(sql, param, EMPLOYEE_ROW_MAPPER);
		return employee;
	}

	/**
	 * @param employee
	 * 従業員情報の変更
	 */
	public void update(Employee employee) {

		SqlParameterSource param = new BeanPropertySqlParameterSource(employee);

		String sql = "UPDATE " + employeeTable
				+ " SET name=:name,image=:image,gender=:gender,hire_date=:hireDate,mail_address=:mailAddress,zip_code=:zipCode,address=:address,telephone=:telephone,saraly=:saraly,dependentCount=:dependentCount WHERE id=:id;";

		template.update(sql, param);
	}
}
