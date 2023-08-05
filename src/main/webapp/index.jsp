<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <title>eBanking System</title>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="http://localhost:8080/demo_war_exploded/w3.css">
    <link rel="stylesheet" href="https://fonts.googleapis.com/css?family=Lato">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
    <style>
        body {font-family: "Lato", sans-serif}
        .mySlides {display: none}
    </style>
</head>
<body>

<!-- Navbar -->
<div class="w3-top">
    <div class="w3-bar w3-black w3-card">
        <a class="w3-bar-item w3-button w3-padding-large w3-hide-medium w3-hide-large w3-right" href="javascript:void(0)" onclick="myFunction()" title="Toggle Navigation Menu"><i class="fa fa-bars"></i></a>
        <a href="#" class="w3-bar-item w3-button w3-padding-large">HOME</a>
        <a href="login.jsp" class="w3-bar-item w3-button w3-padding-large w3-hide-small">LOGIN</a>
        <a href="register.jsp" class="w3-bar-item w3-button w3-padding-large w3-hide-small">REGISTER</a>
    </div>
</div>

<!-- Navbar on small screens (remove the onclick attribute if you want the navbar to always show on top of the content when clicking on the links) -->
<div id="navDemo" class="w3-bar-block w3-black w3-hide w3-hide-large w3-hide-medium w3-top" style="margin-top:46px">
    <a href="#services" class="w3-bar-item w3-button w3-padding-large" onclick="myFunction()">SERVICES</a>
    <a href="#about" class="w3-bar-item w3-button w3-padding-large" onclick="myFunction()">ABOUT</a>
    <a href="#contact" class="w3-bar-item w3-button w3-padding-large" onclick="myFunction()">CONTACT</a>
    <a href="#" class="w3-bar-item w3-button w3-padding-large" onclick="myFunction()">FAQ</a>
</div>

<!-- Page content -->
<div class="w3-content" style="max-width:2000px;margin-top:46px">

    <!-- Automatic Slideshow Images -->
    <div class="mySlides w3-display-container w3-center">
        <img src="bank-backgroud-2.jpeg" style="width:100%">
        <div class="w3-display-bottommiddle w3-container w3-text-white w3-padding-32 w3-hide-small">
            <h3>Banking Made Easy</h3>
            <p><b>Access your accounts, make transactions, and manage finances online.</b></p>
        </div>
    </div>
    <div class="mySlides w3-display-container w3-center">
        <img src="bank-background-3.jpeg" style="width:100%">
        <div class="w3-display-bottommiddle w3-container w3-text-white w3-padding-32 w3-hide-small">
            <h3>Secure &amp; Reliable</h3>
            <p><b>Your privacy and security are our top priorities. Rest assured, your data is safe with us.</b></p>
        </div>
    </div>
    <div class="mySlides w3-display-container w3-center">
        <img src="bank-background.jpeg" style="width:100%">
        <div class="w3-display-bottommiddle w3-container w3-text-white w3-padding-32 w3-hide-small">
            <h3>24/7 Customer Support</h3>
            <p><b>Our dedicated support team is available round-the-clock to assist you with any queries.</b></p>
        </div>
    </div>

    <!-- Services Section -->
    <div class="w3-container w3-content w3-center w3-padding-64" style="max-width:800px" id="services">
        <h2 class="w3-wide">OUR SERVICES</h2>
        <p class="w3-opacity"><i>Banking Simplified</i></p>
        <p class="w3-justify">Welcome to eBanking, your one-stop solution for online banking. Enjoy the convenience of managing your finances from anywhere, anytime. Our services include:</p>
        <div class="w3-row w3-padding-32">
            <div class="w3-third">
                <p>Account Access</p>
                <img src="/demo_war_exploded/extra.jpeg" class="w3-round w3-margin-bottom" alt="Account Access" style="width:60%">
            </div>
            <div class="w3-third">
                <p>Money Transfers</p>
                <img src="trasnfer.jpeg" class="w3-round w3-margin-bottom" alt="Money Transfers" style="width:60%">
            </div>
            <div class="w3-third">
                <p>Bill Payments</p>
                <img src="bills-2.jpeg" class="w3-round" alt="Bill Payments" style="width:60%">
            </div>
        </div>
    </div>

    <!-- About Section -->
    <div class="w3-black" id="about">
        <div class="w3-container w3-content w3-padding-64" style="max-width:800px">
            <h2 class="w3-wide w3-center">ABOUT US</h2>
            <p class="w3-opacity w3-center"><i>Your Trusted Banking Partner</i></p><br>

            <p class="w3-justify">At eBanking, we are committed to providing a seamless banking experience to our customers. Our mission is to empower individuals and businesses with secure, efficient, and innovative financial services. With state-of-the-art technology and a team of dedicated professionals, we strive to make banking easier, faster, and safer for you.</p>

            <p class="w3-justify">Whether you are an individual looking for a convenient way to manage your finances or a business seeking efficient payment solutions, we have got you covered. Join us on this banking journey and experience the future of digital banking.</p>
        </div>
    </div>

    <!-- The Contact Section -->
    <div class="w3-container w3-content w3-padding-64" style="max-width:800px" id="contact">
        <h2 class="w3-wide w3-center">CONTACT</h2>
        <p class="w3-opacity w3-center"><i>Get in touch with us!</i></p>
        <div class="w3-row w3-padding-32">
            <div class="w3-col m6 w3-large w3-margin-bottom">
                <i class="fa fa-map-marker" style="width:30px"></i> Nairobi, Kenya<br>
                <i class="fa fa-phone" style="width:30px"></i> Phone: +254 729291438<br>
                <i class="fa fa-envelope" style="width:30px"> </i> Email: info@ebanking.com<br>
            </div>
            <div class="w3-col m6">
                <form action="/action_page.php" target="_blank">
                    <div class="w3-row-padding" style="margin:0 -16px 8px -16px">
                        <div class="w3-half">
                            <input class="w3-input w3-border" type="text" placeholder="Name" required name="Name">
                        </div>
                        <div class="w3-half">
                            <input class="w3-input w3-border" type="text" placeholder="Email" required name="Email">
                        </div>
                    </div>
                    <input class="w3-input w3-border" type="text" placeholder="Message" required name="Message">
                    <button class="w3-button w3-black w3-section w3-right" type="submit">SEND</button>
                </form>
            </div>
        </div>
    </div>

    <!-- End Page Content -->
</div>

<!-- Image of location/map -->
<img src="/w3images/map.jpg" class="w3-image w3-greyscale-min" style="width:100%">

<!-- Footer -->
<footer class="w3-container w3-padding-64 w3-center w3-opacity w3-light-grey w3-xlarge">
    <i class="fa fa-facebook-official w3-hover-opacity"></i>
    <i class="fa fa-instagram w3-hover-opacity"></i>
    <i class="fa fa-snapchat w3-hover-opacity"></i>
    <i class="fa fa-pinterest-p w3-hover-opacity"></i>
    <i class="fa fa-twitter w3-hover-opacity"></i>
    <i class="fa fa-linkedin w3-hover-opacity"></i>
    <p class="w3-medium">Powered by <a href="https://www.w3schools.com/w3css/default.asp" target="_blank">w3.css</a></p>
</footer>

<script>
    // Automatic Slideshow - change image every 4 seconds
    var myIndex = 0;
    carousel();

    function carousel() {
        var i;
        var x = document.getElementsByClassName("mySlides");
        for (i = 0; i < x.length; i++) {
            x[i].style.display = "none";
        }
        myIndex++;
        if (myIndex > x.length) {myIndex = 1}
        x[myIndex-1].style.display = "block";
        setTimeout(carousel, 4000);
    }

    // Used to toggle the menu on small screens when clicking on the menu button
    function myFunction() {
        var x = document.getElementById("navDemo");
        if (x.className.indexOf("w3-show") == -1) {
            x.className += " w3-show";
        } else {
            x.className = x.className.replace(" w3-show", "");
        }
    }

    // When the user clicks anywhere outside of the modal, close it
    var modal = document.getElementById('ticketModal');
    window.onclick = function(event) {
        if (event.target == modal) {
            modal.style.display = "none";
        }
    }
</script>

</body>
</html>
