package beans;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

@ManagedBean(name = "SessionBean")
@SessionScoped
public class SessionBean {
    private int invalidLoginFlag = 0;
    private int userRegisteredFlag = 0;
     
    public int getInvalidLoginFlag() {
		return invalidLoginFlag;
	}
	public void setInvalidLoginFlag(int invalidLoginFlag) {
		this.invalidLoginFlag = invalidLoginFlag;
	}

	public int getUserRegisteredFlag() {
		return userRegisteredFlag;
	}
	public void setUserRegisteredFlag(int userRegisteredFlag) {
		this.userRegisteredFlag = userRegisteredFlag;
	}

	public SessionBean(){};
}
