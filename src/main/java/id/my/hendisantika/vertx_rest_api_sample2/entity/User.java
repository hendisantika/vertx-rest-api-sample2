package id.my.hendisantika.vertx_rest_api_sample2.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import java.io.Serializable;

/**
 * Created by IntelliJ IDEA.
 * Project : vertx-rest-api-sample2
 * User: hendisantika
 * Email: hendisantika@gmail.com
 * Telegram : @hendisantika34
 * Date: 12/01/25
 * Time: 08.13
 * To change this template use File | Settings | File Templates.
 */
@Entity
@Table(name = "external_user")
public class User implements Serializable {
  @Id
  @Column(unique = true, name = "no_cpf")
  private String cpf;

  @Column(name = "non_user", nullable = false)
  private String name;

  @Column(name = "from_email", nullable = false)
  private String email;

  @Column(name = "ic_situation", nullable = false)
  private String status;

  @Column(name = "ic_access_profile", nullable = false)
  private int profile;

  @ManyToOne
  @JoinColumn(name = "co_function", referencedColumnName = "co_function", nullable = false)
  private Position position;
  @Column(name = "nu_telefone")
  private String phone;

  public String getCpf() {
    return cpf;
  }

  public void setCpf(String cpf) {
    this.cpf = cpf;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public String getStatus() {
    return status;
  }

  public void setStatus(String status) {
    this.status = status;
  }

  public int getProfile() {
    return profile;
  }

  public void setProfile(int profile) {
    this.profile = profile;
  }

  public Position getPosition() {
    return position;
  }

  public void setPosition(Position position) {
    this.position = position;
  }

  public String getPhone() {
    return phone;
  }

  public void setPhone(String phone) {
    this.phone = phone;
  }

}
