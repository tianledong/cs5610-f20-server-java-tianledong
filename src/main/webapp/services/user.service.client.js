function AdminUserServiceClient() {
    this.url = 'https://wbdv-generic-server.herokuapp.com/api/tianledong/users';
    const self = this;

    this.findAllUsers = () => {
        fetch(this.url)
            .then(response => response.json())
    }
    this.createUser = (user) => fetch(this.url, {
        method: 'POST',
        body: JSON.stringify(user),
        headers: {
            'content-type': 'application/json'
        }
    }).then((response) => response.json());

    this.findUserById = (id) => fetch(`${this.url}${id}`, {
        method: 'GET'
    }).then((response) => response.json());

    this.updateUser = (id, user) => fetch(`${this.url}${id}`, {
        method: 'PUT',
        body: JSON.stringify(user),
        headers: {
            'content-type': 'application/json'
        }
    })

    this.deleteUser= (id) => fetch(`${this.url}${id}`, {
        method: 'DELETE'
    })
}