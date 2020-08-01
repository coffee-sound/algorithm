import random

N = 10

def generate_data():
    numbers = [i + 1 for i in range(N)]
    data = []
    while len(numbers) > 0:
        element = random.randrange(len(numbers))
        data.append(numbers[element])
        numbers.remove(numbers[element])
    return data
