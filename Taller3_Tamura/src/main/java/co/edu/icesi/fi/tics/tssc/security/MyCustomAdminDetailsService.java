package co.edu.icesi.fi.tics.tssc.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import co.edu.icesi.fi.tics.tssc.model.TsscAdmin;
import co.edu.icesi.fi.tics.tssc.repository.TsscAdminRepository;

@Service
public class MyCustomAdminDetailsService implements UserDetailsService {

	private TsscAdminRepository adminRepository;
	
	@Autowired
	public MyCustomAdminDetailsService(TsscAdminRepository adminRepository) {		
		this.adminRepository = adminRepository;
	}
	
	@Override
	public UserDetails loadUserByUsername(String user) throws UsernameNotFoundException {
		TsscAdmin tsscAdmin = adminRepository.findByUser(user);
		if (tsscAdmin != null) {
			User.UserBuilder builder = User.withUsername(user).password(tsscAdmin.getPassword()).roles(tsscAdmin.getSuperAdmin());
			return builder.build();
		} else {
			throw new UsernameNotFoundException("User not found.");
		}
	}

}