from data import generate_data

data = generate_data() # 1〜10の数値がバラバラに並んでる
print(data)

# 俺の戦いはここから始まるぜ
divide = lambda data: data if len(data) == 1 else [min(data), max(data)] if len(data) <= 2 else [divide([data[i] for i in range(len(data) // 2)]), divide([data[i] for i in range(len(data) // 2, len(data))])]
def merge(left, right):
    if type(left[0]) is int and type(right[0]) is int:
        indexes, left, right, result = [0, 0], left + [0xFFFF], right + [0xFFFF], []
        while indexes[0] < len(left) - 1 or indexes[1] < len(right) - 1:
            result.append(left[indexes[0]] if left[indexes[0]] < right[indexes[1]] else right[indexes[1]])
            indexes[0 if left[indexes[0]] < right[indexes[1]] else 1] += 1
        return result
    return merge(left, merge(right[0], right[1])) if type(left[0]) is int else (merge(merge(left[0], left[1]), right) if type(right[0]) is int else merge(merge(left[0], left[1]), merge(right[0], right[1])))
print(merge(divide(data)[0], divide(data)[1]))