(function() {
	   $(main);
	var $usernameFld; 
	var $passwordFld;
	var $firstNameFld;
    var $lastNameFld;
    var $roleFld;
	var $verifyPasswordFld;
    var $registerBtn;
    var userService = new UserServiceClient();
    

    function main() {
    	
        $usernameFld = $("#usernameFld");
        $passwordFld = $("#passwordFld");
        $verifyPasswordFld = $("#verifyPasswordFld");
    	$firstNameFld = $("#firstNameFld");
        $lastNameFld = $("#lastNameFld");
        $roleFld = $("#roleFld");
        
        var data = {
        		username: $usernameFld,
        		password: $passwordFld,
        		verifyPassword: $verifyPasswordFld,
    			firstName: $firstNameFld,
    			lastName: $lastNameFld,
    			role: $roleFld 
        }
        $loginBtn = $("#registerBtn")
            .click(data, register);
    	
    }
    
    function register(event) {
    	username = event.data.username.val();
    	password = event.data.password.val();
    	verifyPassword = event.data.verifyPassword.val();
    	firstName = event.data.firstName.val();
    	lastName = event.data.lastName.val();
    	role = event.data.role.val();
    	
        var user = {
        		username:  username,
        		password:  password,
    			firstName: firstName,
    			lastName:  lastName,
    			role: role
        }
    	
        if (verifyPassword === password){
        	 userService
        	 .reg(user)
        	 .then(success);
        }
        else{
        	alert("password do not match")
        }
    }
    
    function success(response) {
    	alert(response)
    }

})();