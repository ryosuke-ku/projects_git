package net.ftlines.blog.cdidemo.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OrderColumn;

@Entity
public class Team implements Serializable {

  @GeneratedValue
  @Id
  private Long id;
  @Basic(optional = false)
  private String name;

  @ManyToMany(cascade = CascadeType.PERSIST)
  @OrderColumn
  private List<Member> members = new ArrayList<Member>();

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public Long getId() {
    return id;
  }

  public List<Member> getMembers() {
    return members;
  }

}
