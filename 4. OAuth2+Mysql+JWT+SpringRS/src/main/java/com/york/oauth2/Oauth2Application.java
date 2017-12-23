package com.york.oauth2;

import com.google.gson.Gson;
import com.york.oauth2.models.Account;
import com.york.oauth2.models.Role;
import com.york.oauth2.services.AccountService;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.Arrays;
import java.util.List;

@SpringBootApplication
@EnableAsync
public class Oauth2Application {

	static int nAccountTotal = 0;

	@Value("${project.database.initail.data.users}")
	private String projectDatabaseInitailDataUsers;

	@Bean
	public BCryptPasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	public static void main(String[] args) {
		SpringApplication.run(Oauth2Application.class, args);
	}

//	@Bean
//	@Qualifier("mainDataSource")
//	public DataSource dataSource() {
//		EmbeddedDatabaseBuilder builder = new EmbeddedDatabaseBuilder();
//		EmbeddedDatabase db = builder.setType(EmbeddedDatabaseType.H2).build();
//		return db;
//	}

	@Bean
	CommandLineRunner init(AccountService accountService) {

		CommandLineRunner cmd;
		try {
			List<Account> acccounts = accountService.findAllByOrderByIdAsc();
			if (acccounts != null)
				nAccountTotal = acccounts.size();
		} catch (Exception e) {
			e.printStackTrace();
		}

		if (nAccountTotal == 0) {
			try {
				Gson g = new Gson();
				Account[] p = g.fromJson(projectDatabaseInitailDataUsers, Account[].class);
				if (p != null && p.length > 0) {
					nAccountTotal=p.length;
					for (Account a : p) {
						System.out.println(a.getUsername());
					}

					cmd = (evt) -> Arrays.asList(p).forEach(account -> {
						Account acct = new Account();
						acct.setUsername(account.getUsername());
						acct.setPassword(account.getPassword());
						acct.setFirstName(account.getUsername());
						acct.setLastName("LastName");
						acct.grantAuthority(Role.ROLE_USER);
						if (account.getUsername().equals("admin"))
							acct.grantAuthority(Role.ROLE_ADMIN);
						accountService.registerUser(acct);
						
					});
					if (nAccountTotal > 0)
						return cmd;
				}

			} catch (Exception e) {
				e.printStackTrace();
			}

			if (nAccountTotal == 0) {
				String users = "user,admin";// "user,admin,john,robert,ana";
				cmd = (evt) -> Arrays.asList(users.split(",")).forEach(username -> {
					Account acct = new Account();
					acct.setUsername(username);
					acct.setPassword("password");
					acct.setFirstName(username);
					acct.setLastName("LastName");
					acct.grantAuthority(Role.ROLE_USER);
					if (username.equals("admin"))
						acct.grantAuthority(Role.ROLE_ADMIN);
					accountService.registerUser(acct);
				});
				return cmd;

			}
		}
		return null;

	}
}
