package com.project.config.security;

import com.burcu.domain.User;
import com.burcu.repository.UserRepository;
import com.project.entity.Auth;
import com.project.repository.AuthRepository;
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
    private AuthRepository repository;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return null;
    }



    //auhtıdden userdetailsıdi oluşturmmaız lazım
    public UserDetails getAuthByAuthId(Long authId){
       Optional<Auth> authUser=repository.findOptionalByAuthId(authId);
        if (authUser.isEmpty()) return null;

        List<GrantedAuthority> yetkiListesi=new ArrayList<>();
        yetkiListesi.add(new SimpleGrantedAuthority("ROLE_USER")); //bu roleler ayrı bir dbden de çekilebilir aslında doğru kulanımı o, role id kullnıcı eşlemeşmesinden
        yetkiListesi.add(new SimpleGrantedAuthority("ADMIN"));
        yetkiListesi.add(new SimpleGrantedAuthority("SUPER_ADMIN"));

        return org.springframework.security.core.userdetails.User.builder()
                .username(authUser.get().getEmail())
                .password("")
                .accountLocked(false) //hesap kitli mi
                .accountExpired(false) //hesap süresi geçmiş mi
                .authorities(yetkiListesi) //kullanıcının yetkilerini yazıyoruz.
                .build();
    }


}
