class Example:
    def __init__(self, things):
       self.things = things

    def __sub__(self, other):
        subtracted = []
        for i in self.things:
            if i not in other.things:
                subtracted.append(i)
        return subtracted


# l1 = Example([1, 2, 3, 4, 1])
# l2 = Example([1, 2, 3, 4])
# print(l1 - l2)

x = 0
def a_function(i, j):
    j = j + 1
    i = j * 1
a = [0, 1, 2]
a_function(a[x], x)
print(str(x) + ", " + str(a[0]) + ", " + str(a[1]) + ", " + str(a[2]))
