const N = 10;
const TOO_BIGGER_NUM = N * 10000;

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
        return [...data];
    }
    const half = Math.floor(data.length / 2);
    const left = data.slice(0, half);
    const right = data.slice(half);
    return [[...divide(left)], [...divide(right)]];
};

const merge = (left, right) => {
    if (typeof left[0] === 'number' && typeof right[0] === 'number') {
        if (left.length <= 2) {
            const reverse = (numbers) => {
                if (numbers.length === 2 && numbers[0] > numbers[1]) {
                    return [numbers[1], numbers[0]];
                }
                return [...numbers];
            };
            left = reverse(left);
            right = reverse(right);
        }
        let leftIndex = 0, rightIndex = 0;
        left = [...left, TOO_BIGGER_NUM];
        right = [...right, TOO_BIGGER_NUM];
        let result = [];
        while (rightIndex < right.length - 1 || leftIndex < left.length - 1) {
            const numL = left[leftIndex], numR = right[rightIndex];
            if (numL < numR) {
                result = [...result, numL];
                leftIndex++;
            } else {
                result = [...result, numR];
                rightIndex++;
            }
        }
        return result;
    } else {
        if (typeof left[0] === 'number') {
            return merge(left, merge(right[0], right[1]));
        }
        if (typeof right[0] === 'number') {
            return merge(merge(left[0], left[1]), right);
        }
        return merge(merge(left[0], left[1]), merge(right[0], right[1]));
    }
};

const data = genereteData();
const division = divide(data);
const result = merge(division[0], division[1]);
console.log(data);
console.log(result);
