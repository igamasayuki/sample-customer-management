package jp.co.sample.customermanagement.form;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * 顧客関連フォーム.
 * @author igamasayuki
 *
 */
public class CustomerForm {
    /** 名 */
    @NotNull
    @Size(min=1, max=127, message="名は1文字以上127文字以内で記載してください")
    private String firstName;

    /** 姓 */
    @NotNull
    @Size(min=1, max=127, message="姓は1文字以上127文字以内で記載してください")
    private String lastName;

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
}
