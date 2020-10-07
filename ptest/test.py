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


for i in range(10):
    print("hey")


class Ball:
    """This is a ball"""

    def __init__(self, diameter):
        self.diameter = diameter

    def increase_diamter(self, amount):
        self.diameter = self.diameter + amount


count = 0
for _ in range(0, int(input("enter amt"))):
    count = count + 1
print(count)


class BiggerBall(Ball):
    def __init__(self, diameter):
        super().__init__(diameter)
        self.color = "red"


ball = BiggerBall(10)
ball.increase_diamter(10)
print(ball.diameter)
