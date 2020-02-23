import { Injectable } from '@angular/core';

export class Review {
    constructor (
        public id: number,
        public content: string,
        public restaurantName: string,
        public grade: number,
        public author: string
    ) {}
}

@Injectable({
    providedIn: "root"
})
export class ReviewAdapter {
    constructor(){}
    
    adapt(item: any): Review {
        return new Review(item.id, item.string, item.restaurantName, item.grade, item.author);
    }
}