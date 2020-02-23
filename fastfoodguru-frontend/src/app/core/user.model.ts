import { Injectable } from '@angular/core';
export class User {
    constructor (
        public id: number,
        public username: string,
        public name: string,
        public email: string
    ) {}
}

@Injectable({
    providedIn: "root"
})
export class UserAdapter {
    constructor(){}
    
    adapt(item: any): User {          
        var v = new User(item.id, item.username, item.name, item.email);
        console.log(v);
        return v;
    }
}