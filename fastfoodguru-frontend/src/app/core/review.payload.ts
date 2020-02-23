export class ReviewPayload {
    content: String;
    ffcId: number;
    grade: number;

    constructor(c: String, f: number, g: number) {
        this.content = c;
        this.ffcId = f;
        this.grade = g;
    }

}