o Build and Run
Go to the cloned directory and run mvn spring-boot:run or build with your chosen IDE.

Spring Security will create default user. Here is the password for the default user\
curl trusted-app:secret@localhost:8080/oauth/token -d "grant_type=password&username=user&password=password"

To get a new token 
curl trusted-app:secret@localhost:8080/oauth/token -d "grant_type=password&username=user&password=password"  

Use Basic token:
Base 64 encode "user:password123!" = "dXNlcjpwYXNzd29yZDEyMyE="
curl -H "AUthorization: Basic dXNlcjpwYXNzd29yZDEyMyE=" localhost:8080/api/user

Use user/password directly
curl user:password123@localhost:8080/api/admin




