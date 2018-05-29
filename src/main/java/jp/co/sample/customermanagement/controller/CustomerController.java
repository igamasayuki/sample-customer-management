package jp.co.sample.customermanagement.controller;

import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import jp.co.sample.customermanagement.domain.Customer;
import jp.co.sample.customermanagement.form.CustomerForm;
import jp.co.sample.customermanagement.service.CustomerService;


/**
 * 顧客関連処理を行うコントローラー.
 * 
 * @author igamasayuki
 *
 */
@Controller
@Transactional
@RequestMapping("/customers")
public class CustomerController {
	@Autowired
	private CustomerService customerService;

	/**
	 * フォームの初期化
	 * 
	 * @return 初記化されたフォーム
	 */
	@ModelAttribute
	public CustomerForm setUpForm() {
		return new CustomerForm();
	}

	/**
	 * 初期表示.
	 * 
	 * @param model
	 *            モデル
	 * @return 顧客一覧画面
	 */
	@RequestMapping("/")
	public String list(Model model) {
		List<Customer> customers = customerService.findAll();
		model.addAttribute("customers", customers);
		return "list";
	}

	/**
	 * 顧客登録.
	 * 
	 * @param form
	 *            フォーム
	 * @param result
	 *            リザルト
	 * @param model
	 *            モデル
	 * @return 顧客一覧
	 */
	@RequestMapping("/create")
	public String create(@Validated CustomerForm form, BindingResult result, Model model) {
		if (result.hasErrors()) {
			return list(model);
		}
		Customer customer = new Customer();
		BeanUtils.copyProperties(form, customer);
		customerService.save(customer);
		return "redirect:/customers/";
	}

	/**
	 * 顧客情報更新画面へ遷移.
	 * 
	 * @param id
	 *            顧客ID
	 * @param form
	 *            フォーム
	 * @return 顧客更新画面
	 */
	@RequestMapping("/toedit")
	public String toedit(@RequestParam Integer id, CustomerForm form) {
		Customer customer = customerService.load(id);
		BeanUtils.copyProperties(customer, form);
		return "edit";
	}

	/**
	 * 顧客情報の更新.
	 * 
	 * @param id
	 *            顧客ID
	 * @param form
	 *            フォーム
	 * @param result
	 *            リザルト
	 * @return 顧客一覧画面
	 */
	@RequestMapping("/edit")
	public String edit(@RequestParam Integer id, @Validated CustomerForm form, BindingResult result) {
		if (result.hasErrors()) {
			return toedit(id, form);
		}
		Customer customer = new Customer();
		BeanUtils.copyProperties(form, customer);
		customer.setId(id);
		customerService.update(customer);
		return "redirect:/customers/";
	}

	/**
	 * 顧客一覧画面へ遷移. <br/>
	 * 編集画面の「戻る」ボタンで使用
	 * 
	 * @return 顧客一覧画面
	 */
	@RequestMapping(value = "edit", params = "goToTop")
	public String goToTop() {
		return "redirect:/customers/";
	}

	/**
	 * 顧客情報を削除.
	 * 
	 * @param id
	 *            顧客ID
	 * @return 顧客情報一覧画面
	 */
	@RequestMapping("delete")
	public String delete(@RequestParam Integer id) {
		customerService.delete(id);
		return "redirect:/customers/";
	}
}
