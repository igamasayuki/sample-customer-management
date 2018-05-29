package jp.co.sample.customermanagement.domain;

/**
 * 顧客を表すクラス.
 * @author igamasayuki
 *
 */
public class Customer {
	/** ID */
    private Integer id;
    /** 名 */
    private String firstName;
    /** 姓 */
    private String lastName;
    public Customer() {}
	public Customer(Integer id, String firstName, String lastName) {
		super();
		this.id = id;
		this.firstName = firstName;
		this.lastName = lastName;
	}
	@Override
	public String toString() {
		return "Customer [id=" + id + ", firstName=" + firstName + ", lastName=" + lastName + "]";
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
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
