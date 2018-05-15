function UserServiceClient() {
    this.createUser = createUser;
    this.findAllUsers = findAllUsers;
    this.deleteUser = deleteUser;
    this.findUserById = findUserById;
    this.findUserIdByUsername = findUserIdByUsername;
    this.updateUser = updateUser;
    this.registerUser = registerUser;
    this.loginUser = loginUser;
    this.updateProfile = updateProfile;
    this.url =
        'http://localhost:8080/api/user';
    this.login =
        'http://localhost:8080/api/login';
    this.register =
        'http://localhost:8080/api/reg';
    this.profile =
        'http://localhost:8080/api/profile';
    var self = this;

    function loginUser(username, password) {
        return fetch(self.login, {
            method: 'post',
            body: JSON.stringify({username:username, password: password}),
            headers: {
                'content-type': 'application/json'
            }
        }).then(function(response){
			return response;
		});
    }
    

    function updateUser(userId, user) {
        return fetch(self.url + '/' + userId, {
            method: 'put',
            body: JSON.stringify(user),
            headers: {
                'content-type': 'application/json'
            }
        });
    }
    
    function updateProfile(user) {
        return fetch(self.profile, {
            method: 'put',
            body: JSON.stringify(user),
            headers: {
                'content-type': 'application/json'
            }
        })
        .then(function(response){
        	var promise = response.json();
        	return promise.then(function (){
					return promise;
					}, function (){
						return null;
					});
	});
    }

    function findUserById(userId) {
        return fetch(self.url + '/' + userId)
            .then(function(response){
                return response.json();
            });
    }
    
    function findUserIdByUsername(username) {
        return fetch(self.profile + '/' + username)
            .then(function(response){
                return response;
            });
    }

    function deleteUser(userId) {
        return fetch(self.url + '/' + userId, {
            method: 'delete'
        })
    }
    
    function registerUser(user) {
       return fetch(self.register, {
            method: 'post',
            body: JSON.stringify(user),
            headers: {
                'content-type': 'application/json'
            }
        }).then(function(response){
        	return response;
        });
    }

    function findAllUsers() {
        return fetch(self.url)
            .then(function (response) {
                return response.json();
            });
    }

    function createUser(user) {
        return fetch(self.url, {
            method: 'post',
            body: JSON.stringify(user),
            headers: {
                'content-type': 'application/json'
            }
        });
    }
   
}