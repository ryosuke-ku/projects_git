package net.ftlines.blog.cdidemo.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Basic;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
public class Employee implements Serializable {

  @GeneratedValue
  @Id
  private Long id;
  @Basic(optional = false)
  private String firstName;
  @Basic(optional = false)
  private String lastName;
  @Basic(optional = false)
  private String email;
  @Temporal(TemporalType.DATE)
  @Basic(optional = false)
  private Date hireDate;

  public Long getId() {
    return id;
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

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public Date getHireDate() {
    return hireDate;
  }

  public void setHireDate(Date hireDate) {
    this.hireDate = hireDate;
  }

  public String getFullName() {
    return firstName + " " + lastName;
  }
}
