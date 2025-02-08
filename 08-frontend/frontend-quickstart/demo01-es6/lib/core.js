// let var

/*
{
    var a = 1;
    let b = 2;
}

console.log(a);
console.log(b);
*/

/*

var a = 1;
var a = 'abc';

let b = 2;
let b = 1; // core.js:18 Uncaught SyntaxError: Identifier 'b' has already been declared (at core.js:18:5)
*/

/*

console.log(a);
var a = 1;

console.log(b);
let b = 2; // core.js:26 Uncaught ReferenceError: Cannot access 'b' before initialization
*/

/*
const a = 1;
a = 2; // core.js:33 Uncaught TypeError: Assignment to constant variable.
*/

// 解构
/*

const arr = [1, 2, 3];
const [a, b, c] = arr;
console.log(a, b, c);

const person = {
    name: "deltav",
    age: 18,
    gender: "male"
}

const {name, age} = person;
console.log(name, age);

function foo({gender, name, age}) {
    console.log(name, age, gender);
}

foo(person);
*/

// 链判断
/*

let person = null;
person = {
    body: {
        user: {
            firstName: 'deltav'
        }
    }
}
// let firstName = person.body.user.firstName;
// console.log(firstName);

let firstName = (person && person.body && person.body.user && person.body.user.firstName) || 'unknown';
// console.log(firstName);

firstName = person?.body?.user?.firstName || 'unknown';
console.log(firstName);

*/

// 参数默认值
/*

function add(a = 1, b = 2) {
    return a + b;
}

console.log(add(10));

*/
// 箭头函数
/*

function add(a, b) {
    return a + b;
}

// console.log(add(10, 20));

let add1 = function (a, b) {
    return a + b;
}
// console.log(add1(10, 20));

let add2 = (a, b) => {
    return a + b;
}
console.log(add2(10, 20));

*/

// 模板字符串
/*

const name = "deltav", age = 18;
let info = "你好，我的名字是：【" + name + "】，年龄是：【" + age + "】"
console.log(info);

info = `你好，我的名字是：【${name}】，年龄是：【${age}】`
console.log(info);

*/

// Promise
/*

const url = "https://mdn.github.io/learning-area/javascript/apis/fetching-data/can-store/products.json";

console.log(1);

let responsePromise = fetch(url);
responsePromise.then(response => {
    console.log(response);
    console.log(response.status);
    console.log(responsePromise);
    response.json().then(data => {
        console.log(data);
    })
}).catch(error => {
    console.log("error occurred:", error)
    console.log(responsePromise);
})

console.log(2);

*/

// async

/*

async function foo() {
    let x = 101;
    if (x % 2 === 0) {
        return x;
    } else {
        throw new Error('x is not even');
    }
}

foo().then(x => {
    console.log("then", x);
}).catch(e => {
    console.log("error", e);
})

*/

// await
/*

async function foo() {
    let url = "https://mdn.github.io/learning-area/javascript/apis/fetching-data/can-store/products.json";
    let resp = await fetch(url);
    let data = await resp.json();
    console.log(data);
    return "success"
}

console.log(1)
foo().then(r => console.log(r));
console.log(2)

*/




















