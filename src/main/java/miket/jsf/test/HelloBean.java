package miket.jsf.test;

import javax.faces.bean.ManagedBean;

@ManagedBean(name="welcome", eager = true)
public class HelloBean {
  public String getMessage() {
    return "Hi there!!!";
  }
}
