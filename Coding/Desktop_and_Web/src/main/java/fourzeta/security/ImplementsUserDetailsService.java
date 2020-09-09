package fourzeta.security;

//@Repository
//@Transactional
//public class ImplementsUserDetailsService implements UserDetailsService{
//
//	@Autowired
//	private UsuarioRepository ur;
//	
//	@Override
//	public UserDetails loadUserByUsername(String login) throws UsernameNotFoundException {
//		Usuario usuario = ur.findByLogin(login);
//		
//		if(usuario == null){
////			throw new UsernameNotFoundException("Usuario não encontrado!");
//			return new User(usuario.getUsername(), usuario.getPassword(), true, true, true, true, usuario.getAuthorities());
//		}
//		return new User(usuario.getUsername(), usuario.getPassword(), true, true, true, true, usuario.getAuthorities());
//	}
//
//}
