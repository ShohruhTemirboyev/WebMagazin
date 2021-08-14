package com.example.webmagazin.entity;

import com.example.webmagazin.entity.enam.Role;
import com.example.webmagazin.entity.template.AbsEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "users")
public class User extends AbsEntity implements UserDetails {

    @Column(unique = true,nullable = false)
    private String phoneNumber;
    @Column(nullable = false)
    private String password;
    private String firstName;
    private String lastName;
    private String middleName;
    private String birthDate;
    private String email;
    private String number;
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "liked_products", joinColumns = {@JoinColumn(name = "user_id")},
            inverseJoinColumns = {@JoinColumn(name = "product_id")})
    private List<Product> likedProduct;
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "card_products", joinColumns = {@JoinColumn(name = "user_id")},
            inverseJoinColumns = {@JoinColumn(name = "product_id")})
    private List<Product> card;
    private Integer location;
    @ManyToOne
    private Address address;
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name="user_role",
        joinColumns = {@JoinColumn(name = "user_id")},
        inverseJoinColumns = {@JoinColumn(name = "role_id")})
    private List<Role> roles;

    public User(String phoneNumber, String password, String userName, List<Role> roles) {
        this.phoneNumber = phoneNumber;
        this.password = password;
        this.lastName = userName;
        this.roles = roles;
    }

    boolean accountNonExpired=true;
boolean accountNonLocked=true;
boolean credentialsNonExpired=true;
boolean enabled=true;
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return this.roles;
    }

    @Override
    public String getUsername() {
        return phoneNumber;
    }

    @Override
    public boolean isAccountNonExpired() {
        return accountNonExpired;
    }

    @Override
    public boolean isAccountNonLocked() {
        return accountNonLocked;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return credentialsNonExpired;
    }

    @Override
    public boolean isEnabled() {
        return enabled;
    }
}
