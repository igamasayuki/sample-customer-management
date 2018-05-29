package jp.co.sample.customermanagement.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jp.co.sample.customermanagement.domain.Customer;
import jp.co.sample.customermanagement.repository.CustomerRepository;

/**
 * 顧客関連サービスクラス.
 * @author igamasayuki
 *
 */
@Service
public class CustomerService {
    @Autowired
    private CustomerRepository customerRepository;
    
	/**
	 * 顧客情報一覧を取得する.
	 * @return 顧客情報一覧 0件の時は0件のリストが返る
	 */
    public List<Customer> findAll() {
        return customerRepository.findAll();
    }
    
	/**
	 * IDから1件の顧客情報を取得する.
	 * @param id 検索したい顧客ID
	 * @return 検索された顧客情報　0件の時はnullが返る
	 */
    public Customer load(Integer id) {
        return customerRepository.load(id);
    }    
    
	/**
	 * 引数でもらった顧客情報を登録する.
	 * @param customer　顧客情報
	 * @return 登録された顧客情報
	 */
    public Customer save(Customer customer) {
        return customerRepository.save(customer);
    }
    
	/**
	 * 引数でもらった顧客情報を更新する.
	 * @param customer　顧客情報
	 * @return 更新された顧客情報
	 */
    public Customer update(Customer customer) {
        return customerRepository.save(customer);
    }

	/**
	 * IDから1件の顧客情報を削除する.
	 * @param id
	 */
    public void delete(Integer id) {
        customerRepository.delete(id);
    }
}
