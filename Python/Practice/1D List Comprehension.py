def one():
    return [x ** 3 for x in range(1, 6)]

def two():
    return [x for x in range(1, 11) if x & 1 == 0]

def three():
    return [fruit[0] for fruit in ['apple', 'banana', 'cherry']]

def four():
    return [len(furniture) for furniture in ['table', 'chair', 'lamp']]

def five():
    return [word.upper() for word in ['hello', 'world', 'python']]

def six():
    return [f"{index}: {number}" for index, number in enumerate([10, 20, 30, 40])]

if __name__ == "__main__":
    print(six())