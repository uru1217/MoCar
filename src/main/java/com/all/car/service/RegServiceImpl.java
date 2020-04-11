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
        //userModel에는 지금 가입하려고 입력한 email 값이 들어있다.
        //email을 기준으로 아이디 갯수를 불러오기 위해서 파라미터로 email을 사용해서 체크
        //그이후 1 일때랑 0일때랑 값을 나눠서 컨트롤러로 보낸다.
        //0인경우 regMapper.insert가 작동하도록
        //1 인경우 0리턴
//        if (checkEmail(userModel.getEmail()) == 0){
            return regMapper.insert(userModel);
//        }
//        return 0;
    }

    @Override
    public UserModel findByEmail(String email) {
        return regMapper.findByEmail(email);
    }

    @Override
    public int checkEmail(String email) {
        System.out.println(regMapper.checkEmail(email)+"service"+email);
        return regMapper.checkEmail(email);
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
