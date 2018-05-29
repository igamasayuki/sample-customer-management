package jp.co.sample.customermanagement;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import jp.co.sample.customermanagement.repository.CustomerRepository;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SampleCustomerManagementApplicationTests {

	@Autowired
	private CustomerRepository repository;
	
	@Test
	public void contextLoads() {
		repository.findAll().forEach(System.out::println);
	}

}
