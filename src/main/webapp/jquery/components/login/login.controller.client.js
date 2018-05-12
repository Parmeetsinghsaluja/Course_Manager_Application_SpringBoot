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
    	username = event.data.username.val();
    	password = event.data.password.val();
    	console.log(username)
    	userService
    		.login(username,password)
    		.then()
    }

})();