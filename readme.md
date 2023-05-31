## Student Management System

The goal of this project is to build a student management system
that has the following functionalities:

1. registration and creation of student accounts (users).
2. activation of student accounts using an activation key.
3. registration of teachers (admin action).
4. ability for students to register for courses.
5. ability to view student's gpa

I broke the project down into three main parts:
1. securing the application with authentication and authorization.
2. building the business logic.
3. containerizing and deploying the application.

So far, I have completed only the first part which I will illustrate the 
workflow:

### Securing the application
This section consists of registering users, authenticating users; 
basically the whole login flow and user verification. I will explain how to run the application, 
and test the registration and login flow. Ensure you have Java 17 installed on your machine.

The project requirements include:
1. an editor for tweaking come property variables (Intellij IDEA, VS code)
2. MySql server and MySql work bench
3. docker (preferably docker desktop)

Once you meet these requirements are met, you can start the spring boot application
by running the following code in the terminal `./mvnw spring-boot:run`. You can also
run the project in your IDE if you are using one.

After this works, you have to run the following command in your terminal in order
to run a redis instance `docker run -d --name redis-stack -p 6379:6379 -p 8001:8001 redis/redis-stack:latest
`. This will run a redis instance in docker with port number 6379.

The redis instance is used in the application to store activation codes, which will expire after 
30 minutes


Now you are all set, let's see how to use the applications

#### Registering Students

url: `POST {{base_url}}/api/v1/student/register`

request
```agsl
{
    "firstName": "Victor",
    "lastName": "Osimehen",
    "email": "osimehen@gmail.com",
    "phoneNumber": "2348102023271",
    "password": "Password123$"
}
```
response
```agsl
{
    "data": {
        "userId": "29556139-f8a7-45fc-9fff-a74740275717",
        "email": "mosesvictor@gmail.com",
        "matricNUmber": "554682",
        "activationKey": "6706688"
    },
    "message": "successful",
    "error": false
}
```
#### Activate student account

url: `PATCH {{base_url}}/api/v1/student/register`

params:
> `key` 6706688

> `userId` 29556139-f8a7-45fc-9fff-a74740275717

response
```agsl
{
    "data": {
        "userId": "29556139-f8a7-45fc-9fff-a74740275717",
        "email": "mosesvictor@gmail.com"
    },
    "message": "successful",
    "error": false
}
```

#### Login user

url: `POST {{base_url}}/api/v1/user/authenticate`

request
```agsl
{
    "username": "mosesvictor@gmail.com",
    "password": "Password123$"
}
```
response
```agsl
{
    "data": {
        "authToken": "eyJhbGciOiJIUzI1NiJ9.eyJpc3MiOiJzdHVkZW50LWFwcCIsInN1YiI6Imp3dC10b2tlbiIsInVzZXJuYW1lIjoibW9zZXN2aWN0b3JAZ21haWwuY29tIiwiYXV0aG9yaXRpZXMiOiJST0xFX1N
                        UVURFTlQiLCJpYXQiOjE2ODU1NjA1OTcsImV4cCI6MTY4NTU2NDU5N30.cLlp5XVhrtBx1nGTJ-PdY8X_Fjy9saYGkioCmS7BBRw"
    },
    "message": "successful",
    "error": false
}
```
validating the authToken on `jwt.io` we have the following payload

```agsl
{
  "iss": "student-app",
  "sub": "jwt-token",
  "username": "mosesvictor@gmail.com",
  "authorities": "ROLE_STUDENT",
  "iat": 1685560597,
  "exp": 1685564597
}
```

Authentication successful. You can see that the registered student has `ROLE_STUDENT`.
You can register teachers as well but you need to create an admin user, which can be 
done with SQL.

Other parts of the project will be completed in the future, but as of now, the application
is secured, and authorizations are in place to ensure students dont change their grades.
