![BusReservation](https://user-images.githubusercontent.com/102857782/231242066-ffe16639-e927-4d85-854f-b4f7afedc4fd.jpg)



<h1>Bus Reservation System</h1>

<p>A web service solution designed to provide customers with a personalized easy-to-utilize user experience for booking and purchasing bus tickets online.</p>

<h3>Database Schemas</h3>

![Screenshot (96)](https://user-images.githubusercontent.com/102857782/231252527-23c8491f-3e1f-40ce-95df-b79e0d9720d4.png)



<h3>Tech Stacks</h3>

<p>* Java</p>
<p>* Spring Boot</p>
<p>* Spring Data Jpa</p>
<p>* Spring Security with JWT Token</p>
<p>* MySql Database</p>
<p>* Maven Build tool</p>
<p>* Lombok</p>
<p>* Swagger</p>


<h3> Features</h3>

<p>* User Authentication</p>
<p>* Role based Authorization</p>
<p>* User can control and manage everything on account related reservations, checks bus on specific route, etc</p>
<p>* Admin Panel</p>
<p>* Validations</p>

<h2>Documentation for API</h2>

<h3>Swagger UI Documentation URL</h3>
<h4>https://busreservationsystem-production.up.railway.app/swagger-ui/index.html#/</h4>
<br>
<p><b>NOTE : </b> All the end points are protected except for register user so to use this api first you need to create the user and the login from the email and password that you have provoded while registering the user. After successfull login you will be given the jwt token in the headers section named as authorization. 
You need to authorize that key by sending the token in the key-pair values in the headers like </p> <b> Authorization : Bearer {jwt_Token}</b> 

<h3>URL</h3> 
<h3>https://busreservationsystem-production.up.railway.app/</h3>
<h3>While registering as user then you must only give authority role as user or admin and you can be both also for that you can add two authority one for admin and one for user</h3>

<h3>Admin Controller</h3>

![Screenshot (71)](https://user-images.githubusercontent.com/102857782/224115844-7dd2a366-8ca0-4823-bb6f-9e3703d1fe79.png)

<h3>User Controller</h3>

![Screenshot (70)](https://user-images.githubusercontent.com/102857782/224115235-237f7fae-2301-4d18-b11c-f2a2ceaaf850.png)


<h3>Login Controller</h3>

![Screenshot (92)](https://user-images.githubusercontent.com/102857782/231245107-fde4dc5b-8e2f-429a-bec5-e6f14380b5ed.png)


<b>NOTE :</b>  Read carefully : Wherever you see time in any schemas example given below like <b> "arrivalTime": {
    "hour": 0,
    "minute": 0,
    "second": 0,
    "nano": 0
  }, then kindly consider the schema like this <b>"arrivalTime" : "HH:MM:SS"</b> change it in this schema and provide the time for example <b>"arrivalTime" : "12:01:02"</b> 



![Screenshot (72)](https://user-images.githubusercontent.com/102857782/224118831-3b0151cb-87b1-403c-a84c-43d4958d127c.png)
