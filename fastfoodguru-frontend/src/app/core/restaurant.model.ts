import { Injectable } from '@angular/core';

export class Restaurant {
    constructor (
        public name: string,
        public id: number,
        public address: string,
        public logo: string,
        public reviews: []
    ) {}
}

@Injectable({
    providedIn: "root"
})
export class RestaurantAdapter {
    constructor(){}
    
    adapt(item: any): Restaurant {          
        return new Restaurant(item.name, item.id, item.address, item.logo, item.reviews);
    }
}