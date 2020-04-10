package com.all.car.service;

import com.all.car.mapper.RegMapper;
import com.all.car.model.CustomUserModel;
import com.all.car.model.UserModel;
import lombok.AllArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.Collection;

@Service
@AllArgsConstructor
public class RegServiceImpl implements RegService, UserDetailsService {

    private RegMapper regMapper;
    private PasswordEncoder passwordEncoder;


    @Override
    public int insert(UserModel userModel) {
        userModel.setPassword(passwordEncoder.encode(userModel.getPassword()));
        System.out.println(userModel.getPassword());
        return regMapper.insert(userModel);
    }

    @Override
    public UserModel findByEmail(String email) {
        return regMapper.findByEmail(email);
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        HttpServletRequest httpServletRequest = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();// form email="uru" pw="1234"
        String password = httpServletRequest.getParameter("password");//form 에서 입력한 1234
        UserModel userModel = findByEmail(email);//DB에서 form에서 입력된 email="uru"을 기준으로 데이터 가져와서 model에 삽입
        //userModel.getPassword() = $2a$10$Ypup2SckkNIYoi2hdK.2jOWLrV9x0WPYzzX9ltisYDYDvVjqPHj7G
        // pw = "1234"
        //비교 passwordEncoder의 matches 메서드 사용 앞부분 form에서 받아온 pw="1234" 뒤 파라미터 DB에서 가져온 인코딩된 패스워드
        if(userModel != null && passwordEncoder.matches(password,userModel.getPassword())) {
            //생성자 통해서 순서대로 넣어준다.
            return new CustomUserModel(userModel.getEmail(), userModel.getPassword(), auth("member"), userModel.getUserId(), userModel.getName(), userModel.getEmail());
        }

        throw new UsernameNotFoundException(email);
    }

    private  static Collection<? extends GrantedAuthority> auth(String auth) {
        Collection<SimpleGrantedAuthority> authorities = new ArrayList<>();
        authorities.add(new SimpleGrantedAuthority(auth));
        return authorities;
    }


}
