public class EmpPojo {
    String name;
    String job;
    java.math.BigDecimal salary;

public void setName (String sName) {
  this.name = sName;
}
public String getName () {
  return this.name;
}

public void setJob (String sJob) {
  this.job = sJob;
}
public String getJob () {
  return this.job;
}

public void setSalary (java.math.BigDecimal bdSalary) {
  this.salary = bdSalary;
}
public java.math.BigDecimal getSalary () {
  return this.salary;
}
}
