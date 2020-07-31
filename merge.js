const N = 8;

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

const divide = (data) => {
    if (data.length <= 2) {
        return data;
    }
    const half = Math.floor(data.length / 2);
    const left = data.slice(0, half);
    const right = data.slice(half);
    return [[...divide(left)], [...divide(right)]];
};

const data = genereteData();
const division = divide(data);
console.log(division);
