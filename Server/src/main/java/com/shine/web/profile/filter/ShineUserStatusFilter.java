package com.shine.web.profile.filter;

import com.shine.common.web.FilterOrder;
import com.shine.common.web.ShineRequestContext;
import com.shine.core.profile.service.ShineUserService;
import com.shine.core.security.dto.UserSession;
import com.shine.web.profile.service.AnonymousUserHolder;
import org.springframework.core.Ordered;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.UUID;

/**
 * @author Javad Alimohammadi<bs.alimohammadi@gmail.com>
 */

public class ShineUserStatusFilter extends OncePerRequestFilter implements Ordered {

    private AnonymousUserHolder anonymousUserHolder;

    private ShineUserService shineUserService;

    public ShineUserStatusFilter(AnonymousUserHolder anonymousUserHolder, ShineUserService shineUserService) {
        this.anonymousUserHolder = anonymousUserHolder;
        this.shineUserService = shineUserService;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        ShineRequestContext shineRequestContext = ShineRequestContext.getShineRequestContext();
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        final boolean isLoggedinUser = !shineUserService.isCurrentUserAnonymous() &&
                authentication.isAuthenticated();

        // set session id
        if (isLoggedinUser) {
            shineRequestContext.setSessionId(UUID.fromString(authentication.getDetails().toString()));
        } else {
            UserSession anonymousUserSession = anonymousUserHolder.getAnonymousUserSession();
            shineRequestContext.setSessionId(anonymousUserSession.getId());
        }


        filterChain.doFilter(request, response);
    }

    @Override
    public int getOrder() {
        return FilterOrder.POST_SECURITY_HIGH;
    }
}
