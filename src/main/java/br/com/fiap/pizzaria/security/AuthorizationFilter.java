import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationConfigurerAdapter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

public class AuthorizationFilter extends OncePerRequestFilter {

    private final String HEADER = "Authorization";
    private final String PREFIX = "Bearer ";

    private final String SECRET;

    public AuthorizationFilter(String secret) {
        this.SECRET = secret;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

        try {
            if (existeTokenJWT(request)) {
                Claims claims = validarTokenJWT(request);
                if (claims.get("authorities") != null) {
                    configurarAutenticacao(claims);
                } else {
                    SecurityContextHolder.clearContext();
                }
            }
            filterChain.doFilter(request, response);
        } catch (SignatureException e) {
            response.setStatus(HttpServletResponse.SC_FORBIDDEN);
            response.sendError(HttpServletResponse.SC_FORBIDDEN, e.getMessage());
            return;
        }

    }

    private boolean existeTokenJWT(HttpServletRequest request) {
        String authenticationHeader = request.getHeader(HEADER);
        return authenticationHeader != null && authenticationHeader.startsWith(PREFIX);
    }

    private Claims validarTokenJWT(HttpServletRequest request) {
        String jwt = request.getHeader(HEADER).replace(PREFIX, "");
        return Jwts.parser().setSigningKey(SECRET.getBytes()).parseClaimsJws(jwt).getBody();
    }

    private void configurarAutenticacao(Claims claims) {
        String usuario = claims.getSubject();
        List<String> authorities = (List<String>) claims.get("authorities");
        Collection<SimpleGrantedAuthority> authoritiesConvertidas = authorities.stream().map(SimpleGrantedAuthority::new).collect(Collectors.toList());
        Authentication authentication = new UsernamePasswordAuthenticationToken(usuario, null, authoritiesConvertidas);
        SecurityContextHolder.getContext().setAuthentication(authentication);
    }
}
