package com.f2x.domain;

import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.f2x.domain.security.Authority;
import com.f2x.domain.security.UserRole;
import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class User implements UserDetails{
	/**
	 * 
	 */
	
	@Id
	@Column(name = "userId", nullable = false, updatable = false) 
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long userId;
	private String username;
	private String password;
	private String firstName;
	private String lastName;
	
	
	@Column(name = "email", nullable = false, updatable = true)
	private String email;
	private String phone;
	
	private Boolean enabled=true;
	
	@OneToOne
	private PrimaryAccount primaryAccount;
	@OneToOne
	private SavingsAccount savingAccount;
	@OneToMany(mappedBy = "user", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JsonIgnore
	private List<Appointment> appointment;
	@OneToMany(mappedBy = "user", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	private List<Recipient> recipients;
	
	@OneToMany(mappedBy = "user", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	@JsonIgnore
	private Set<UserRole> userRoles = new HashSet<>();
	
	
	
	public Set<UserRole> getUserRoles() {
		return userRoles;
	}
	public void setUserRoles(Set<UserRole> userRoles) {
		this.userRoles = userRoles;
	}
	public Long getUserId() {
		return userId;
	}
	public void setUserId(Long userId) {
		this.userId = userId;
	}
	
	
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
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
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public Boolean getEnabled() {
		return enabled;
	}
	public void setEnable(Boolean enable) {
		this.enabled = enable;
	}
	public PrimaryAccount getPrimaryAccount() {
		return primaryAccount;
	}
	public void setPrimaryAccount(PrimaryAccount primaryAccount) {
		this.primaryAccount = primaryAccount;
	}
	public SavingsAccount getSavingAccount() {
		return savingAccount;
	}
	public void setSavingAccount(SavingsAccount savingAccount) {
		this.savingAccount = savingAccount;
	}
	public List<Appointment> getAppointment() {
		return appointment;
	}
	public void setAppointment(List<Appointment> appointment) {
		this.appointment = appointment;
	}
	public List<Recipient> getRecipients() {
		return recipients;
	}
	public void setRecipients(List<Recipient> recipients) {
		this.recipients = recipients;
	}
	@Override
	public String toString() {
		return "user [userId=" + userId + ", username=" + username + ", password=" + password + ", firstName="
				+ firstName + ", lastName=" + lastName + ", email=" + email + ", phone=" + phone + ", enabled=" + enabled
				+ "]";
	}
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		Set<GrantedAuthority> authorities = new HashSet<>();
		userRoles.forEach(ur -> authorities.add(new Authority(ur.getRole().getName())));
		return authorities;
		
	}
	
	@Override
	public boolean isAccountNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}
	@Override
	public boolean isAccountNonLocked() {
		// TODO Auto-generated method stub
		return true;
	}
	@Override
	public boolean isCredentialsNonExpired() {
		// TODO Auto-generated method stub
		return false;
	}
	@Override
	public boolean isEnabled() {
		// TODO Auto-generated method stub
		return enabled;
	}
	
	
	
	
}
