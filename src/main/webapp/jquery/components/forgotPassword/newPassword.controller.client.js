(function() {
	
	var $usernameFld;
	var $passwordFld;
	var $verifyPasswordFld;
	var $changePasswordBtn;
    var userService = new UserServiceClient();
	$(main);
	
    function main() {
        $usernameFld = $("#usernameFld");
        $passwordFld = $("#passwordFld");
        $verifyPasswordFld = $("#verifyPasswordFld");
        $changePasswordBtn = $("#changePasswordBtn")
            .click(changePassword);  	
    }
    
    function changePassword(){
    	var username = $usernameFld.val();
    	var verifyPassword = $verifyPasswordFld.val();
    	var password =$passwordFld.val();
        if (verifyPassword === password){
       	 userService
       	 .findUserIdByUsername(username)
    	 .then(function(response){
    		 if (response === null){
    			 failure();
    		 }
    		 else{
    			 updatePassword(response,password);
    		 }
    	 });
       }
        else{
        	alert("Password Do Not Match");
        }
        
    }
        function updatePassword(userId,password){
        	userService.findUserById(userId)
        	.then(function(response){
        		var user = {
                		username:  response.username,
                		password:  password,
            			firstName: response.firstName,
            			lastName:  response.lastName,
            			role: response.role,  
            			dateOfBirth: response.dateOfBirth,
            			phone: response.phone,
            			email: response.email
            			
        			}
        		userService.updateUser(userId,user);
        });
        alert("success");
        }
        
        function failure(){
        	alert("Wrong Username");
        }
    	
})();

    