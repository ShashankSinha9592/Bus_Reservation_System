<h1>Bus Reservation System</h1>

<p>A web service solution designed to provide customers with a personalized easy-to-utilize user experience for booking and purchasing bus tickets online.</p>

<h2>Documentation for API</h2>

<h3>Models for post or put requests to send body of json data keyname refer below</h3>

<h4>Admin</h4>

{
"adminId": integer , // Optional for post request  but important for put request<br><br>
"email": "string",
"password": "string"
}

<h4>User</h4>

{<br>
"userid": integer, // Optional for post request but important for put request<br>
"dateOfBirth": "YYYY-MM-DD" ,<br>
"email": "string" ,<br>
"firstName": "string" ,<br>
"lastName": "string" ,<br>
"mobile": "string" ,<br>
"password": "string" ,<br>
}


<h4>Route</h4>
{<br>
"routeId": integer, // Optional for post request  but important for put request<br>
"distance" : Integer,<br>
"routeFrom" : "string",<br>
"routeTo" : "string"<br>
}

<h4>Bus</h4>

{<br>
"busId": integer,  // Optional for post request  but important for put request <br>
"arrivalTime": "HH:MM:SS" ,<br> 
"availableSeats": integer ,<br>
"busName": "string",<br>
"busType": "string",<br>
"departureTime": "HH:MM:SS",<br>
"driver": <br>{<br>
"driverName": "string",<br>
"mobile": "string"<br>
},<br>
"totalSeats": integer<br>
}


<h4>Feedback</h4>

{<br>
"feedbackId": integer, // Optional for post request  but important for put request <br>
"comments": "string",<br>
"driverRating": integer,<br>
"feedbackDate": "2023-02-12",<br>
"overAllRating": integer ,<br>
"serviceRating": integer <br>
}


<h4>Reservation </h4>

{<br>
"reservationId": integer,  // Optional for post request  but important for put request  <br>
"destination": "string",<br>
"reservationDate": "YYYY-MM-DD",<br>
"reservationStatus": "string",<br>
"reservationTime": "HH:MM:SS",<br>
"reservationType": "string",<br>
"source": "string"<br>
}


<h4>Admin/User Login detail for post</h4>

{<br>
"email": "string",<br>
"password": "string"<br>
}


<h4> NOTE : To use the api for admin or user one should be logged in only registeration of user does not authenticate token else all APIs are authenticated via token. Note that you will be provided a TOKEN when you login with valid credentials I suggest you to save that token as that token needs to be send with api everytime;

Images of the APIs and their urls you will find type of method also and if the method is post or put you will have to send the JSON body according to the Models you are sending refer to the Models for post pr put methods.</h4>

<p><b>Note</b> For the API usage</p>

API default = http://localhost:8080/admin/{busId}/{token}  // here provide busid in place of {budId} and the token you got in {token}<br>
resulting api example : http://localhost:8080/admin/2/mytoken
<p>You can also change the port number from 8080 to another port number from application.properties just change server.port=8080 to another number</p>
<h2>API Image</h2>
<h3>In the image url =  <b> /user/login which means http://localhost:8080/user/login and this goes for all the urls you will find in the image</h3>
<h3>Admin Login Controller</h3>

<img src = "src/main/resources/ImageResource/AdminLogin.png">



<h3>User Login Api</h3>

<img src = "src/main/resources/ImageResource/UserLogin.png">


<h3>Admin Controller</h3>
<img src = "src/main/resources/ImageResource/AdminContoller.png">

<h3>User Controller</h3>

<img src = "src/main/resources/ImageResource/UserController.png">


<h3>
As this project is not deployed anywhere to run this application locally follow the below steps</h3>


<p><b>Note</b> : Recommended to use Intellij Idea. Refer below link to download community edition free edition</p>
<a>https://www.jetbrains.com/idea/download/#section=windows</a>

<p>Clone this repo in your personal device, continue with the instructions below</p>

<p>open command prompt and follow these steps</p>

<p><b>1)</b> git clone https://github.com/ShashankSinha9592/Bus_Reservation_System.git</p>

<p><b>2)</b> Install MySql if you don't have refer below link to install mysql</p>
<a>https://www.mysql.com/downloads/</a>

<p><b>3)</b> Open mysql and copy paste this query : <b>CREATE DATABASE busReservation; </b></p>
<p><b>4)</b> Open the file busreservationsystem from your IDE Intellij idea and follow below steps</p>

<p><b>**</b> go to src/main/resources/application.properties and change the below two lines from your mysql username and password  </p>
<p>Note : Default username of mysql is root</p>
<p>Remove the curly braces{} after writing your username and password and don't put any extraspace anywhere</p>
<p>spring.datasource.username={YOUR USERNAME}</p>
<p>spring.datasource.password={YOUR PASSWORD}</p>

<p>Go to src/main/java/com/bus_reservation_system/demo/DemoApplication.java and run the code.</p>


