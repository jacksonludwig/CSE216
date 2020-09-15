def take_name(num):
    print("No name " + str(num))


print("hey")
print("bye")

for i in range(0, 10):
    take_name(i)


def mult_1(x, n):
    sum = 0
    for _ in range(n):
        sum = sum + x
    return sum


def mult_2(x, n):
    if n == 0:
        return 0
    else:
        return x + mult_2(x, n - 1)
