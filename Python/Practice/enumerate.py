def one():
    colors: list = ['red', 'green', 'blue']

    for index, color in enumerate(colors):
        print(f"{color} {index}")

def two():
    animals: list = ['cat', 'dog', 'rabbit']

    for index, animal in enumerate(animals):
        print(f"index: {index} animal: {animal}")

def three():
    letters: list = ['a', 'b', 'c', 'd']

    for index, letter in enumerate(letters):
        print(f"{index} {letter.upper()}")

def four():
    cosmos: list = ['sun', 'moon', 'starts']

    for index, object in enumerate(cosmos):
        if index & 1 == 0:
            print(object)

if __name__ == "__main__":
    four()