(function () {

    jQuery(main);

    var tbody;
    var template;

    function main() {
        tbody = $('tbody');
        template = $('.template');
        $('#createUser').click(createUser);

        var promise = fetch('http://localhost:8080/api/user');
        promise.then(function (response) {
            return response.json();
        }).then(renderUsers)
    }

    function createUser() {
        console.log('createUser');

        var username = $('#usernameFld').val();
        var password = $('#passwordFld').val();
        var firstName = $('#firstNameFld').val();
        var lastName = $('#lastNameFld').val();
        var date = $('#dateFld').val();

        var user = {
            username: username,
            password: password,
            firstName: firstName,
            lastName: lastName,
            date: date
        };

        fetch('http://localhost:8080/api/user', {
            method: 'post',
            body: JSON.stringify(user),
            headers: {
                'content-type': 'application/json'
            }
        });
    }

    function renderUsers(users) {
        for(var i=0; i<users.length; i++) {
            var user = users[i];
            var clone = template.clone();
            clone.find('.username').html(user.username);
            clone.find('.password').html(user.password);
            clone.find('.firstName').html(user.firstName);
            clone.find('.lastName').html(user.lastName);
            clone.find('.date').html(user.date);
            tbody.append(clone);
        }
    }
    
    function deleteUser(event) {
        var deleteBtn = $(event.currentTarget);
        var userId = deleteBtn
            .parent()
            .parent()
            .attr('id');

        userService
            .deleteUser(userId)
            .then(findAllUsers);
    }

    function editUser(event) {
        console.log('editUser');
        console.log(event);
    }

})();