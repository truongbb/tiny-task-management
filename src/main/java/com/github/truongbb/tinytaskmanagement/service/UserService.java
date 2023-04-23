package com.github.truongbb.tinytaskmanagement.service;

import com.github.truongbb.tinytaskmanagement.entity.CustomOAuth2User;
import com.github.truongbb.tinytaskmanagement.entity.User;
import com.github.truongbb.tinytaskmanagement.repository.UserRepository;
import com.github.truongbb.tinytaskmanagement.statics.Provider;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class UserService {

    UserRepository userRepository;

    public void processOAuthPostLogin(CustomOAuth2User customOAuth2User) {
        String username = customOAuth2User.getEmail();
        String picture = customOAuth2User.getPicture();
        User existUser = userRepository.getUserByUsername(username);
        if (ObjectUtils.isEmpty(existUser)) {
            User newUser = User.builder()
                    .username(username)
                    .provider(Provider.GOOGLE)
                    .enabled(true)
                    .avatarUrl(picture)
                    .build();
            userRepository.save(newUser);
        }
    }

    public List<User> findAll() {
        List<User> users = new ArrayList<>();
        Iterable<User> userIterable = userRepository.findAll();
        userIterable.forEach(users::add);
        return users;
    }

}
