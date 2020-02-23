	
export class AuthLoginInfo {
    usernameOrEmail: string;
    password: string;
 
    constructor(username: string, password: string) {
        this.usernameOrEmail = username;
        this.password = password;
    }
}