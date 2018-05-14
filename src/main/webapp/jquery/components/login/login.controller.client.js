(function() {
	
	
	var $usernameFld; 
	var $passwordFld;
    var $loginBtn;
    var userService = new UserServiceClient();
    $(main);

    function main() {
        $usernameFld = $("#usernameFld");
        $passwordFld = $("#passwordFld");
        var data = {
        		username: $usernameFld,
        		password: $passwordFld
        }
        $loginBtn = $("#loginBtn")
            .click(data, login);
    	
    }
    function login(event) {
    	var username = event.data.username.val();
    	var password = event.data.password.val();
    	userService.loginUser(username,password)
    			   .then(function(response){
    				   return response.json();
    			   }).then(success,failure);
    }
    
    function success(responseJSON){
    	var user = responseJSON;
    	location.href="http://localhost:8080/jquery/components/profile/profile.template.client.html?username="+user.username;
    	}
    
    function failure(){
    	alert("Wrong Username/Password");
    }

})();