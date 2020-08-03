from data import generate_data

data = generate_data() # 1〜10の数値がバラバラに並んでる
print(data)

# 俺の戦いはここから始まるぜ
divide = lambda data: data if len(data) == 1 else [min(data), max(data)] if len(data) <= 2 else [divide([data[i] for i in range(len(data) // 2)]), divide([data[i] for i in range(len(data) // 2, len(data))])]
merge = lambda left, right: [right.pop(0) if len(left) == 0 else left.pop(0) if len(right) == 0 else left.pop(0) if left[0] < right[0] else right.pop(0) for i in range(len(left) + len(right))] if type(left[0]) is int and type(right[0]) is int else merge(left, merge(right[0], right[1])) if type(left[0]) is int else (merge(merge(left[0], left[1]), right) if type(right[0]) is int else merge(merge(left[0], left[1]), merge(right[0], right[1])))
print(merge(divide(data)[0], divide(data)[1]))
