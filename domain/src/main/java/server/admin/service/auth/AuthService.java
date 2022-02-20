package server.admin.service.auth;

import com.sun.istack.NotNull;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import server.admin.model.auth.dto.request.SignInRequest;
import server.admin.model.auth.dto.request.SignUpRequest;
import server.admin.model.auth.dto.response.RefreshTokenResponse;
import server.admin.model.auth.dto.response.SignInResponse;
import server.admin.model.common.rest.RestFailResponse;
import server.admin.model.common.rest.RestResponse;
import server.admin.model.common.rest.RestSuccessResponse;
import server.admin.model.user.entity.User;
import server.admin.model.user.exception.UserException;
import server.admin.model.user.repository.UserRepository;
import server.admin.model.user.role.UserRole;
import server.admin.utils.JwtTokenProvider;
import server.admin.utils.TextMessageProvider;

import javax.mail.MessagingException;
import javax.naming.AuthenticationNotSupportedException;
import java.sql.Timestamp;
import java.util.Arrays;
import java.util.Collection;
import java.util.Optional;
import java.util.concurrent.ThreadLocalRandom;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

import static server.admin.model.admin.exception.AdminException.*;
import static server.admin.model.auth.exception.AuthException.*;
import static server.admin.model.user.exception.UserException.*;

@Service
@RequiredArgsConstructor
@Transactional
public class AuthService implements UserDetailsService {
    private final UserRepository userRepository;
//    private final EmailSender emailSender;
    private final JwtTokenProvider jwtTokenProvider;
    private final TextMessageProvider textMessageProvider;
//    private final AuthenticationManager authenticationManager;

    private void validateNameAndPassword(String name,String password){
        String namePattern = "^[ㄱ-ㅎ|가-힣]+$";//한글만 가능
        String passwordPattern = "^(?=.*[A-Za-z])(?=.*\\d)(?=.*[!@#$%^&*()_+\\-=\\[\\]{};':\"\\\\|,.<>\\/?`~])[A-Za-z\\d!@#$%^&*()_+\\-=\\[\\]{};':\"\\\\|,.<>\\/?`~]{8,}$";//총8자 이상, 영문자, 숫자, 특수문자 각각 하나이상
        if (!Pattern.matches(namePattern, name)){ throw new InvalidNameException(); }
        if (!Pattern.matches(passwordPattern, password)){ throw new InvalidPasswordException(); }
    }

    private Boolean duplicatePhoneNumber( String phoneNumber ){
        Optional<User> optionalUser = userRepository.findByPhoneNumber(phoneNumber);
        return optionalUser.isPresent();
    }
    private Authentication toAuthentication(Long userId, UserRole role){
        Collection<? extends GrantedAuthority> authorities =
                Arrays.stream(role.toString().split(","))
                        .map(SimpleGrantedAuthority::new)
                        .collect(Collectors.toList());

        System.out.println(authorities.toString());
        UserDetails principal = new org.springframework.security.core.userdetails.User(userId.toString(), "plavcorp", authorities);
        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(principal, "plavcorp", authorities);
//        Authentication authentication = authenticationManager.authenticate(authenticationToken);
        return authenticationToken;
    }

//    public VerificationResponse sendVerificationCode(String email) throws MessagingException {
//        final Timestamp currentTime = new Timestamp(System.currentTimeMillis());
//        final Integer verificationCode = ThreadLocalRandom.current().nextInt(100000, 999999);
//        final Timestamp emailVerificationExpiredAt = new Timestamp(System.currentTimeMillis()+600000);
//        final Timestamp emailVerifierValidAt = new Timestamp(System.currentTimeMillis()+300000);
//        final Optional<Admin> optionalAdmin = adminRepository.findByEmail(email);
//
//        if( optionalAdmin.isPresent()){
//            Admin admin = optionalAdmin.get();
//            if(currentTime.before(admin.getEmailVerifierValidAt())){
//                final String message = String.format("현재 이메일을 재인증 할 수 없습니다. %d초 뒤 재인증이 가능합니다. %d",
//                        (admin.getEmailVerifierValidAt().getTime() - currentTime.getTime()) / 1000,
//                        System.currentTimeMillis()
//                        );
//
//                return VerificationResponse.newInstance(
//                        HttpStatus.FORBIDDEN,message
//                );
//            } else {
//                admin.setEmailVerificationCode(verificationCode);
//                admin.setEmailVerifiedExpiredAt(emailVerificationExpiredAt);
//                admin.setEmailVerifierValidAt(emailVerifierValidAt);
//                emailSender.sendEmail(verificationCode.toString(), email);
//
//                return VerificationResponse.newInstance(
//                        HttpStatus.OK, "인증번호가 재발급되었습니다."
//                );
//            }
//        } else {
//            Admin admin = new Admin(
//                    null, null, email, null, false, null, AdminRole.MODERATOR, verificationCode, false, emailVerificationExpiredAt, emailVerifierValidAt
//            );
//            emailSender.sendEmail(verificationCode.toString(), email);
//            adminRepository.save(admin);
//
//            return VerificationResponse.newInstance(
//                    HttpStatus.OK, "인증번호가 발급되었습니다."
//            );
//
//        }
//
//    }

    @Transactional
    public RestResponse verifyingPhoneNumber(
            @NotNull final String phoneNumber
    ) {
        Optional<User> userEntityOptional = userRepository.findByPhoneNumberAndIsEnabledTrue(phoneNumber);

        if (userEntityOptional.isPresent()) {
            final User user = userEntityOptional.get();

            // 해당 핸드폰 번호가 인증 가능한 시간인지 확인
            final Timestamp currentTime = new Timestamp(System.currentTimeMillis());

            if (currentTime.before(user.getPhoneNumberVerifierValidAt())) {
                final String message = String.format(
                        "현재 핸드폰 번호를 인증할 수 없습니다. %d초 뒤 재 인증이 가능합니다. %d",
                        (user.getPhoneNumberVerifierValidAt().getTime() - currentTime.getTime()) / 1000,
                        System.currentTimeMillis()
                );
                System.out.println(message);
                return RestFailResponse.newInstance(
                        HttpStatus.FORBIDDEN,
                        message
                );
            }

            // 인증 가능한 부분 갱신
            final Timestamp expiredAt = new Timestamp(System.currentTimeMillis() + + 180000);
            final Integer verificationCode = ThreadLocalRandom.current().nextInt(100000, 999999);
            user.setPhoneNumberVerifierValidAt(new Timestamp(System.currentTimeMillis() + + 60000));
            user.setLoginVerificationExpiredAt(expiredAt);
            user.setLoginVerificationCode(verificationCode);

            textMessageProvider.sendOne(
                    phoneNumber,
                    String.format("인증번호는 %d 입니다.", verificationCode)
            );

            return RestFailResponse.newInstance(
                    HttpStatus.OK,
                    "발급된 인증번호를 입력해주세요."
            );
        }

        return RestFailResponse.newInstance(
                HttpStatus.NOT_FOUND,
                "해당 핸드폰 번호를 사용하는 계정을 찾을 수 없습니다."
        );
    }

    public RestResponse signUp(SignUpRequest request){
        if(duplicatePhoneNumber(request.getPhoneNumber())){
            return RestFailResponse.newInstance(
                    HttpStatus.CONFLICT,
                    "해당 핸드폰 번호가 이미 존재합니다."
            );
        } else{
            User user = request.toEntity(request);
            userRepository.save(user);
            String message = "어드민 회원가입이 완료되었습니다.";
            return RestSuccessResponse.newInstance(
                    message
            );
        }
    }

    public SignInResponse signIn(
            final String phoneNumber,
            final Integer verificationCode
    ){
        Optional<User> optionalUser = userRepository.findByPhoneNumberAndLoginVerificationCodeAndLoginVerificationExpiredAtIsAfterAndIsEnabledTrue(
                phoneNumber,
                verificationCode,
                new Timestamp(System.currentTimeMillis())
        );
        User user = optionalUser.orElseThrow(RuntimeException::new);

        String accessToken = jwtTokenProvider.createToken(user.getId(), user.getNickname(), toAuthentication(user.getId(), user.getRole()));
        String refreshToken = jwtTokenProvider.createRefreshToken(user.getId(), user.getNickname(), toAuthentication(user.getId(), user.getRole()));
        user.setRefreshToken(refreshToken);
        return new SignInResponse(accessToken, refreshToken);
    }


    @Override
    public UserDetails loadUserByUsername(String userId) throws UsernameNotFoundException {
        return userRepository.findByIdAndIsEnabledTrue(Long.valueOf(userId))
                .orElseThrow(() -> new UsernameNotFoundException(userId));
    }

    public User loadUserByNickname(String nickname, Long userId){
        return userRepository.findByNicknameAndId(nickname, userId).orElseThrow(() -> new UsernameNotFoundException(nickname));
    }

    public RefreshTokenResponse regenerateToken(User user){
        final String accessToken = jwtTokenProvider.createToken(user.getId(),user.getNickname(), toAuthentication(user.getId(), user.getRole()));
        final String refreshToken = jwtTokenProvider.createRefreshToken(user.getId(),user.getNickname(), toAuthentication(user.getId(), user.getRole()));
        Optional<User> optionalAdmin = userRepository.findById(user.getId());
        optionalAdmin.orElseThrow(AdminNotExistException::new).setRefreshToken(refreshToken);
        return new RefreshTokenResponse(accessToken, refreshToken);
//        redisService.setValues(user.getNickname(), refreshToken);
    }

    public Boolean existsRefreshToken(String refreshNickname, Long userId){
        Optional<User> optionalUser= userRepository.findByNicknameAndId(refreshNickname, userId);
        return optionalUser.isPresent();
    }

    public String logout(User user){
        Optional<User> optionalAdmin = userRepository.findById(user.getId());
        optionalAdmin.orElseThrow(AdminNotExistException::new).setRefreshToken(null);
        return "로그아웃에 성공하였습니다.";
    }

}
