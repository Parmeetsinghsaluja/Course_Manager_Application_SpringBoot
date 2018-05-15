(function() {
	
	var $emailFld;
	var $submitBtn;
	$(main);
    function main() {
        $emailFld = $("#emailFld");
        $submitBtn = $("#submitBtn")
            .click(submit);  	
    }
    
    function submit(){
    	var email = $emailFld.val();
    	Email.send("saluja.parmeetsingh@gmail.com",
    			email,
    			"Reset your account",
    			"Click here to reset your password https://saluja-summer1-2018.herokuapp.com/jquery/components/forgotPassword/newPassword.template.client.html",
    			"smtp.yourisp.com",
    			"saluja.parmeetsingh@gmail.com",
    			"af5a5ec1-ff38-4749-b784-1578773fb2de",
    			function done(message) { alert("Check Your Email") } 
    	);
    	
    }
	
})();