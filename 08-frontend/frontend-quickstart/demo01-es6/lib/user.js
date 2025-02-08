const user = {
    username: 'deltav',
    age: 18
}

const isAdult = ({age}) => {
    if (age >= 18) {
        console.log("adult");
    } else {
        console.log("child");
    }
}

export {user, isAdult}