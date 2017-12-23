<h1>Spring Oauth2 + MySQL + REST</h1>
This project is part of a tutorial about Oauth2 authentication on Spring by using user info from mysql database.<br/>
The database name is demodb.<br/> 
Use "spring.jpa.hibernate.ddl-auto = create" to create table first. <br/>
After then change it back to "spring.jpa.hibernate.ddl-auto = update"<br/>
The default user accounts are in "project.database.initail.data.users"<br/>
<h3>Tutorial's Summary</h3><br/>
<p>How to create from scratch a REST WebService with Spring Boot. We'll secure it using the Oauth2 protocol, using JSON and Web Tokens, or JWT. There are several interesting materials scattered on the web, however, after studying a lot of then, I believe that the theme could be examined a little further.</p>
<br/>
<h4>To Build and Run</h4>
Go to the cloned directory and run <code>mvn spring-boot:run</code> or build with your chosen IDE.

<h4>Curl Commands</h4>
You could install <a href="https://stedolan.github.io/jq/">./JQ</a> before running these Curl commands in the purpose of convenient.
<div>
<strong>To get a new token</strong> <br/>
<code>
curl trusted-app:secret@localhost:8080/oauth/token -d "grant_type=password&username=user&password=password" | jq
</code>

<br/>
<strong>To get a refresh token</strong><br/>
<code>
curl trusted-app:secret@localhost:8080/oauth/token -d "grant_type=refresh_token&jti=[JTI]&refresh_token=[REFRESH_TOKEN]" | jq
curl trusted-app:secret@localhost:8080/oauth/token -d "grant_type=refresh_token&refresh_token=[REFRESH_TOKEN]" 
</code>

<br/>
<strong>To access a protected resource</strong><br/>
<code>
curl -H "Authorization: Bearer [ACCESS_TOKEN]" localhost:8080/api/hello
</code>

</div>