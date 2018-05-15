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
        
        $loginBtn = $("#registerBtn")
            .click(register);
    	
    }
    
    function register() {
    	var username = $usernameFld.val();
    	var password = $passwordFld.val();
    	var verifyPassword = $verifyPasswordFld.val();
    	var firstName = $firstNameFld .val();
    	var lastName = $lastNameFld.val();
    	var role = $roleFld.val();
    	
        var user = {
        		username:  username,
        		password:  password,
    			firstName: firstName,
    			lastName:  lastName,
    			role: role
        }
    	
        if (verifyPassword === password){
        	 userService
        	 .registerUser(user)
        	 .then(function(response){
    			   return response.json();
    			   }).then(success,failure);
        }
        else{
        	alert("Password Do Not Match");
        }
    }
    
    function success() {
    	alert("Registered Succesfully");
    }
    
    function failure() {
    	alert("Username Taken Already");
    }

})();