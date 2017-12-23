This demo project shows how to create OAuthServer+RestApi with user account info in memory.

1. SecurityConfig.java:
   Account infos are defined here. For example, user or york or admin, and roles such as USER/ADMIN.
   Spring Security had default USER, also can be assgnied by .authorities("ROLE_ADMIN");

2. ResourceConfig.java:
   Filters are defined here:
   public void configure(HttpSecurity http){
        ...... 
        .antMatchers("/api/hello").access("hasAnyRole('USER')")          
        .antMatchers("/api/admin").hasRole("ADMIN")
        ......


3. Build and Run
   Go to the cloned directory and run mvn spring-boot:run or build with your chosen IDE.

4. Curl Commands
   As an option, you should install ./JQ before running these Curl commands.

5. To get a new token 
   curl trusted-app:secret@localhost:8080/oauth/token -d "grant_type=password&username=user&password=password" 

6. To get a refresh token
   curl trusted-app:secret@localhost:8080/oauth/token -d "grant_type=refresh_token&jti=[JTI]&refresh_token=[REFRESH_TOKEN]"  
   curl trusted-app:secret@localhost:8080/oauth/token -d "grant_type=refresh_token&refresh_token=[REFRESH_TOKEN]"

7. To access a protected resource
   curl -H "Authorization: Bearer [ACCESS_TOKEN]" localhost:8080/api/hello

