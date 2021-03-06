package com.shine.core.profile.service;

import com.shine.core.profile.dao.ShineUserDAO;
import com.shine.core.security.domain.ShineRole;
import com.shine.core.security.domain.ShineUser;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.Objects;
import java.util.Optional;
import java.util.Set;

/**
 * @author Javad Alimohammadi<bs.alimohammadi@gmail.com>
 */
@Service("shineUserServiceImpl")
public class ShineUserServiceImpl implements ShineUserService {

    @Resource(name = "shineUserDAOImpl")
    protected ShineUserDAO shineUserDAO;

    @Resource(name = "roleServiceImpl")
    protected RoleService roleService;


    @Resource(name = "BCryptPasswordEncoder")
    protected PasswordEncoder passwordEncoder;


    @Value("${default.registeration.user_role}")
    protected String defaultRegistrationUserRole;

    @Value("${anonymous_user_name}")
    private String anonymousUsername;


    @Transactional
    @Override
    public ShineUser createNewUser(ShineUser shineUser) {
        return createNewUser(shineUser, defaultRegistrationUserRole);
    }

    @Transactional
    @Override
    public ShineUser createNewUser(ShineUser shineUser, final String roleName) {
        // todo: change default enable status flag with a configuration property
        ShineRole shineRole = roleService.findRoleByNameNN(roleName);
        shineUser.addRole(shineRole);

        final String password = shineUser.getUnEncodedPassword();
        shineUser.setPassword(passwordEncoder.encode(password));

        return shineUserDAO.createOrUpdate(shineUser);
    }

    @Override
    public void updateUser(ShineUser shineUser) {
        shineUserDAO.update(shineUser);
    }

    @Transactional
    @Override
    public Optional<ShineUser> findUserByUserName(String userName) {
        Optional<ShineUser> shineUser = shineUserDAO.readUserByUserName(userName);
        return shineUser;
    }

    @Transactional
    @Override
    public ShineUser findUserByUserNameNN(String userName) throws UsernameNotFoundException {
        return findUserByUserName(userName)
                .orElseThrow(() -> new UsernameNotFoundException(String.format("Username [%s] not found", userName)));
    }

    @Transactional
    @Override
    public Set<ShineRole> findRoleByUserName(String userName) {
        return roleService.findRolesByUserName(userName);
    }

    @Override
    public boolean isCurrentUserAnonymous() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return !Objects.isNull(authentication) &&
                Objects.equals(anonymousUsername, authentication.getPrincipal().toString());
    }

    @Override
    public boolean isAnonymousUser(String login) {
        return Objects.equals(anonymousUsername, login);
    }

    @Override
    public ShineUser currentLoggedInUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (Objects.isNull(authentication)) {
            throw new UsernameNotFoundException("User not logged in");
        }

        return findUserByUserNameNN(authentication.getPrincipal().toString());
    }


}
