package jp.co.sample.customermanagement.repository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

import jp.co.sample.customermanagement.domain.Customer;

/**
 * customersテーブル操作用のリポジトリクラス.
 * 
 * @author igamasayuki
 */
@Repository
public class CustomerRepository {
	/**
	 * ResultSetオブジェクトからCustomerオブジェクトに変換するためのクラス実装&インスタンス化
	 */
	private static final RowMapper<Customer> CUSTOMER_ROW_MAPPER = (rs, i) -> {
		Integer id = rs.getInt("id");
		String firstName = rs.getString("first_name");
		String lastName = rs.getString("last_name");
		return new Customer(id, firstName, lastName);
	};
	
	@Autowired
	private NamedParameterJdbcTemplate jdbcTemplate;

	/**
	 * 顧客情報一覧を取得する.
	 * @return 顧客情報一覧 0件の時は0件のリストが返る
	 */
	public List<Customer> findAll() {
		List<Customer> customers = jdbcTemplate.query(
				"SELECT id,first_name,last_name FROM customers ORDER BY id",
				CUSTOMER_ROW_MAPPER);
		return customers;
	}
	
	/**
	 * IDから1件の顧客情報を取得する.
	 * @param id 検索したい顧客ID
	 * @return 検索された顧客情報　0件の時はnullが返る
	 */
	public Customer load(Integer id) {
		SqlParameterSource param = new MapSqlParameterSource().addValue("id", id);
		Customer customer = jdbcTemplate.queryForObject(
				"SELECT id,first_name,last_name FROM customers WHERE id=:id", 
				param, 
				CUSTOMER_ROW_MAPPER);
		return customer;
	}
	
	/**
	 * <pre>
	 * 引数でもらった顧客情報を登録または更新する.
	 * ID値がnullなら新規登録を行います。
	 * ID値に数値が入っていればそのIDの顧客情報を更新します。
	 * </pre>
	 * @param customer　顧客情報
	 * @return 登録または更新された顧客情報
	 */
	public Customer save(Customer customer) {
		SqlParameterSource param = new BeanPropertySqlParameterSource(customer);
		if (customer.getId() == null) {
			jdbcTemplate.update(
					"INSERT INTO customers(first_name,last_name) values(:firstName, :lastName)",
					param);
		} else {
			jdbcTemplate.update(
					"UPDATE customers SET first_name=:firstName, last_name=:lastName WHERE id=:id",
					param);
		}
		return customer;
	}
	
	/**
	 * IDから1件の顧客情報を削除する.
	 * @param id
	 */
	public void delete (Integer id) {
		SqlParameterSource param = new MapSqlParameterSource().addValue("id", id);
		jdbcTemplate.update("DELETE FROM customers WHERE id=:id", 
				param);
	}
}
