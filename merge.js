const N = 10;

const genereteData = () => {
    let numbers = [...Array(N)].map((val, index) => index + 1);
    let data = [];
    for (let i = 0; i < N; i++) {
        const element = Math.floor(Math.random() * numbers.length);
        data = [...data, numbers[element]];
        numbers = [...numbers.slice(0, element), ...numbers.slice(element + 1)];
    }
    return data;
};

const data = genereteData();
console.log(data);
