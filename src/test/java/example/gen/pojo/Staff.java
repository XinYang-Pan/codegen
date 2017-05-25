package example.gen.pojo;

import io.github.xinyangpan.commons.functions.Copyable;
import example.gen.pojo.Staff;

public class Staff implements Copyable<Staff> {

	private String department;

	private int depId;

	public static final String DEPARTMENT = "department";

	public static final String DEP_ID = "depId";

	public String getDepartment() {
		return this.department;
	}

	public void setDepartment(String department) {
		this.department = department;
	}

	public int getDepId() {
		return this.depId;
	}

	public void setDepId(int depId) {
		this.depId = depId;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Staff [department=");
		builder.append(department);
		builder.append(", depId=");
		builder.append(depId);
		builder.append("]");
		return builder.toString();
	}

	@Override
	public Staff copy() {
		Staff copy = new Staff();
		copy.department = this.department;
		copy.depId = this.depId;
		return copy;
	}

}