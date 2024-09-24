with open("letters.txt") as letters:
    string: str = letters.read()

new = [string[0]]

for char in string[1:]:
    new.pop() if new and abs(ord(char) - ord(new[-1])) == 32 else new.append(char)

print("".join(new))
print(len(new))
