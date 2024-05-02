package com.project.config.security;

import com.project.entity.Company;
import com.project.repository.CompanyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class JwtUserDetail implements UserDetailsService {
    @Autowired
    private CompanyRepository repository;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return null;
    }


    public UserDetails getCompanyById(Long companyId){
        Optional<Company> companyUser=repository.findOptionalById(companyId);
        if (companyUser.isEmpty()) return null;

        List<GrantedAuthority> authorizedList=new ArrayList<>();
        authorizedList.add(new SimpleGrantedAuthority("EMPLOYEE")); //employee
        authorizedList.add(new SimpleGrantedAuthority("MANAGER")); //manager
        authorizedList.add(new SimpleGrantedAuthority("ADMIN")); //site yöneticisi



        return org.springframework.security.core.userdetails.User.builder()
                .username(companyUser.get().getEmail())
                .password("")
                .accountLocked(false)
                .accountExpired(false)
                .authorities(authorizedList)
                .build();
    }
}
