<h1>Bus Reservation System</h1>

<p>A web service solution designed to provide customers with a personalized easy-to-utilize user experience for booking and purchasing bus tickets online.</p>

<h2>Documentation for API</h2>

<p><b>NOTE : </b> All the end points are protected except for register user so to use this api first you need to create the user and the login from the email and password that you have provoded while registering the user. After successfull login you will be given the jwt token in the headers section named as authorization. 
You need to authorize that key by sending the token in the key-pair values in the headers like </p> <b> Authorization : Bearer {jwt_Token}</b> 

<h1>URL</h1>
<h3>https://busreservationsystem-production.up.railway.app/</h3>

<h3>While registering as user then you must only give authority role as user or admin and you can be both also for that you can add two authority one for admin and one for user</h3>

<h3>Admin Controller</h3>

![Screenshot (71)](https://user-images.githubusercontent.com/102857782/224115844-7dd2a366-8ca0-4823-bb6f-9e3703d1fe79.png)

<h3>User Controller</h3>

![Screenshot (70)](https://user-images.githubusercontent.com/102857782/224115235-237f7fae-2301-4d18-b11c-f2a2ceaaf850.png)

<b>NOTE :</b> <p> Read carefully : Wherever you see time in any schemas example given below like <b> "arrivalTime": {
    "hour": 0,
    "minute": 0,
    "second": 0,
    "nano": 0
  }, then kindly consider the schema like this <b>"arrivalTime" : "HH:MM:SS"</b> change it in this schema and provide the time for example <b>"arrivalTime" : "12:01:02"</b> </p>



![Screenshot (72)](https://user-images.githubusercontent.com/102857782/224118831-3b0151cb-87b1-403c-a84c-43d4958d127c.png)
