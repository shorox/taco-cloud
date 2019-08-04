package sia.tacocloud.dao;

import lombok.Data;
import org.hibernate.validator.constraints.CreditCardNumber;

import javax.persistence.*;
import javax.validation.constraints.Digits;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "taco_orders")
@Data
public class Order implements Serializable {

  private static final long serialVersionUID = 1L;

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Long id;

  @Column(name = "created_at")
  private Date createdAt;

  @Column(name = "delivery_name")
  @NotBlank(message = "Name is required")
  private String name;

  @Column(name = "delivery_street")
  @NotBlank(message = "Street is required")
  private String street;

  @Column(name = "delivery_city")
  @NotBlank(message = "City is required")
  private String city;

  @Column(name = "delivery_state")
  @NotBlank(message = "State is required")
  private String state;

  @Column(name = "delivery_zip")
  @NotBlank(message = "Zip code is required")
  private String zip;

  @Column(name = "cc_number")
  @CreditCardNumber(message = "Not a valid credit card number")
  private String ccNumber;

  @Column(name = "cc_expiration")
  @Pattern(regexp = "^(0[1-9]|1[0-2])([\\/])([1-9][0-9])$", message = "Must be formatted MM/YY")
  private String ccExpiration;

  @Column(name = "cc_cvv")
  @Digits(integer = 3, fraction = 0, message = "Invalid CVV")
  private String ccCVV;

  @ManyToMany(targetEntity = Taco.class)
  private List<Taco> tacos = new ArrayList<>();

  @ManyToOne
  private User user;

  public void addDesign(Taco design) {
    tacos.add(design);
  }

  @PrePersist
  public void createdAt() {
    this.createdAt = new Date();
  }
}
