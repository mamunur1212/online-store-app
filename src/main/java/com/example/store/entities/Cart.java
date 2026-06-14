package com.example.store.entities;

import jakarta.persistence.*;
import java.time.LocalDate;
import java.util.LinkedHashSet;
import java.util.Set;
import java.util.UUID;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "carts")
public class Cart {
  @Id
  @GeneratedValue(strategy = GenerationType.UUID)
  @Column(name = "id")
  private UUID id;

  @Column(name = "date_created", insertable = false, updatable = false)
  private LocalDate dateCreated;

  @OneToMany(mappedBy = "cart", cascade = CascadeType.ALL, orphanRemoval = true)
  @Builder.Default
  private Set<CartItem> items = new LinkedHashSet<>();

  public void addItem(CartItem item) {
    items.add(item);
    item.setCart(this);
  }

  public void removeItem(CartItem item) {
    items.remove(item);
    item.setCart(null);
  }
}
