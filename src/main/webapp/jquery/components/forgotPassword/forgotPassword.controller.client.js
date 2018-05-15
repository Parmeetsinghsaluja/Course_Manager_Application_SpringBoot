(function() {
	
	var $emailFld;
	var $submitBtn;
    function main() {
        $emailFld = $("#emailFld");
        $submitBtn = $("#submitBtn")
            .click(submit);  	
    }
    
    function submit(){
    	var email = $emailFld.val();
    	Email.send(saluja.parmeetsingh@gmail.com,
    			email,
    			"Reset your account",
    			"http://localhost:8080/jquery/components/forgotPassword/newPassword.template.client.html",
    			"smtp.yourisp.com",
    			"saluja.parmeetsingh@gmail.com",
    			"af5a5ec1-ff38-4749-b784-1578773fb2de");
    	
    }
	
})();