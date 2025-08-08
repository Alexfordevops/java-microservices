package boxsystem.auth_service.config;

import boxsystem.auth_service.util.JwtUtil;
import boxsystem.auth_service.model.UserModel;
import boxsystem.auth_service.repository.UserRepository;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;


@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private UserRepository userRepository;

    //Filtro do token JWT
    @Override
    protected void doFilterInternal(
            HttpServletRequest request,
            HttpServletResponse response,
            FilterChain filterChain
    ) throws ServletException, IOException {

        //Usa o valor do header authorization
        String authHeader = request.getHeader("Authorization");
        String username = null;
        String jwt = null;

        //Verifica se o header começa com "bearer"
        if (authHeader != null && authHeader.startsWith("Bearer")) {
            jwt = authHeader.substring(7); //Remove o "Bearer"
            username = jwtUtil.extractUsername(jwt); //Usa o username do token
        }

        // Se encontrou username e ainda não autenticou no contexto do Spring
        if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {

            //Busca um usuario com o username extraido do token jwt
            var userOpt = userRepository.findByUsername(username);

            //Se encontrar um usuario e o token for valido
            if(userOpt.isPresent() && jwtUtil.isTokenValid(jwt)){

                UserModel user = userOpt.get();

                //Cria um objeto de autenticação para o spring
                UsernamePasswordAuthenticationToken authToken =
                        new UsernamePasswordAuthenticationToken(user, null, null);
                        authToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));

                //Registra no contexto do spring security
                SecurityContextHolder.getContext().setAuthentication(authToken);
            }
        }

        //Passa a requisição adiante
        filterChain.doFilter(request, response);
    }
}
